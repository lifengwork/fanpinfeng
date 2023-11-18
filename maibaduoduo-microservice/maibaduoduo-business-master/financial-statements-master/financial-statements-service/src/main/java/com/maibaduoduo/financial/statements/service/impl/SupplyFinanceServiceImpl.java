package com.maibaduoduo.financial.statements.service.impl;

import com.maibaduoduo.financial.statements.entity.*;
import com.maibaduoduo.financial.statements.service.SupplyFinanceService;
import org.springframework.stereotype.Service;

/**
 * 供应商实际财务状况
 * 具体财务数据来源企业ERP系统或者企业财务报表
 */
@Service
public class SupplyFinanceServiceImpl implements SupplyFinanceService {
    @Override
    public SupplyFinanceVo computeSupplyFinance(String supplyId) {
        return this.initFinanceDataFromERP(supplyId);
    }
    private SupplyFinanceVo computeSupplyFinance(AssetsEntity assetsEntity,
                                                 IncomeEntity incomeEntity, LiabilityEntity liabilityEntity,
                                                 OwnersEquityEntity ownersEquityEntity) {
        return new SupplyFinanceVo(assetsEntity,incomeEntity,liabilityEntity,ownersEquityEntity);
    }
    private SupplyFinanceVo initFinanceDataFromERP(String supplyId) {
        /**
         * 从ERP系统或者财务系统获取供应商财务数据
         * TODO
         */
        //填充具体的资产表、负债表、利润表等
        AssetsEntity assetsEntity = new AssetsEntity();
        IncomeEntity incomeEntity = new IncomeEntity();
        LiabilityEntity liabilityEntity = new LiabilityEntity();
        OwnersEquityEntity ownersEquityEntity = new OwnersEquityEntity();
        return this.computeSupplyFinance(assetsEntity,incomeEntity,liabilityEntity,ownersEquityEntity);
    }
}
