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
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/11/2
 */
@Slf4j
@Component
public class Action4 extends ExtAction<States, Events, Order> {

    @Override
    public void handle(StateContext<States, Events> context) {
        String id = getBizNo();
        Order order = getParam();
        System.out.println(">>>>>>>>>>>>>>>>>" + JSON.toJSONString(order));
        Order order2 = new Order("2", States.S1);
        if (1 == 1) {
            throw new RuntimeException("action4");
        }
    }

    @Override
    public boolean needLock() {
        return true;
    }


}
