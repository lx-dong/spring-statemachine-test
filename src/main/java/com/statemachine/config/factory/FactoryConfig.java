package com.statemachine.config.factory;

import com.alibaba.fastjson.JSON;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import com.statemachine.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/10/29
 */
@Configuration
//@EnableStateMachine(name = "ddd")
@Scope("prototype")
@EnableStateMachineFactory(name = "stateMachineFactory")
public class FactoryConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    /**订单状态机ID*/
    public static final String orderStateMachineId = "factoryStateMachineId";
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
        transitions
                .withExternal()
                .source(States.S0).target(States.S1).event(Events.E1)
                .and()
                .withExternal()
                .source(States.S1).target(States.S2).event(Events.E2)
                .and()
                .withExternal()
                .source(States.S0).target(States.S2).event(Events.E2);
    }

    @Bean
    public StateMachinePersister<States, Events, Order> factoryPersister(@Autowired @Qualifier("factoryStateMachinePersist") FactoryStateMachinePersist factoryStateMachinePersist){
        return new DefaultStateMachinePersister(factoryStateMachinePersist);
    }
}
