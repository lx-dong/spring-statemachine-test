package com.statemachine.config.factory2;

import com.statemachine.component.Action1;
import com.statemachine.component.Action2;
import com.statemachine.component.Action3;
import com.statemachine.component.Action4;
import com.statemachine.core.EventObj;
import com.statemachine.core.ExtStateMachineBuilder;
import com.statemachine.core.ExtStateMachineFactory;
import com.statemachine.core.ExtStateMachinePersist;
import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import javax.annotation.Resource;
import java.util.EnumSet;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/10/29
 */
@Configuration
@EnableStateMachineFactory(name = "stateMachineFactory2")
public class FactoryConfig2 extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Resource(name = "action1")
    Action1 action1;
    @Resource(name = "action2")
    Action2 action2;
    @Resource(name = "action3")
    Action3 action3;
    @Resource(name = "action4")
    Action4 action4;

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
                .source(States.S2).target(States.S1).event(Events.E2).action(action4)
                .and()
                .withExternal()
                .source(States.S1).target(States.S3).event(Events.E3).action(action2)
                ;
    }

    @Bean(name = "factoryPersister2")
    public StateMachinePersister<States, Events, EventObj<States, Events>> factoryPersister(){
        return new DefaultStateMachinePersister(new ExtStateMachinePersist<States, Events, EventObj<States, Events>>());
    }

    @Bean(name = "extStateMachineFactory2")
    public ExtStateMachineFactory<States, Events> extStateMachineFactory(@Autowired @Qualifier("stateMachineFactory2") StateMachineFactory stateMachineFactory) {
        return new ExtStateMachineFactory(stateMachineFactory);
    }

    @Bean(name = "extStateMachineBuilder")
    public ExtStateMachineBuilder<States, Events> extStateMachineBuilder(@Autowired @Qualifier("extStateMachineFactory2") ExtStateMachineFactory extStateMachineFactory) {
        return new ExtStateMachineBuilder(extStateMachineFactory, factoryPersister());
    }

}
