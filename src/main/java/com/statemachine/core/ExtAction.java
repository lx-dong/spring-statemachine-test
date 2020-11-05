package com.statemachine.core;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.util.StringUtils;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/11/4
 */
public abstract class ExtAction<S,E,P> implements Action<S,E> {
    private StateContext<S, E> stateContext;

    @Override
    public void execute(StateContext<S, E> context) {
        this.stateContext = context;
        try {
            if (needLock()) {
                //TODO do lock
//                if (!tryLock()) {
//                    throw new RuntimeException("lock fail");
//                }
            }
            handle(context);
        } catch (Exception e) {
            StateMachine stateMachine = context.getStateMachine();
            stateMachine.setStateMachineError(e);
            throw e;
        } finally {
            //TODO release lock
        }
    }

    public abstract void handle(StateContext<S, E> context);

    private <T> T getHeader(String key, Class<T> clazz) {
        return stateContext.getMessageHeaders().get(key, clazz);
    }

    public String getBizNo() {
        return getHeader(EventObj.BIZ_NO_NAME, String.class);
    }

    public P getParam() {
        return (P)stateContext.getMessageHeaders().get(EventObj.PARAM_NAME);
    }


    public abstract boolean needLock();

    public  String getRedisKey(StateContext<S, E> context){
        if (StringUtils.isEmpty(getBizNo())) {
            throw new RuntimeException("getRedisKey error: bizNo is empty");
        }
        return "action-lock-" + getBizNo();
    }
}
