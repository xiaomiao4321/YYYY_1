package com.mnitools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 获得格式化日期字符串
 * @author wcj
 *
 */
public class GetNowDate{

	@SuppressLint("SimpleDateFormat")

	public String getNowDate(String formString) {
		// TODO Auto-generated method stub
		String currentTime = "";
		SimpleDateFormat format = new SimpleDateFormat(formString);
		currentTime = format.format(new Date());
		System.out.println("当前日期"+currentTime);
		return currentTime;
	}

}
