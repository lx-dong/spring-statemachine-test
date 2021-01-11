package com.statemachine.config.factory;

import com.statemachine.component.Action1;
import com.statemachine.component.Action2;
import com.statemachine.component.Action3;
import com.statemachine.core.ExtStateMachineFactory;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author DongLingXu
 * @description
 * @email
 * @date 2020/10/29
 */
@Configuration
//@EnableStateMachine(name = "ddd")
@Scope("prototype")
@EnableStateMachineFactory(name = "stateMachineFactory")
public class FactoryConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    /**订单状态机ID*/
    public static final String orderStateMachineId = "factoryStateMachineId";

    @Resource(name = "action1")
    Action1 action1;
    @Resource(name = "action2")
    Action2 action2;
    @Resource(name = "action3")
    Action3 action3;

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.S0)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        System.out.println("============config-init==============");
        transitions
                .withExternal()
                .source(States.S0).target(States.S1).event(Events.E1)
                .and()
                .withExternal()
                .source(States.S1).target(States.S2).event(Events.E2)
                .and()
                .withExternal()
                .source(States.S0).target(States.S2).event(Events.E2)
                .and()
                .withExternal()
                .source(States.S2).target(States.S1).event(Events.E2).action(action3)
                .and()
                .withExternal()
                .source(States.S1).target(States.S3).event(Events.E3).action(action2)
                ;
    }

    @Bean(name = "factoryPersister")
    public StateMachinePersister<States, Events, Order> factoryPersister(@Autowired @Qualifier("factoryStateMachinePersist") FactoryStateMachinePersist factoryStateMachinePersist){
        return new DefaultStateMachinePersister(factoryStateMachinePersist);
    }

    @Bean(name = "extStateMachineFactory")
    public ExtStateMachineFactory extStateMachineFactory(@Autowired @Qualifier("stateMachineFactory") StateMachineFactory stateMachineFactory) {
        return new ExtStateMachineFactory(stateMachineFactory);
    }
}
