package com.funeral.kris.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OAuthServiceImpl  implements OAuthService {

	public static final String ALIPAY_GATEWAY = "https://mapi.alipay.com/gateway.do?";
	public static final String QQ_GATEWAY = "https://graph.qq.com/oauth2.0/authorize?";
	public static final String WEBCHAT_GATEWAY = "https://open.weixin.qq.com/connect/qrconnect?";
	public static final String SINA_GATEWAY = "https://api.weibo.com/oauth2/authorize?";
	public static final String QQ_APP_KEY = "";
	public static final String QQ_APP_ID = "218908";
	public static final String WEBCHAT_APP_ID = "wxbdc5610cc59c1631";
	public static final String SINA_APP_ID="3613929600";

	public String buildUrl(String oauthType,String returnUrl) {
		StringBuffer sbUrl = new StringBuffer();
		if (oauthType == null) {
			return null;
		}
		// QQ
		if ("1".equals(oauthType)) {
			sbUrl.append(QQ_GATEWAY);
			sbUrl.append("response_type=code");
			sbUrl.append("&scope=get_user_info,add_share");
			sbUrl.append("&client_id="+QQ_APP_ID);
			sbUrl.append("&redirect_uri="+"http://www.365niannian.com/");
		}
		// web chat
		else if ("2".equals(oauthType)) {
			sbUrl.append(WEBCHAT_GATEWAY);
			sbUrl.append("response_type=code");
			sbUrl.append("&scope=snsapi_login");
			sbUrl.append("&appid="+WEBCHAT_APP_ID);
			sbUrl.append("&redirect_uri="+"https://passport.yhd.com/wechat/callback.do");
		}
		// Sina
		else if ("3".equals(oauthType)) {
			sbUrl.append(SINA_GATEWAY);
			sbUrl.append("response_type=code");
			sbUrl.append("&scope=all");
			sbUrl.append("&client_id="+SINA_APP_ID);
			sbUrl.append("&redirect_uri="+"https://passport.yhd.com/wechat/callback.do");
		}
		// alipay
		else if ("4".equals(oauthType)) {
			sbUrl.append(ALIPAY_GATEWAY);
		}

		return sbUrl.toString();
	}

}
