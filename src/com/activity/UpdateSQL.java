package com.activity;

import android.database.sqlite.SQLiteDatabase;

/**
 * ���ܸ������ݿ⣬������࣬�Ժ�������ݿ�Ĵ��༴��
 * @author wcj
 *
 */
public class UpdateSQL {
	private SQLiteDatabase db;
	public UpdateSQL(SQLiteDatabase db) {
		// TODO Auto-generated constructor stub
		this.db = db;
	}
	
	/**
	 * SQLite���ݿ����
	 * @param sql
	 * @return
	 */
	public boolean updateSQLite(String sql){
		boolean tag = false;
		db.execSQL(sql);
		tag = true;
		return tag;
	}
}
