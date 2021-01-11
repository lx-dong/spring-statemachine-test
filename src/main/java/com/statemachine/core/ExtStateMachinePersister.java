package com.statemachine.core;

import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.AbstractStateMachinePersister;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/11/5
 */
public class ExtStateMachinePersister<S, E, T extends EventObj<S,E>> extends AbstractStateMachinePersister<S, E, T> {
    /**
     * Instantiates a new abstract state machine persister.
     @param stateMachinePersist the state machine persist
     */
    public ExtStateMachinePersister(StateMachinePersist<S, E, T> stateMachinePersist) {
        super(stateMachinePersist);
    }
}
