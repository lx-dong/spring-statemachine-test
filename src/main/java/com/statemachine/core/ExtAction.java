package com.statemachine.core;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.util.StringUtils;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/11/4
 */
public abstract class ExtAction<S,E,P,Et> implements Action<S,E> {
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
            Et entity = getEntity(getBizNo());
            if (!context.getMessage().getPayload().equals(getCurrState(entity))) {
                throw new RuntimeException("状态不一致");
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

    public abstract Et getEntity(String bizNo);

    public abstract S getCurrState(Et entity);

    public  boolean needLock(){
        return true;
    }

    public  String getRedisKey(StateContext<S, E> context){
        if (StringUtils.isEmpty(getBizNo())) {
            throw new RuntimeException("getRedisKey error: bizNo is empty");
        }
        return "action-lock-" + getBizNo();
    }
}
