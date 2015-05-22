package com.dao.basic;

import com.activity.Index_Activity;
import com.dao.DataBase;
import com.inteface.IBasicDAO;

import android.database.Cursor;
/**
 * ���ݿ������������̬
 */
import android.database.sqlite.SQLiteDatabase;

public class BasicDAO implements IBasicDAO{

	private static SQLiteDatabase db = null;

	/**
	 * ����sqlite���ݿ�
	 * @return
	 */
	public boolean connectDataBase(String dbName) {
		boolean tag = false;
		DataBase dataBase = new DataBase(Index_Activity.indexActivity, dbName);
		db = dataBase.getWritableDatabase();
		if (db != null)
			tag = true;
		return tag;
	}
	
	public Cursor selectCursor(String sql) {
		Cursor cursor = null;
		if (isConnect())
			cursor = db.rawQuery(sql, null);
		else {
			System.out.println("δ�������ݿ�");
		}
		return cursor;
	}

	public float selectFloat(String sql) {
		float result = 0f;
		if (isConnect()) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				result = cursor.getFloat(0);
			}
		} else {
			System.out.println("δ�������ݿ�");
		}
		return result;
	}

	public int selectInt(String sql) {
		int result = 0;
		if (isConnect()) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				result = cursor.getInt(0);
			}
		} else {
			System.out.println("δ�������ݿ�");
		}
		return result;
	}

	public String selectString(String sql) {
		String result = null;
		if (isConnect()) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				result = cursor.getString(0);
			}
		} else {
			System.out.println("δ�������ݿ�");
		}
		return result;
	}

	public boolean insert(String sql) {
		boolean tag = false;
		if (isConnect()) {
			db.execSQL(sql);
			tag = true;
		} else {
			System.out.println("δ�������ݿ�");
		}
		return tag;
	}

	public boolean update(String sql) {
		boolean tag = false;
		if (isConnect()) {
			db.execSQL(sql);
			tag = true;
		} else {
			System.out.println("δ�������ݿ�");
		}
		return tag;
	}

	public boolean drop(String sql) {
		boolean tag = false;
		if (isConnect()) {
			db.execSQL(sql);
			tag = true;
		} else {
			System.out.println("δ�������ݿ�");
		}
		return tag;
	}

	/**
	 * ������ݿ��Ƿ�����
	 * 
	 * @return
	 */
	public boolean isConnect() {
		boolean tag = (db != null) ? true : false;
		return tag;
	}
}
