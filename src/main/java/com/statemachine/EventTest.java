package com.statemachine;

import com.statemachine.component.OrderStateMachineHolder;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/10/27
 */
@Component
public class EventTest implements CommandLineRunner {


    @Resource
    private OrderStateMachineHolder orderStateMachineHolder;



    @Override
    public void run(String... args) throws Exception {
        factoryTest();
    }


    private void factoryTest() throws Exception {
        new Thread(()->{
            Order order1 = new Order("1", States.S0);
            StateMachine stateMachine1 = orderStateMachineHolder.getStateMachine(order1);
            stateMachine1.sendEvent(MessageBuilder.withPayload(Events.E1).setHeader("order", order1).build());
        }).start();

        new Thread(()->{
            Order order2 = new Order("2", States.S1);
            StateMachine stateMachine2 = orderStateMachineHolder.getStateMachine(order2);
            stateMachine2.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order2).build());
        }).start();


        Order order3 = new Order("3", States.S0);
        StateMachine stateMachine3 = orderStateMachineHolder.getStateMachine(order3);
        stateMachine3.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order3).build());
    }

}
