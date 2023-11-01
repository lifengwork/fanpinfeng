package com.maibaduoduo.task.program.strategy;

public class StandardPlanStrategy extends PlanStrategy {
    private PlanTemplate planTemplate;
    public StandardPlanStrategy(PlanTemplate planTemplate){
        this.planTemplate  = planTemplate;
    }

    @Override
    public boolean strategy(Strategy strategy) {
        planTemplate.one();
        return false;
    }
}
