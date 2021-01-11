package com.statemachine.core;

import com.statemachine.core.ExtStateMachine;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.AbstractStateMachine;

import java.util.UUID;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/11/4
 */
public class ExtStateMachineFactory<S, E> implements StateMachineFactory<S, E>{
    private StateMachineFactory<S, E> stateMachineFactory;

    public ExtStateMachineFactory(StateMachineFactory<S, E> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }


    @Override
    public ExtStateMachine<S, E> getStateMachine() {
        StateMachine<S, E> stateMachine = stateMachineFactory.getStateMachine();
        return new ExtStateMachine<S,E>((AbstractStateMachine<S, E>) stateMachineFactory.getStateMachine());
    }

    @Override
    public ExtStateMachine<S, E> getStateMachine(String machineId) {
        return new ExtStateMachine<>((AbstractStateMachine<S, E>) stateMachineFactory.getStateMachine(machineId));
    }

    @Override
    public ExtStateMachine<S, E> getStateMachine(UUID uuid) {
        return new ExtStateMachine<>((AbstractStateMachine<S, E>)stateMachineFactory.getStateMachine(uuid));
    }

}
