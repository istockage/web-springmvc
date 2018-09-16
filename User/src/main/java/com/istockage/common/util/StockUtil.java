package com.istockage.common.util;

import com.istockage.model.entity.StockEntity;

public class StockUtil {

	public static Integer getSt_sell_delivery(Float st_sell_price, StockEntity stockEntity) {

		Integer st_sell_fee = (int) Math
				.round(Math.floor((float) (st_sell_price * stockEntity.getSt_buy_share() * 0.001425))
						* stockEntity.getSt_buy_discount() / 100);
		Integer st_sell_tax = (int) Math.floor((float) (st_sell_price * stockEntity.getSt_buy_share() * 0.003));
		Integer st_sell_delivery = (int) (st_sell_price * stockEntity.getSt_buy_share() - st_sell_fee - st_sell_tax);

		return st_sell_delivery;
	}

}
