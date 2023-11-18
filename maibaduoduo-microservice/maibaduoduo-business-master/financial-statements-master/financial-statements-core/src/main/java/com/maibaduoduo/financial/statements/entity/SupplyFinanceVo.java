package com.maibaduoduo.financial.statements.entity;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * 了解供供应商的实际经营状况、预防供应风险等
 * 数据来源ERP
 */
public class SupplyFinanceVo {
    private AssetsEntity assetsEntity;
    private IncomeEntity incomeEntity;
    private LiabilityEntity liabilityEntity;
    private OwnersEquityEntity ownersEquityEntity;

    /**
     * 供应商实际经营状况
     * @param assetsEntity
     * @param incomeEntity
     * @param liabilityEntity
     * @param ownersEquityEntity
     */
    public SupplyFinanceVo(AssetsEntity assetsEntity,
                              IncomeEntity incomeEntity,
                              LiabilityEntity liabilityEntity,
                              OwnersEquityEntity ownersEquityEntity) {
    this.assetsEntity = assetsEntity;
    this.incomeEntity = incomeEntity;
    this.liabilityEntity = liabilityEntity;
    this.ownersEquityEntity = ownersEquityEntity;
    }

    /**
     * 资产负债比率
     */
    private BigDecimal assetsLiabilitiesRatio;
    /**
     * 流动资产占总资产
     */
    private BigDecimal CurrentAssetsOfTotalAssetsRatio;
    /**
     * 流动比率
     */
    private BigDecimal currentRation;
    /**
     * 供应商存货周转率
     */
    private BigDecimal stockTurnover;
    /**
     * 供应商主业务毛利率
     */
    private BigDecimal grossMargin;
    /**
     * 供应商总资产回报率
     */
    private BigDecimal returnOnAssets;

    public BigDecimal getAssetsLiabilitiesRatio() {
        if (null != liabilityEntity.getTotalLiability() && null != assetsEntity.getTotalAssets()) {
            assetsLiabilitiesRatio = liabilityEntity.getTotalLiability().
                    divide(assetsEntity.getTotalAssets(), BigDecimal.ROUND_HALF_UP).
                    setScale(2).multiply(BigDecimal.valueOf(100));
        }
        return assetsLiabilitiesRatio;
    }

    public BigDecimal getCurrentAssetsOfTotalAssetsRatio() {
        if (null != assetsEntity.getCash()
                && null != assetsEntity.getTradeAccountsReceivable()
                && null != assetsEntity.getInventories() && null != assetsEntity.getTotalAssets()) {
            BigDecimal totalCurrentAssets = assetsEntity.getCash().
                    add(assetsEntity.getInventories()).
                    add(assetsEntity.getTradeAccountsReceivable());
            CurrentAssetsOfTotalAssetsRatio = totalCurrentAssets.
                    divide(assetsEntity.getTotalAssets(), BigDecimal.ROUND_HALF_UP).
                    setScale(2).multiply(BigDecimal.valueOf(100));
        }
        return CurrentAssetsOfTotalAssetsRatio;
    }

    public BigDecimal getCurrentRation() {
        return currentRation;
    }

    public BigDecimal getStockTurnover() {
        //以收入为基础的存货周转率
        if(null!=incomeEntity.getOperatingRevenue()&&null!=assetsEntity.getInventories()){
            stockTurnover =incomeEntity.getOperatingRevenue().
                    divide(assetsEntity.getInventories(),BigDecimal.ROUND_HALF_UP)
                    .setScale(2).multiply(BigDecimal.valueOf(100));
        }
        return stockTurnover;
    }

    public BigDecimal getGrossMargin() {
        //主营业务收入净额 - 主营业务成本
        if (null != incomeEntity.getOperatingRevenue() && null != incomeEntity.getSalesCosts()) {
            grossMargin = incomeEntity.getOperatingRevenue().subtract(incomeEntity.getSalesCosts()).divide(incomeEntity.getOperatingRevenue())
                    .setScale(2).multiply(BigDecimal.valueOf(100));
        }
        return grossMargin;
    }

    public BigDecimal getReturnOnAssets() {
        //净利润总额/平均资产总额（(年初+年末)/2）
        if (null != incomeEntity.getTotalProfit() && null != assetsEntity.getTotalAssets()) {
            returnOnAssets = incomeEntity.getTotalProfit().divide(assetsEntity.getTotalAssets())
                    .setScale(2).multiply(BigDecimal.valueOf(100));
        }
        return returnOnAssets;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SupplyFinanceVo.class.getSimpleName() + "[", "]")
                .add("assetsLiabilitiesRatio=" + assetsLiabilitiesRatio)
                .add("CurrentAssetsOfTotalAssetsRatio=" + CurrentAssetsOfTotalAssetsRatio)
                .add("currentRation=" + currentRation)
                .add("stockTurnover=" + stockTurnover)
                .add("grossMargin=" + grossMargin)
                .add("returnOnAssets=" + returnOnAssets)
                .toString();
    }
}
