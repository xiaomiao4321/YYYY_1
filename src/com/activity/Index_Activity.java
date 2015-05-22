package com.activity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.http.impl.conn.SingleClientConnManager;














//import com.yyyy.yyyy.Index_Activity.MyAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

import com.dao.DataBase;
import com.dao.JZ_DAO;
import com.dao.basic.BasicDAO;
import com.inteface.IBasicDAO;
import com.logic.BackgroundColor;
import com.logic.Index_ContorlHelper;
import com.model.user.Init;
import com.yyyy.yyyy.R;

@SuppressWarnings("deprecation")
@SuppressLint("InflateParams")
public class Index_Activity extends Activity {

	Context context = null;

	private ViewPager viewPager;
	@SuppressWarnings("unused")
	private PagerTitleStrip pagerTitleStrip;
	private List<View> views;
	public static Activity indexActivity;
	public static float remain;//ʣ��Ԥ��
	public static float budget;//��Ԥ��
	LocalActivityManager manager = null;
	public static DataBase dataBase;// �����ݿ�
	public static SQLiteDatabase db;//�����ݿ�����
	public static IBasicDAO basicDAO = null;
	static int SIGN = 0;//��һ��������SIGN = 0;��־λ
	int current = 0;
	int passed = -1;
	Index_ContorlHelper index_ContorlHelper;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null) {
			db.close();// SQLiteDatabase sqldb;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (SIGN != 0) {
			//���¼��˽���Ԥ����ʾ
			JZ_DAO jz_DataBaseHelper = new JZ_DAO();
			jz_DataBaseHelper.updateBudgetRemain();
			BackgroundColor backgroundColor = new BackgroundColor();
			backgroundColor.refreshback();
			System.out.println("������resum");
		}
		SIGN++;
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		context = Index_Activity.this;
		indexActivity = this;
		manager = new LocalActivityManager(this, true);
//		dataBase = new DataBase(Index_Activity.this, "user.db");
//		db = dataBase.getWritableDatabase();
		basicDAO = new BasicDAO();
		basicDAO.connectDataBase("user.db");
		
		manager.dispatchCreate(savedInstanceState);
		// ��ü�������
		index_ContorlHelper = new Index_ContorlHelper(Index_Activity.this, dataBase);
		// ���viewPager
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);

		views = new ArrayList<View>();

		Intent intent_jzIntent = new Intent(context, JZ_Activity.class);
		intent_jzIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		views.add(getView("JZ_Activity", intent_jzIntent));

		Intent intent_streamIntent = new Intent(context, Stream_Activity.class);
		intent_streamIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		View streamView = getView("Stream_Activity", intent_streamIntent);
		views.add(streamView);
		
		Intent intent_countIntent = new Intent(context, Count_Activity.class);
		intent_countIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		views.add(getView("Count_Activity", intent_countIntent));
		
		//��һ���������¼��˽���Ԥ����ʾ
		JZ_DAO jz_DataBaseHelper = new JZ_DAO();
		jz_DataBaseHelper.updateBudgetRemain();
		
		viewPager.setAdapter(new MyAdapter());
		try {
			@SuppressWarnings("unused")
			Init init = new Init();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("��ʼ������");
			e.printStackTrace();
		}
		/**
		 * ҳ�滬���ı�״̬����
		 */
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			/**
			 * ��ҳ�汻ѡ��ʱִ�д˺���ˢ��ҳ��
			 */
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				System.out.println("ѡ����-------------" + arg0);
				passed = current;
				current = arg0;
				// ����
				index_ContorlHelper.getDecide(current, passed);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		//���½�����ɫ
		BackgroundColor backgroundColor = new BackgroundColor();
		backgroundColor.refreshback();
		//��������
		String consumed = new DecimalFormat("0.0").format(budget - remain);
		System.out.println("��ʽ��֮��ĸ�����" + consumed);
		JZ_Activity.consumed.setText(consumed);
		
	}

	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	class MyAdapter extends PagerAdapter {
		public MyAdapter() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			System.out.println("���ٵ�viewΪ" + position);
			((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(views.get(position));
			System.out.println("��ʼ����viewΪ" + position);
			return views.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
	}
}
