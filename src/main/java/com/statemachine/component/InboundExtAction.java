package com.statemachine.component;

import com.statemachine.core.ExtAction;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.statemachine.StateContext;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/11/6
 */
public abstract class InboundExtAction extends ExtAction<States, Events,Order, Order> {


    @Override
    public Order getEntity(String bizNo) {
        return null;
    }

    @Override
    public States getCurrState(Order order) {
        return null;
    }
}
