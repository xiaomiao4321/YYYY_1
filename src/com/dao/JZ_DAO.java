package com.dao;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.widget.TextView;

import com.activity.Index_Activity;
import com.activity.JZ_Activity;
import com.dao.basic.SQLString;
import com.mnitools.GetNowDate;
import com.yyyy.yyyy.R;

public class JZ_DAO {
	private String currentString;

	@SuppressLint("SimpleDateFormat")
	public JZ_DAO() {
		// ��õ�ǰ���ڣ����ڲ�ѯ���ݿ���������
		this.currentString = (new GetNowDate()).getNowDate("yyyy-MM");
	}

	/**
	 * ���¼���ҳ���Ԥ�������ʾ
	 */
	public void updateBudgetRemain() {
		Activity jz_Activity = JZ_Activity.jzActivity;// ���JZ_Activity����
		TextView remainTextView = (TextView) jz_Activity
				.findViewById(R.id.budgetRemain);

		String sql = SQLString.getUpdateBudgetRemain_JZ(currentString);
		Cursor cursor = (Cursor) Index_Activity.basicDAO.selectCursor(sql);

		// String sql =
		// "select remain,totalbudget from tabletotalbudget where month = '"
		// + currentString + "'";
		// ISelect selecter = new SelectCursor(Index_Activity.db);
		// Cursor cursor = (Cursor)selecter.select(sql);
		if (cursor.moveToNext()) {
			String remainString = cursor.getString(cursor
					.getColumnIndex("remain"));
			Index_Activity.budget = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("totalbudget")));
			remainString = new DecimalFormat("0.0").format(Float
					.parseFloat(remainString));// ��ʽ��������
			remainTextView.setText(remainString);
		}
	}

	public static void insertStream(float consum1, String kind, String date,
			int inOrOut, int consumekind) {
		String sql = SQLString.getInsertStream(consum1, kind, date, inOrOut, consumekind);
		Index_Activity.basicDAO.insert(sql);
	}
}
