//package com.statemachine.component;
//
//import com.alibaba.fastjson.JSON;
//import com.statemachine.config.OrderOnTransition;
//import com.statemachine.enums.Events;
//import com.statemachine.enums.States;
//import org.springframework.messaging.Message;
//import org.springframework.statemachine.ExtendedState;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.annotation.*;
//import org.springframework.statemachine.persist.DefaultStateMachinePersister;
//import org.springframework.statemachine.persist.StateMachinePersister;
//import org.springframework.statemachine.state.State;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
///**
// *
// *
// * @author DongLingXu
// * @description
// * @email
// * @date 2020/10/27
// */
//@WithStateMachine(id = "poolStateMachineId")
//public class PoolEventHandler {
//
//
//    @OrderOnTransition(target = States.S1)
//    public void t1(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<States, Events> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//
//
//        System.out.println(String.format(">>>>>>>>>>>> target=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S1",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//        System.out.println(String.format("=============== target=%s, currentState=%s, events=%s","S1", stateMachine.getState().getId(),message.getPayload()));
//    }
//
//
//    @OrderOnTransition(target = States.S2)
//    public void t2(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<States, Events> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> target=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S2",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//        System.out.println(String.format("=============== target=%s, currentState=%s","S2", stateMachine.getState().getId()));
//    }
//
//    @OnTransition(source = "S0", target = "S2")
//    public void t3(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<String, String> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> target=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S0->S2",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//    }
//
//    @OnStateChanged(target = "S1")
//    public void c1(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<String, String> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> OnStateChanged=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S1",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//    }
//
//
//    @OnStateChanged(target = "S2")
//    public void changed2(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<String, String> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> OnStateChanged=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S2",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//    }
//
//    @OnStateEntry(target = "S2")
//    public void entry2(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<String, String> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> OnStateEntry=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S2",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//    }
//
//    @OnStateExit(target = "S2")
//    public void exit2(@EventHeaders Map<String, Object> headers,
//                   ExtendedState extendedState,
//                   StateMachine<String, String> stateMachine,
//                   Message<String> message,
//                   Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> OnStateExit=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                "S2",
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//    }
//
//
//    @OnEventNotAccepted
//    public void notAccepted2(@EventHeaders Map<String, Object> headers,
//                      ExtendedState extendedState,
//                      StateMachine<String, String> stateMachine,
//                      Message<String> message,
//                      Exception e) {
//        System.out.println(String.format(">>>>>>>>>>>> OnEventNotAccepted=%s, headers=%s, extendedState=%s, stateMachine=%s, message=%s, e=%s",
//                message.getPayload(),
//                headers,
//                extendedState,
//                stateMachine,
//                message,
//                e));
//    }
//}
