package br.net.comexport.api.service.util;

import java.math.BigDecimal;

public class Util {

	public static BigDecimal calculatePrice(final BigDecimal price, final Double fixedRateValue, final Double percentageRateValueOfProduct) {
		BigDecimal fixedRate = new BigDecimal(fixedRateValue);
		BigDecimal percentageRate = new BigDecimal(percentageRateValueOfProduct);
		
		BigDecimal rate = price.multiply(percentageRate).divide(new BigDecimal(100));
		
		return price.add(fixedRate).add(rate);
	}
}
