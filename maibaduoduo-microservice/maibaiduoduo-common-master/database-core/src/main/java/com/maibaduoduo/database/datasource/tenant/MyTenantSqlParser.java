package com.maibaduoduo.database.datasource.tenant;

import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.update.Update;

/**
 * @Description: //TODO
 * @date: 2023/4/19 16:46
 * @Author: pm2022
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyTenantSqlParser extends TenantSqlParser {
    /**
     * select 语句处理
     */
    @Override
    public void processSelectBody(SelectBody selectBody) {
        MyTenantHandler tenantHandler = (MyTenantHandler) getTenantHandler();
        if (tenantHandler.doAdminFilter()){
            return;
        }
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
                operationList.getSelects().forEach(this::processSelectBody);
            }
        }
    }

    /**
     * insert 语句处理
     */
    @Override
    public void processInsert(Insert insert) {
        MyTenantHandler tenantHandler = (MyTenantHandler) getTenantHandler();
        if (tenantHandler.doTableFilter(insert.getTable().getName())) {
            return;
        }
        if (tenantHandler.doAdminFilter()){
            return;
        }
        insert.getColumns().add(new Column(tenantHandler.getTenantIdColumn()));
        if (insert.getSelect() != null) {
            processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
        } else if (insert.getItemsList() != null) {
            ItemsList itemsList = insert.getItemsList();
            if (itemsList instanceof MultiExpressionList) {
                ((MultiExpressionList) itemsList).getExprList().forEach(el -> el.getExpressions().add(tenantHandler.getTenantId(true)));
            } else {
                ((ExpressionList) insert.getItemsList()).getExpressions().add(tenantHandler.getTenantId(true));
            }
        } else {
            throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId");
        }
    }

    /**
     * update 语句处理
     */
    @Override
    public void processUpdate(Update update) {
        MyTenantHandler tenantHandler = (MyTenantHandler) getTenantHandler();
        if (tenantHandler.doAdminFilter()){
            return;
        }
        Table table = update.getTable();
        if (tenantHandler.doTableFilter(table.getName())) {
            return;
        }
        update.setWhere(this.andExpression(table, update.getWhere()));
    }

    /**
     * delete 语句处理
     */
    @Override
    public void processDelete(Delete delete) {
        MyTenantHandler tenantHandler = (MyTenantHandler) getTenantHandler();
        if (tenantHandler.doAdminFilter()){
            return;
        }
        if (tenantHandler.doTableFilter(delete.getTable().getName())) {
            return;
        }
        delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere()));
    }
}
