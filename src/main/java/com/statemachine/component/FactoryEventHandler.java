package com.statemachine.component;

import com.alibaba.fastjson.JSON;
import com.statemachine.config.OrderOnTransition;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.*;

import java.util.Map;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/10/27
 */
@WithStateMachine(id = "factoryStateMachineId")
public class FactoryEventHandler {


    @OrderOnTransition(target = States.S1)
    public void t1(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<States, Events> stateMachine,
                   Message<String> message,
                   Exception e) {


        /*System.out.println(String.format(">>>>>>>>>>>> target=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S1",
                headers,
                extendedState,
                stateMachine,
                message,
                e));*/
        Order order = (Order)headers.get("order");
        System.out.println(String.format("=============== 【target=%s, currentState=%s, events=%s, headers=%s】",
                "S1",
                stateMachine.getState().getId(),
                message.getPayload(),
                JSON.toJSON(headers)));
        try {
            System.out.println("=================sleep10s==============");
            Thread.sleep(1000 * 10);
            System.out.println("=================wake up==============");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    @OrderOnTransition(target = States.S2)
    public void t2(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<States, Events> stateMachine,
                   Message<String> message,
                   Exception e) {
        /*System.out.println(String.format(">>>>>>>>>>>> target=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S2",
                headers,
                extendedState,
                stateMachine,
                message,
                e));*/
        Order order = (Order)headers.get("order");
        System.out.println(String.format("=============== 【target=%s, currentState=%s, events=%s, headers=%s】",
                "S2",
                stateMachine.getState().getId(),
                message.getPayload(),
                JSON.toJSON(headers)));
    }

    @OnTransition(source = "S0", target = "S2")
    public void t3(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<String, String> stateMachine,
                   Message<String> message,
                   Exception e) {
        /*System.out.println(String.format(">>>>>>>>>>>> target=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S0->S2",
                headers,
                extendedState,
                stateMachine,
                message,
                e));*/
        Order order = (Order)headers.get("order");
        System.out.println(String.format("=============== 【target=%s, currentState=%s, events=%s, headers=%s】",
                "S0->S2",
                stateMachine.getState().getId(),
                message.getPayload(),
                JSON.toJSON(headers)));
    }

    @OnStateChanged(target = "S1")
    public void c1(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<String, String> stateMachine,
                   Message<String> message,
                   Exception e) {
        System.out.println(String.format(">>>>>>>>>>>> OnStateChanged=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S1",
                headers,
                extendedState,
                stateMachine,
                message,
                e));
    }


    @OnStateChanged(target = "S2")
    public void changed2(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<String, String> stateMachine,
                   Message<String> message,
                   Exception e) {
        System.out.println(String.format(">>>>>>>>>>>> OnStateChanged=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S2",
                headers,
                extendedState,
                stateMachine,
                message,
                e));
    }

    @OnStateEntry(target = "S2")
    public void entry2(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<String, String> stateMachine,
                   Message<String> message,
                   Exception e) {
        System.out.println(String.format(">>>>>>>>>>>> OnStateEntry=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S2",
                headers,
                extendedState,
                stateMachine,
                message,
                e));
    }

    @OnStateExit(target = "S2")
    public void exit2(@EventHeaders Map<String, Object> headers,
                   ExtendedState extendedState,
                   StateMachine<String, String> stateMachine,
                   Message<String> message,
                   Exception e) {
        System.out.println(String.format(">>>>>>>>>>>> OnStateExit=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                "S2",
                headers,
                extendedState,
                stateMachine,
                message,
                e));
    }


    @OnEventNotAccepted
    public void notAccepted2(@EventHeaders Map<String, Object> headers,
                      ExtendedState extendedState,
                      StateMachine<String, String> stateMachine,
                      Message<String> message,
                      Exception e) {
        System.out.println(String.format(">>>>>>>>>>>> OnEventNotAccepted=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
                message.getPayload(),
                headers,
                extendedState,
                stateMachine,
                message,
                e));
    }
}
