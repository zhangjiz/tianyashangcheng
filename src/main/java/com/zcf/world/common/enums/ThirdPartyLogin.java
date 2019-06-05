package com.zcf.world.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yuan
 * @date 2019/2/18 0018
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ThirdPartyLogin {
    /**
     * 微信
     */
    WE_CHAT(1, "wxOpenId"),
    /**
     * QQ
     */
    QQ(2, "qqOpenId"),
    /**
     * 支付宝
     */
    ALI_PAY(3, "aliOpenId"),
    ;
    private Integer code;
    private String name;
}
