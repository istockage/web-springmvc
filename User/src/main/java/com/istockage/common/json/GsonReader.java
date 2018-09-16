package com.istockage.common.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class GsonReader {

	public static StockExchangeReportBean getStockExchangeReport(String date, String stockNo)
			throws MalformedURLException, IOException {

		String url = "http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=" + date + "&stockNo="
				+ stockNo;

		StockExchangeReportBean stockExchangeReportBean;
		try (InputStream inputStream = new URL(url).openStream();
				Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

			Gson gson = new Gson();
			stockExchangeReportBean = gson.fromJson(reader, StockExchangeReportBean.class);
		}

		return stockExchangeReportBean;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {

		String date = "201809";
		String stockNo = "2884";

		StockExchangeReportBean stockExchangeReportBean = getStockExchangeReport(date, stockNo);

		int size = stockExchangeReportBean.getData().get(0).size();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(stockNo);
		stringBuilder.append(": ");
		stringBuilder.append(stockExchangeReportBean.getData().get(size).get(0));
		stringBuilder.append(" ");
		stringBuilder.append(stockExchangeReportBean.getFields().get(6));
		stringBuilder.append(" ");
		stringBuilder.append(stockExchangeReportBean.getData().get(size).get(6));
		stringBuilder.append(" 元");

		System.out.println(stringBuilder.toString());
	}

}
