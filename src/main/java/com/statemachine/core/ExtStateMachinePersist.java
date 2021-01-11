package com.statemachine.core;

import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/11/5
 */
public class ExtStateMachinePersist<S, E, T extends EventObj<S, E>> implements StateMachinePersist<S, E, T> {
    @Override
    public void write(StateMachineContext<S, E> context, T contextObj) throws Exception {

    }

    @Override
    public StateMachineContext<S, E> read(T contextObj) throws Exception {
        StateMachineContext<S, E> result =new DefaultStateMachineContext<S, E>(contextObj.getState(), null, null, null, null, contextObj.getBizNo());
        return result;
    }
}
