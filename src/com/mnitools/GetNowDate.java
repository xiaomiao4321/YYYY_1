package com.mnitools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * ��ø�ʽ�������ַ���
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
		System.out.println("��ǰ����"+currentTime);
		return currentTime;
	}

}
