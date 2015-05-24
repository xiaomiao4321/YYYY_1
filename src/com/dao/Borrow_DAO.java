package com.dao;

import android.database.Cursor;

import com.dao.basic.BasicDAO;
import com.dao.basic.SQLString;

public class Borrow_DAO {
	private BasicDAO dao  = new BasicDAO();
	public Borrow_DAO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��������ȡ�ܺ�
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
	/**
	 * ����ӵĽ��itemд�뵽���ݿ�
	 */
//	public void insertBorrowItem(){
//		String sql = SQLString.InsertBorrowItem();
//		dao.insert(sql);
//	}
}
