package com.zcf.world.controller.api;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.zcf.world.common.wx.WeChatPAyHelp;
import com.zcf.world.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuan
 * @date 2018/11/21 0021
 */
@RestController
public class TestController {
    @Autowired
    private WeChatPAyHelp help;

    @PostMapping(value="WeChatPay", produces="application/xml;charset=UTF-8")
    public void Test() {
        String order=help.createOrder();
    }

    @PostMapping(value="TestDemo")
    public String Tests(@Validated Test r) {
        System.err.println(r);
        return "success";
    }

    //方圆支付   微信  返回参数
    public static void main(String[] args) {
        JSONObject paramMap=JSONUtil.createObj();
        paramMap.put("userId", "wx4fd7341078827aea");
        paramMap.put("amount", "100");
        paramMap.put("bizType", "WECHATPAY");
        paramMap.put("merchant_id", "ON00004397");
        paramMap.put("operator_id", "0000015951");
        paramMap.put("call_back_url", "www.baidu.com");
        String result2=HttpRequest.post("https://mppay.ottpay.com/inAppPay")
                .body(paramMap)
                .execute().body();
        Console.log(result2);
    }
}
