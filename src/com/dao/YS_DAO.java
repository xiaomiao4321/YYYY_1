package com.dao;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;
import com.mnitools.GetNowDate;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class YS_DAO {

	private String currentString;
//	private UpdateSQL updateSQL = null;
	@SuppressLint("SimpleDateFormat")
	public YS_DAO() {
		GetNowDate getNowDate = new GetNowDate();
		currentString = getNowDate.getNowDate("yyyy-MM");		// ��õ�ǰ����
		System.out.println("YS�����õ�������:" + currentString);
//		this.updateSQL = new UpdateSQL(Index_Activity.db);
	}

	/**
	 * ��ѯ���ݿ�õ���������Ԥ����Ϣ
	 * 
	 * @return
	 */
	public Cursor read_budget() {
//		SQLiteDatabase db = dataBase.getReadableDatabase();
//		Cursor cursor;
//		cursor = Index_Activity.db.rawQuery("select budget from tablebudget where month = '"
//				+ currentString + "'", null);
		String sql = SQLString.getBudget_Ys(currentString);
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		System.out.println("��ѯʱ�䣺" + currentString);
		return cursor;
	}

	/**
	 * ��ȡ��Ԥ��
	 * 
	 * @return
	 */
	public String read_totalbudget() {
		String totalString = "Ŷ�����ݿ�崻���...";
//		String sql = "select totalbudget from tabletotalbudget where month = '"
//				+ currentString + "'";
//		ISelect selecter = new SelectString(Index_Activity.db);
//		totalString = (String)selecter.select(sql);
		String sql = SQLString.getTotalbudget_Ys(currentString);
		totalString = Index_Activity.basicDAO.selectString(sql);
		return totalString;
	}

	/**
	 * ���û�и���Ԥ������룬����������
	 * 
	 * @param consum_mony
	 *            Ԥ����
	 * @param kind
	 *            Ԥ�����
	 * @return
	 */
	public boolean add(float[] budget, int[] kind) {
		String sql;
		for (int i = 0; i < budget.length; i++) {
			// ���Ԥ�㣬�Դ�ǰ�����ݸ���
//			sql = "update tablebudget set budget = " + budget[i]
//					+ ", remain = remain - budget + " + budget[i]
//					+ " where kind = " + kind[i] + " and month = '"
//					+ currentString + "'";
//			updateSQL.updateSQLite(sql);
			sql = SQLString.getAddBudget_Ys(budget[i], kind[i], currentString);
			Index_Activity.basicDAO.update(sql);
		}
		return true;
	}

	/**
	 * ������Ԥ���
	 * 
	 * @param totalbudget
	 *            ��Ԥ��
	 * @return
	 */
	public boolean addtotal(float totalbudget) {
//		String sql = "update tabletotalbudget set remain = remain - totalbudget + "
//				+ totalbudget + " where month = '" + currentString + "'";
//		updateSQL.updateSQLite(sql);
		String sql = SQLString.getAddtotal_Ys(totalbudget, currentString);
		Index_Activity.basicDAO.update(sql);
		
//		sql = "update tabletotalbudget set totalbudget = " + totalbudget
//				+ " where month = '" + currentString + "'";
//		updateSQL.updateSQLite(sql);
		sql = SQLString.getAddtotal1_Ys(totalbudget, currentString);
		Index_Activity.basicDAO.update(sql);
		return true;
	}

	/**
	 * ������Ԥ���,��ÿ�μ�һ��ʱ����
	 */
	private boolean deltotal(float consume) {
//		String sql = "update tabletotalbudget set remain = remain -" + consume
//				+ " where month = '" + currentString + "'";
//		updateSQL.updateSQLite(sql);
		String sql = SQLString.getDeltotal_Ys(consume, currentString);
		Index_Activity.basicDAO.update(sql);
		return true;
	}

	/**
	 * ÿ��һ���˸��·���Ԥ��ʣ��
	 * 
	 * @return
	 */
	public boolean update(float consume, int kind) {
//		String sql = "update tablebudget set remain = remain -" + consume
//				+ " where kind = " + kind + " and month = '" + currentString
//				+ "'";
//		updateSQL.updateSQLite(sql);
		String sql = SQLString.getUpdate_Ys(consume, kind, currentString);
		Index_Activity.basicDAO.update(sql);
		deltotal(consume);
		return true;
	}

	/**
	 * ���������
	 * 
	 * @param in
	 * @param db
	 * @return
	 */
	public boolean updatein(float in) {
//		String sql = "update consumein set mony = mony + " + in
//				+ " where month = '" + currentString + "'";
//		updateSQL.updateSQLite(sql);
		String sql = SQLString.getUpdatein_Ys(in, currentString);
		Index_Activity.basicDAO.update(sql);
		return true;
	}
}
