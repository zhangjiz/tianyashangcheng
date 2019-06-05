package com.zcf.world.common.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

public class Query {
	// 配置初始化信息参数
	public static DefaultAlipayClient alipayclient() {
		DefaultAlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				ALiPayConfig.appid, ALiPayConfig.private_key, "json", ALiPayConfig.input_charset,
				ALiPayConfig.ali_public_key, "RSA2");
		return alipayClient;
	}

	public static boolean  QueryStatus() throws AlipayApiException {
		DefaultAlipayClient alipayclient = alipayclient();
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\"\"," +
				"\"trade_no\":\"2018071621001004470558427348\"" +
				"  }");
		AlipayTradeQueryResponse response = alipayclient.execute(request);
		if (response.isSuccess()) {
			System.err.println("调用成功");
			return true;
		} else {
			System.err.println("调用失败");
			return false;
		}
	}
	public static void main(String[] args) throws AlipayApiException {
		refundMoney(153793170434003L);
	}
	
	//153793170434003
	public static void refundMoney(Long id) throws AlipayApiException{														
		DefaultAlipayClient alipayClient = alipayclient();
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
		"\"out_trade_no\":\""+id+"\"," +
		"\"refund_amount\":\""+0.01+"\"," +
		"\"refund_reason\":\"正常退款\"" +
		"  }");
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("退款成功");
		} else {
			System.out.println("退款失败");
		}
	}
}
