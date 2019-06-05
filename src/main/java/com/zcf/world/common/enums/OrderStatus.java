package com.zcf.world.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yuan
 * @date 2019/3/12 0012
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatus {
    /**
     * 等待付款
     */
    WAITING_FOR_PAYMENT(1),
    /**
     * 等待发货
     */
    WAITING_FOR_SHIPMENT(2),
    /**
     * 等待收货
     */
    WAITING_FOR_RECEIVING(3),
    /**
     * 等待评价
     */
    WAITING_FOR_EVALUATION(4),
    /**
     * 已经完成
     */
    ALREADY_COMPLETED(5);
    private Integer status;
}
