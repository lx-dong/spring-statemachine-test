//package com.statemachine.config.pool;
//
//import com.statemachine.config.factory.FactoryStateMachinePersist;
//import com.statemachine.enums.Events;
//import com.statemachine.enums.States;
//import com.statemachine.model.Order;
//import org.springframework.aop.framework.ProxyFactoryBean;
//import org.springframework.aop.target.CommonsPool2TargetSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.config.StateMachineBuilder;
//import org.springframework.statemachine.persist.DefaultStateMachinePersister;
//import org.springframework.statemachine.persist.StateMachinePersister;
//
//import java.util.EnumSet;
//
///**
// * 上海识装信息科技有限公司
// *
// * @author DongLingXu
// * @description
// * @email donglingxu@theduapp.com
// * @date 2020/10/28
// */
//@Configuration
//public class PoolConfig {
//
//    /**订单状态机ID*/
//    public static final String stateMachineId = "poolStateMachineId";
//
//
//    @Bean(name = "poolMachinePersister")
//    public StateMachinePersister<States, Events, Order> poolMachinePersister(@Autowired FactoryStateMachinePersist factoryStateMachinePersist) {
//        return new DefaultStateMachinePersister<>(factoryStateMachinePersist);
//    }
//
//
//
//    @Bean(name = "poolStateMachineTarget")
//    @Scope(scopeName="prototype")
//    public StateMachine<States, Events> poolStateMachineTarget() throws Exception {
//        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.<States, Events>builder();
//
//        builder.configureConfiguration()
//                .withConfiguration()
//                .autoStartup(true);
//
//        builder.configureStates().withStates()
//                .initial(States.S0)
//                .states(EnumSet.allOf(States.class));
//
//        builder.configureTransitions()
//                .withExternal()
//                .source(States.S0).target(States.S1).event(Events.E1)
//                .and()
//                .withExternal()
//                .source(States.S1).target(States.S2).event(Events.E2)
//                .and()
//                .withExternal()
//                .source(States.S0).target(States.S2).event(Events.E2);
//
//        return builder.build();
//    }
//
//    @Bean
//    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public ProxyFactoryBean poolStateMachine() {
//        ProxyFactoryBean pfb = new ProxyFactoryBean();
//        pfb.setTargetSource(poolTargetSource());
//        return pfb;
//    }
//
//
//    @Bean
//    public CommonsPool2TargetSource poolTargetSource() {
//        CommonsPool2TargetSource pool = new CommonsPool2TargetSource();
//        pool.setMaxSize(3);
//        pool.setTargetBeanName("poolStateMachineTarget");
//        return pool;
//    }
//}
//
