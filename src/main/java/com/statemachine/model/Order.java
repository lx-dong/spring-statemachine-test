package com.statemachine.model;

import com.statemachine.enums.States;
import lombok.AllArgsConstructor;
import lombok.Data;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/10/27
 */
@Data
@AllArgsConstructor
// Table
public class Order {
    private String orderNo;
    private States state;
}
