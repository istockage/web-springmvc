package com.istockage.common.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class GsonReader {

	private static final Logger logger = Logger.getLogger(GsonReader.class);

	public static StockExchangeReportBean getStockExchangeReport(String date, String stockNo)
			throws MalformedURLException, IOException {

		String url = "http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=" + date + "&stockNo="
				+ stockNo;

		logger.info("URL: " + url);

		InputStream inputStream = new URL(url).openStream();
		Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		StockExchangeReportBean stockExchangeReportBean = new Gson().fromJson(reader, StockExchangeReportBean.class);

		int lastDay = stockExchangeReportBean.getData().get(0).size();

		logger.info(stockNo + ": " + stockExchangeReportBean.getData().get(lastDay).get(0) + ", "
				+ stockExchangeReportBean.getData().get(lastDay).get(6));

		return stockExchangeReportBean;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {

		String date = "20180917";
		String stockNo = "2884";

		StockExchangeReportBean stockExchangeReportBean = getStockExchangeReport(date, stockNo);

		int lastDay = stockExchangeReportBean.getData().get(0).size();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(stockNo);
		stringBuilder.append(": ");
		stringBuilder.append(stockExchangeReportBean.getData().get(lastDay).get(0));
		stringBuilder.append(" ");
		stringBuilder.append(stockExchangeReportBean.getFields().get(6));
		stringBuilder.append(" ");
		stringBuilder.append(stockExchangeReportBean.getData().get(lastDay).get(6));
		stringBuilder.append(" 元");

		System.out.println(stringBuilder.toString());
	}

}
