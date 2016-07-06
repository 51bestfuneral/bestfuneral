package com.funeral.kris.service;

import java.math.BigDecimal;

public interface PayService {

	public String weixinPay(String orderNo,BigDecimal price,String desc) throws Exception;
}
