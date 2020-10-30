package com.statemachine.component;

import com.statemachine.config.factory.FactoryConfig;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/10/30
 */
@Component
public class OrderStateMachineHolder {
    @Resource(name = "stateMachineFactory")
    StateMachineFactory<States, Events> stateMachineFactory;

    @Resource(name = "factoryPersister")
    private StateMachinePersister<States, Events, Order> factoryPersister;

    public StateMachine getStateMachine(Order order){
        StateMachine machine =  stateMachineFactory.getStateMachine(FactoryConfig.orderStateMachineId);
        machine.start();
        try {
            factoryPersister.restore(machine,order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return machine;
    }
}
