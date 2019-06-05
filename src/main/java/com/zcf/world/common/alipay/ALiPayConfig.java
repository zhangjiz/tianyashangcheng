package com.zcf.world.common.alipay;

/**
 * 支付宝支付所需的必要参数 理想情况下只需配置此处？？
 */
public class ALiPayConfig {
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088231024556844";

	// appid
	public static String appid = "2018071960697512";

	// 商户支付宝账号
	public static String seller_email = "xuzhouzaichufa@126.comm";

	// 商户真实姓名
	public static String account_name = "徐州再出发网络科技有限公司";

	// URL
	public static String url = "https://openapi.alipay.com/gateway.do";
	public static String timeoutExpress = "1000";

	// 商户的私钥RSA
	public static String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCJBZ5f513t1fsJbWGoxsyPDMuye8KkXyQ1WcCBCyAT7Kin3SrULK/01AD/M12BxHXgh364Me1m7iON8YTepJeNAixMeIxrjAelXhudjccYnvLUC7izr6/EOin3Dh30OS2Mrp4JUdF8OOd9/93fqwrxuvTV0nkDkNc0GpJyBU9MU8rypVxuaMBV0lCFiBhfMQ/XeZ5TLsZ0jztyLzcrWse198VsEQbJ/skLNTllen76dQH/kw3AQ9j2pFgeavUJOEZFdpzhWVykpsQdKwR0a4M65BbmxzbKk3KmU9779613npS7Nthp9Oa0fWYBGeel8WoBMMhFX264i6vadAEAGpPVAgMBAAECggEADjVy8ub/Koah/ZxGIEZdOAhPpzaGPJ1RF+cyuy74KJjF9IJOyqnHpU/GurAM5kQFJT9nuJoU0DOppDKDYMMYpBqxlUx64zwmHtPfcTsehkMVUz/T624wf9y4NVJVcNntC0WgRb8iRgtwlfROgb8u51pHwVSOD/yZFGIdBY3fZz6yP/RnHts8Zy24vl4ZOnMxs9w6lmx/rQzM7W7e/6dYQN56cMBuYpe4DMT1lpEasD1py6j8yQKtrYyskl8djMtqKydKUfvzAuIiTB8H1kWtU5sOTJ1DxhRzoqfqLENCJt81W6bfE3LapVzcUTnBLWmfT07ErcaQqOQkOVIaqyNBgQKBgQDJD83uSmYw8xYCTK/79ShyXsDJibXpcFsy0aobGSDXXuhwyrK+rQZ74Sp8lfKfnOXPUS6ku9icQLtHoy0uGj3TA3bQcF6WxqnN/cMT/bXdEuxEIefsurEQvb+qHei5p+j5qY2A9b4SHmD7eoowv7xoFtGwVnyDeK5EfFCITt+DPQKBgQCudkEW7Tm8WNyNp1V9R1yv48fOgmMWNx00/hkO3gD8iW8JUieeVkxuJOGDkkEoZKMpTH+D6cTwdaIqlZ8eBzNx9KWjaMHuJE+jd4CAT6CRxDSgANuoj6ZZM+8vIo/905Nkt7Lh1q8kb9uP0J3JnVMdmuLvrpNrpYqj4n7uxrR8eQKBgQDEjUmwUFT0Nz7lwpgrhC8lF1H580C9UxQkauiTEw6S3Hn4gX8ZfcYf5i+FmYSU2mmiMOebLPOFVJaAYplxPz/+/5zwCVm+pbkr4Y4KBfT6iFwmAstaFeuwxP4QTKrMi8PqBQGK9zD3P3FphhJ/s4B4dQ5KB1IzqA8cbja/+fLwtQKBgQCfSHJW91HeR5decWeZEN1r7WBQiDTlZH5zEodYTpLB/sx0yyBG7O2tJlkLIi4BLhjVrPVDP9zB2fSsQpza8qIiqcXM8ukUfuyDB6k3/PtR+rw9VWs3c6fiC4uWEk065r/MlfdpP/P6JXJl1IcrO0tWXUJSqKYKA0MYK8POR3cjqQKBgQC5wJrarFXio5qmYNqF/HePl1kgtsTeQ7j8c1Qz73OEKeOmwQbslqxUotV2QUmseM0+bStWCkC3m6ue9LkMu8ik4cEIIAoKO4yDJKsp/PLxxUoxLDyq5uEIh2Z9BRZaoR3V9bV4O2YleqWF/IBywvcU8eTrxcw1jrzL+sC62GAItw==";
	// 支付宝的公钥 RSA
	public static String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArqoTa6tSlY5H1O6HTbqruMeNpS7RwlrJy5+1+zXG6uTCe6VWOLO2xPeXpSbJBeY5FBJ3LlqTgIixr3VJthA0IF+1ChaY6ZYDKkBA3cbzQWemPGoYOifTHvzigzThVPCp2WnvLtCQIDVW40kRy/HNkNQGmZldtF1/4owwATJm4ChvH6e72izyQkK7NsYYxsaxE6P6biLiaPH2MR4kv4emGxIxfRfUp0CELolxNStgAONvO4m/HxyCO34DtnIJuz3DavJAdCldZLXWY2zGz/RnGsYsmdDDN8MKGL5vIKSVEqbLrJx7sJW0BqHEnL15DBQcNArHgSblelp/89GrA0Z+QQIDAQAB";
	// 签名方式 (支付回调签名方式)
	public static String pay_sign_type = "RSA2";

	/**
	 * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
	 * 这里需要测试的话，需要用外网测试。https://www.ngrok.cc/ 这里有免费和付费的，实际上，免费用一下就可以了。
	 */
	public static String notify_url = "http://xby0612.free.idcfengye.com/ali/payAlisHD";
	// 商品的标题/交易标题/订单标题/订单关键字等。
	public static String subject = "商品购买";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 接口名称 固定为：alipay.trade.app.pay
	public static String method = "alipay.trade.app.pay";

	// 调用的接口版本，固定为：1.0
	public static String version = "1.0";

	// 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
	public static String product_code = "QUICK_MSECURITY_PAY";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

}
