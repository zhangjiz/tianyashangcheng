package com.zcf.world.common.WXpay;

public class ConstantUtil {
	/**
	 * 微信开发平台应用ID
	 */
	public static final String APP_ID = "wx7c8a9a2397247799";
	/**
	 * 应用对应的AppSecret
	 */
	public static final String APP_SECRET = "1cb0cc24c55389076d76be2e0c53c3b1";
	/**
	 * APP_KEY 商户平台---api安全---密钥
	 */
	public static final String APP_KEY = "ZaichufaZaichufaZaichufa20190302";
	/**
	 * 微信支付商户号
	 */
	public static final String MCH_ID = "1509857021";
	/**cp
	 * 商品描述
	 */
	public static final String BODY = "账号充值";
	/**
	 * 商户号对应的密钥 也是 商户平台---api安全---密钥 同上面那个APP_KEY
	 */
	public static final String PARTNER_key = "ZaichufaZaichufaZaichufa20190302";

	/**
	 * 商户id 同微信支付商户号
	 */
	public static final String PARTNER_ID = "1509857021";
	/**
	 * 常量固定值
	 */
	public static final String GRANT_TYPE = "client_credential";
	/**
	 * 获取预支付id的接口url
	 */
	public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 微信服务器回调通知url
	 */
	//服务器
	public static String NOTIFY_URL = "http://baby.viphk.ngrok.org/baby/weChat/rechargeMember/notify";

}
