package com.statemachine.core;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccessor;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.AbstractStateMachine;
import org.springframework.statemachine.transition.Transition;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/11/4
 */
public class ExtStateMachine<S, E> implements StateMachine<S, E> {
    private AbstractStateMachine stateMachine;

    public ExtStateMachine(AbstractStateMachine<S, E> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public Exception getException() {
        Exception exception = null;
        try {
            Field field = AbstractStateMachine.class.getDeclaredField("currentError");
            field.setAccessible(true);
            Object ex = field.get(stateMachine);
            if (ex != null) {
                exception = (Exception)ex;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return exception;
    }

    @Override
    public State<S, E> getInitialState() {
        return stateMachine.getInitialState();
    }

    @Override
    public ExtendedState getExtendedState() {
        return stateMachine.getExtendedState();
    }

    @Override
    public StateMachineAccessor<S, E> getStateMachineAccessor() {
        return stateMachine.getStateMachineAccessor();
    }

    @Override
    public void setStateMachineError(Exception exception) {
        stateMachine.setStateMachineError(exception);
    }

    @Override
    public boolean hasStateMachineError() {
        return stateMachine.hasStateMachineError();
    }

    @Override
    public UUID getUuid() {
        return stateMachine.getUuid();
    }

    @Override
    public String getId() {
        return stateMachine.getId();
    }

    @Override
    public void start() {
        stateMachine.start();
    }

    @Override
    public void stop() {
        stateMachine.stop();
    }

    @Override
    @Deprecated
    public boolean sendEvent(Message<E> event) {
        throw new UnsupportedOperationException("unsupported");
    }

    @Override
    @Deprecated
    public boolean sendEvent(E event) {
        throw new UnsupportedOperationException("unsupported");
    }


    public boolean sendEvent(EventObj eventObj) {
        boolean result = stateMachine.sendEvent(MessageBuilder
                .withPayload(eventObj.getEvent())
                .setHeader(EventObj.PARAM_NAME, eventObj.getParam())
                .setHeader(EventObj.BIZ_NO_NAME, eventObj.getBizNo())
                .build());
        Exception ex = getException();
        if (ex != null) {
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }

    @Override
    public State<S, E> getState() {
        return stateMachine.getState();
    }

    @Override
    public Collection<State<S, E>> getStates() {
        return stateMachine.getStates();
    }

    @Override
    public Collection<Transition<S, E>> getTransitions() {
        return stateMachine.getTransitions();
    }

    @Override
    public boolean isComplete() {
        return stateMachine.isComplete();
    }

    @Override
    public void addStateListener(StateMachineListener<S, E> listener) {
        stateMachine.addStateListener(listener);
    }

    @Override
    public void removeStateListener(StateMachineListener<S, E> listener) {
        stateMachine.removeStateListener(listener);
    }
}
