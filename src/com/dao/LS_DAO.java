package com.dao;

//import android.app.Activity;
import com.activity.Index_Activity;
import com.dao.basic.SQLString;

import android.content.Context;
import android.database.Cursor;

public class LS_DAO {
	Context context;
	String tableNameString = "test1"; // ��ˮ���� test1

	public LS_DAO(Context context) {
		this.context = context;

	}

	/**
	 * ��ѯ������ˮ��
	 * 
	 * @param manager
	 * @param dataBase
	 * @return
	 */
	public Cursor selectAllAccount(String dateString) {
		
//		String sql = "select consume, kind, date, inorout from stream where date >= '"
//				+ dateString
//				+ "' and date < date('"
//				+ dateString
//				+ "', '+1 month') order by date desc";
//		System.out.println("sql �����" + sql);
//		ISelect selecter = new SelectCursor(Index_Activity.db);
//		Cursor cursor = (Cursor)selecter.select(sql);
		String sql = SQLString.getSelectAllAccount_LS(dateString);
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
}
