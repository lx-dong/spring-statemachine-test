package com.statemachine.core;

import lombok.Builder;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/11/5
 */
@Builder
public class EventObj<S, E> {
    public static String BIZ_NO_NAME = "bizNo";
    public static String PARAM_NAME = "param";

    private S state;
    private E event;
    private String bizNo;
    private Object param;


    public EventObj(S state, E event, String bizNo, Object param) {
        this.state = state;
        this.event = event;
        this.bizNo = bizNo;
        this.param = param;
    }

    public S getState() {
        return state;
    }

    public void setState(S state) {
        this.state = state;
    }

    public E getEvent() {
        return event;
    }

    public void setEvent(E event) {
        this.event = event;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
