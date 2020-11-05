package com.statemachine.config;

import com.statemachine.enums.Events;
import com.statemachine.enums.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/11/2
 */
public abstract class AbstractAction<S,E> implements Action<S,E> {

    public abstract void onException(StateContext<S, E> context);

    public Action<S, E> exceptionAction() {
        return context -> onException(context);
    }

    public <T> T getHeader(StateContext<S, E> context, String key, Class<T> clazz) {
        return context.getMessageHeaders().get(key, clazz);
    }


}
