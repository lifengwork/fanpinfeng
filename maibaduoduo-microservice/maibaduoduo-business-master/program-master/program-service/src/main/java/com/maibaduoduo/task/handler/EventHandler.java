package com.maibaduoduo.task.handler;

import com.lmax.disruptor.WorkHandler;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

public abstract class EventHandler implements WorkHandler<ProgramEvent> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void doHandle(ProgramEvent programEvent){
        throw new SaasException("任务处理器为空，请实现。");
    }

    public abstract EventHandler programEventHandlerInit(Program program,Executor executor);

    @Override
    public void onEvent(ProgramEvent programEvent){
        this.beforeExecute();
        this.doHandle(programEvent);
        this.afterExecute();
    }
    abstract void beforeExecute();
    abstract void afterExecute();
}
