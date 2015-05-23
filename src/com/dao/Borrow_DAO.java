package com.dao;

import android.R.integer;
import android.database.Cursor;

import com.dao.basic.BasicDAO;
import com.dao.basic.SQLString;

public class Borrow_DAO {
	private BasicDAO dao  = new BasicDAO();
	public Borrow_DAO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 根据类别获取总和
	 * @return
	 */
	public Float getTotal(int kind){
		String sql = SQLString.getTotal(kind);
		Float total = dao.selectFloat(sql);
		return total;
	}
	public Cursor getList(int kind){
		String sql = SQLString.getList(kind);
		Cursor cursor = dao.selectCursor(sql);
		return cursor;
	}
}
