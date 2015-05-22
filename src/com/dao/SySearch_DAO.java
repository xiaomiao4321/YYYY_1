package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;
import com.mnitools.GetNowDate;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class SySearch_DAO {
	
	private Cursor cursor;
	private String sql;
//	private ISelect cursorSelecter;
//	private ISelect stringSelecter;
	
	public SySearch_DAO() {
		// TODO Auto-generated constructor stub
//		cursorSelecter = new SelectCursor(Index_Activity.db);
//		stringSelecter = new SelectString(Index_Activity.db);
	}
	/**
	 * �õ�id
	 * @return
	 */
	public String searchId(){
//		sql = "select id from user";
//		String id = (String)stringSelecter.select(sql);
		sql = SQLString.getSearchId_Sy();
		String id = Index_Activity.basicDAO.selectString(sql);
		return id;
	}
	/**
	 * ��ѯ�õ�������ˮ
	 * @param time	�ϴ�ͬ����ʱ��
	 * @return
	 */
	public Cursor searchStreamCount(){
		String time = getLastsytime();
//		sql = "select * from test1 where date > '" + time + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchStreamCount_Sy(time);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		int i = cursor.getCount();
		System.out.println("������ˮ:" + i );
		return cursor;
	}
	
	
	/**
	 * �õ�����Ԥ���Ԥ��ʣ��
	 * @param datetime	��һ�µ�Ԥ��	 ��ʽ:xxxx-xx
	 * @return
	 */
	public Cursor searchBudget(String datetime){
//		sql = "select * from tabletotalbudget where month = '" + datetime + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchBudget_Sy(datetime);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	
	/**
	 * �õ���������
	 * @param datetime	��һ�µ�����	��ʽ:xxxx-xx
	 * @return
	 */
	public Cursor searchincome(String datetime){
//		sql = "select * from consumein where month = '" + datetime + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchincome_Sy(datetime);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	
	/**
	 * �õ����·���Ԥ��
	 * @param datetime	��һ�µķ���Ԥ��
	 * @return
	 */
	public Cursor searchBudgetByKind(String datetime){
//		sql = "select * from tablebudget where month = '" + datetime + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchBudgetByKind_Sy(datetime);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	@SuppressLint("SimpleDateFormat")
	public boolean isNextMonth(String date) throws ParseException{
		String lastDate = getLastsytime();
//		sql = "select sytime from time";
//		lastDate = (String)stringSelecter.select(sql);
//		sql = SQLString.getSytime_Sy();
//		lastDate = Index_Activity.basicDAO.selectString(sql);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = format.parse(lastDate);
		format = new SimpleDateFormat("yyyy-MM");
		lastDate = format.format(date1);
		return !(lastDate.equals(date));
	}
	
	public String getLastsytime(){
		sql = SQLString.getSytime_Sy();
		String lastDate = Index_Activity.basicDAO.selectString(sql);
		System.out.println("��һ��ͬ��ʱ��" + lastDate);
		return lastDate;
	}
	
	
	/**
	 * ÿ��ͬ��֮�����ʱ���
	 */
	@SuppressLint("SimpleDateFormat")
	public void updateTime(){
		String currenString = (new GetNowDate()).getNowDate("yyyy-MM-dd hh:mm:ss");
		System.out.println("���ڸ���ͬ��ʱ��" + currenString);
		
//		sql = "update time set sytime = '" + currenString + "'";
//		(new UpdateSQL(Index_Activity.db)).updateSQLite(sql);
		sql = SQLString.getUpdateTime_Sy(currenString);
		Index_Activity.basicDAO.update(sql);
	}
}
