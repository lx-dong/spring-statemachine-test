package com.statemachine.component;

import com.alibaba.fastjson.JSON;
import com.statemachine.core.ExtAction;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/11/2
 */
@Slf4j
@Component
public class Action3 extends ExtAction<States, Events, Order> {

    @Override
    public void handle(StateContext<States, Events> context) {
        Order order = getParam();
        System.out.println(">>>>>>>>>>>>>>>>>" + JSON.toJSONString(order));
        try {
            Order order2 = new Order("2", States.S1);
            if (1 == 1) {
                throw new RuntimeException("action3");
            }
        } catch (Exception e) {
            log.error("================= error E3", e);
            context.getStateMachine().setStateMachineError(e);
            throw e;
        }
    }

    @Override
    public boolean needLock() {
        return true;
    }

    @Override
    public String getRedisKey(StateContext<States, Events> context) {
        return null;
    }

}
