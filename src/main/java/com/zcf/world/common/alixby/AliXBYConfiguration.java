package com.zcf.world.common.alixby;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.bean.PayOrder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static com.zcf.world.common.alipay.ALiPayConfig.*;

/**
 *   支付宝支付  配置
 */
public class AliXBYConfiguration {

    public static AliPayService AliConfiguration(){

        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
        aliPayConfigStorage.setPid(partner);
        aliPayConfigStorage.setAppid(appid);
        aliPayConfigStorage.setKeyPublic(ali_public_key);
        aliPayConfigStorage.setKeyPrivate(private_key);
        aliPayConfigStorage.setNotifyUrl(notify_url); // 回調  回调
        aliPayConfigStorage.setReturnUrl(notify_url);
        aliPayConfigStorage.setSignType(pay_sign_type);
        aliPayConfigStorage.setInputCharset(input_charset);
        AliPayService service = new AliPayService(aliPayConfigStorage);
        return service;
    }



    public static void main(String[] args) {
        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
        aliPayConfigStorage.setPid(partner);
        aliPayConfigStorage.setAppid(appid);
        aliPayConfigStorage.setKeyPublic(ali_public_key);
        aliPayConfigStorage.setKeyPrivate(private_key);
        aliPayConfigStorage.setNotifyUrl(notify_url); // 回調  回调
        aliPayConfigStorage.setSignType(pay_sign_type);
        aliPayConfigStorage.setInputCharset(input_charset);
        AliPayService service = new AliPayService(aliPayConfigStorage);

        //支付订单基础信息
        PayOrder payOrder = new PayOrder("订单ew", "asd摘要",  new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""));

        /*-----------APP-------------------*/
        payOrder.setTransactionType(AliTransactionType.APP);
        //获取APP支付所需的信息组，直接给app端就可使用
        Map appOrderInfo = service.orderInfo(payOrder);
        /*-----------/APP-------------------*/

        System.out.println(appOrderInfo);

    }
}
