package com.statemachine;

import com.statemachine.component.OrderStateMachineBuilder;
import com.statemachine.core.ExtStateMachine;
import com.statemachine.core.ExtStateMachineBuilder;
import com.statemachine.core.EventObj;
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
    private OrderStateMachineBuilder orderStateMachineBuilder;
    @Resource
    private ExtStateMachineBuilder extStateMachineBuilder;



    @Override
    public void run(String... args) throws Exception {
        //factoryTest();
        //loopTest();
        //actionTest();
        factory2Test();
    }

    private void factory2Test() {
        for (int i = 0; i < 10; i ++) {
            Order order2 = new Order("2", States.S2);

            EventObj<States, Events> eventObj = new EventObj(order2.getState(), Events.E2, order2.getOrderNo(), order2);
            ExtStateMachine stateMachine2 = extStateMachineBuilder.build(eventObj);

            try {
                //stateMachine2.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order2).build());
                stateMachine2.sendEvent(eventObj);
            } catch (Exception e) {
                System.out.println("======== error" + e.getMessage());
            }
            boolean errorFlag = stateMachine2.hasStateMachineError();

            System.out.println("========= errorFlag=" + errorFlag);
        }
    }

    private void actionTest() {
        for (int i = 0; i < 10; i ++) {
            Order order2 = new Order("2", States.S2);
            StateMachine stateMachine2 = orderStateMachineBuilder.buildExt(order2);
            try {
                stateMachine2.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order2).build());
            } catch (Exception e) {
                System.out.println("======== error" + e.getMessage());
            }
            boolean errorFlag = stateMachine2.hasStateMachineError();

            System.out.println("========= errorFlag=" + errorFlag);
        }
    }

    private void loopTest() {
        int i = 1;
        for (;;) {
            Order order2 = new Order("2", States.S1);
            StateMachine stateMachine2 = orderStateMachineBuilder.build(order2);
            stateMachine2.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order2).build());
            i ++;
            if (i % 10000 == 0) {
                try {
                    System.out.println("=================i=" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void factoryTest() throws Exception {
        new Thread(()->{
            Order order1 = new Order("1", States.S0);
            StateMachine stateMachine1 = orderStateMachineBuilder.build(order1);
            stateMachine1.sendEvent(MessageBuilder.withPayload(Events.E1).setHeader("order", order1).build());
        }).start();

        new Thread(()->{
            Order order2 = new Order("2", States.S1);
            StateMachine stateMachine2 = orderStateMachineBuilder.build(order2);
            stateMachine2.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order2).build());
        }).start();


        Order order3 = new Order("3", States.S0);
        StateMachine stateMachine3 = orderStateMachineBuilder.build(order3);
        stateMachine3.sendEvent(MessageBuilder.withPayload(Events.E2).setHeader("order", order3).build());
    }

}
