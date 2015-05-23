package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dao.Borrow_DAO;
import com.yyyy.yyyy.R;

public class Borrow_Return extends Activity {
	private TextView additem;// ��ӽ����Ŀ��
	private TextView borrow_total;// �ܵĽ����Ǯ��
	private TextView return_total;// �ܵ��軹�ص�Ǯ��
	private ListView borrow_list;// �������ϸ
	private ListView return_list;// �軹�ص���ϸ
	private Borrow_DAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrow__return);
		additem = (TextView) this.findViewById(R.id.add_borrow_item);
		borrow_total = (TextView) this.findViewById(R.id.borrow);
		return_total = (TextView) this.findViewById(R.id.back);
		borrow_list = (ListView) this.findViewById(R.id.borrow1);
		return_list = (ListView) this.findViewById(R.id.back1);
		/**
		 * ��ӽ����Ŀ
		 */
		additem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Borrow_Return.this, Add_Borrow_item.class);
				startActivity(intent);
			}
		});
		/**
		 * ��ȡ�ܵĽ���ͽ���Ľ��Ľ��,���ŷֱ���0��1,����ʾ
		 */
		dao = new Borrow_DAO();
		String total_Borrow = dao.getTotal(0).toString();
		borrow_total.setText(total_Borrow);
		String total_Return = dao.getTotal(1).toString();
		return_total.setText(total_Return);
		/**
		 * ����������ϸ�б�
		 */
		SimpleAdapter adapterBorrow = new SimpleAdapter(this, getData(0),
				R.layout.borrow_return_list_item, new String[] { "name",
						"money", "date" }, new int[] { R.id.userId, R.id.money,
						R.id.returnDate });
		borrow_list.setAdapter(adapterBorrow);
		SimpleAdapter adapterReturn = new SimpleAdapter(this, getData(1),
				R.layout.borrow_return_list_item, new String[] { "name",
						"money", "date" }, new int[] { R.id.userId, R.id.money,
						R.id.returnDate });
		return_list .setAdapter(adapterReturn);
	}

	private List<Map<String, Object>> getData(int kind) {
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Cursor cursor = dao.getList(kind);
		while (cursor.moveToNext()){
			map.put("name", cursor.getString(cursor.getColumnIndex("userId")));
			map.put("money", cursor.getFloat(cursor.getColumnIndex("money")));
			map.put("date", cursor.getString(cursor.getColumnIndex("return_time")));
			list.add(map);
		}
		return list;
	}
}
