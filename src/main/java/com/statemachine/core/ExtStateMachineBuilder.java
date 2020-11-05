package com.statemachine.core;

import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/10/30
 */
public class ExtStateMachineBuilder<S,E> {
    private ExtStateMachineFactory<S, E> extStateMachineFactory;
    private StateMachinePersister<S, E, EventObj<S, E>>factoryPersister;

    public ExtStateMachineBuilder(ExtStateMachineFactory<S, E> extStateMachineFactory, StateMachinePersister<S, E, EventObj<S, E>> factoryPersister) {
        this.extStateMachineFactory = extStateMachineFactory;
        this.factoryPersister = factoryPersister;
    }

    public ExtStateMachine build(EventObj<S, E> eventObj){
        ExtStateMachine machine =  extStateMachineFactory.getStateMachine(eventObj.getBizNo());
        try {
            factoryPersister.restore(machine, eventObj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return machine;
    }
}
