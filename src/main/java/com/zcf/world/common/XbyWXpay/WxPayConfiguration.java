package com.zcf.world.common.XbyWXpay;

import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.wx.api.WxPayConfigStorage;
import com.egzosn.pay.wx.api.WxPayService;

/**
 * 微信配置   XBY
 */
public class WxPayConfiguration {

    public static PayService Configuration(){
        WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
//        wxPayConfigStorage.setMchId("1509857021");
        wxPayConfigStorage.setMchId("1533226301");
//        wxPayConfigStorage.setAppid("wx7c8a9a2397247799");
        wxPayConfigStorage.setAppid("wxc96ca0989f7b82c2");
//        wxPayConfigStorage.setSecretKey("ZaichufaZaichufaZaichufa20190302");
        wxPayConfigStorage.setSecretKey("yuleshangchengyuleshangchengyule");
        wxPayConfigStorage.setNotifyUrl("http://xby0612.free.idcfengye.com/wxPay/rechargeMember");
        wxPayConfigStorage.setReturnUrl("http://www.baidu.com");
        wxPayConfigStorage.setSignType("MD5");
        wxPayConfigStorage.setInputCharset("utf-8");
        PayService service =  new WxPayService(wxPayConfigStorage);
        return service;
    }

    public static PayService BettingRecordConfiguration(){
        WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
//        wxPayConfigStorage.setMchId("1509857021");
        wxPayConfigStorage.setMchId("1533226301");
//        wxPayConfigStorage.setAppid("wx7c8a9a2397247799");
        wxPayConfigStorage.setAppid("wxc96ca0989f7b82c2");
//        wxPayConfigStorage.setSecretKey("ZaichufaZaichufaZaichufa20190302");
        wxPayConfigStorage.setSecretKey("yuleshangchengyuleshangchengyule");
        wxPayConfigStorage.setNotifyUrl("http://xby0612.free.idcfengye.com/bettingRecordRechargeMember");
        wxPayConfigStorage.setReturnUrl("http://www.baidu.com");
        wxPayConfigStorage.setSignType("MD5");
        wxPayConfigStorage.setInputCharset("utf-8");
        PayService service =  new WxPayService(wxPayConfigStorage);
        return service;
    }

    public static PayService bugShoppingCartWX(){
        WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
//        wxPayConfigStorage.setMchId("1509857021");
        wxPayConfigStorage.setMchId("1533226301");
//        wxPayConfigStorage.setAppid("wx7c8a9a2397247799");
        wxPayConfigStorage.setAppid("wxc96ca0989f7b82c2");
//        wxPayConfigStorage.setSecretKey("ZaichufaZaichufaZaichufa20190302");
        wxPayConfigStorage.setSecretKey("yuleshangchengyuleshangchengyule");
        wxPayConfigStorage.setNotifyUrl("http://xby0612.free.idcfengye.com/shoppingCart/HDBugShoppingCartWX");
        wxPayConfigStorage.setReturnUrl("http://www.baidu.com");
        wxPayConfigStorage.setSignType("MD5");
        wxPayConfigStorage.setInputCharset("utf-8");
        PayService service =  new WxPayService(wxPayConfigStorage);
        return service;
    }

    public static PayService WXRecharge(){
        WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
//        wxPayConfigStorage.setMchId("1509857021");
        wxPayConfigStorage.setMchId("1533226301");
//        wxPayConfigStorage.setAppid("wx7c8a9a2397247799");
        wxPayConfigStorage.setAppid("wxc96ca0989f7b82c2");
//        wxPayConfigStorage.setSecretKey("ZaichufaZaichufaZaichufa20190302");
        wxPayConfigStorage.setSecretKey("yuleshangchengyuleshangchengyule");
        wxPayConfigStorage.setNotifyUrl("http://xby0612.free.idcfengye.com/wxPay/rechargeWXCZMoney");
        wxPayConfigStorage.setReturnUrl("http://www.baidu.com");
        wxPayConfigStorage.setSignType("MD5");
        wxPayConfigStorage.setInputCharset("utf-8");
        PayService service =  new WxPayService(wxPayConfigStorage);
        return service;
    }

}
