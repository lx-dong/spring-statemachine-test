package com.statemachine.config.factory;

import com.alibaba.fastjson.JSON;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/10/27
 */
@Component(value = "factoryStateMachinePersist")
public class FactoryStateMachinePersist implements StateMachinePersist<States, Events, Order> {

    @Override
    public void write(StateMachineContext<States, Events> context, Order order) throws Exception {
        System.out.println(String.format(">>>>>>>>>>>Persist.write() context=%s, order=%s", context, JSON.toJSON(order)));
    }

    @Override
    public StateMachineContext<States, Events> read(Order order) throws Exception {
        System.out.println(String.format(">>>>>>>>>>>Persist.read() order=%s", JSON.toJSON(order)));
        StateMachineContext<States, Events> result =new DefaultStateMachineContext<States, Events>(order.getState(), null, null, null, null, FactoryConfig.orderStateMachineId);
        return result;
    }
}
