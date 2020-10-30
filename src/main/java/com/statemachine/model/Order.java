package com.statemachine.model;

import com.statemachine.enums.States;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 上海识装信息科技有限公司
 *
 * @author DongLingXu
 * @description
 * @email donglingxu@theduapp.com
 * @date 2020/10/27
 */
@Data
@AllArgsConstructor
// Table
public class Order {
    private String orderNo;
    private States state;
}
