package com.logic;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;

import com.activity.Stream_Activity;
import com.dao.DataBase;
import com.dao.JZ_DAO;
import com.dao.LS_DAO;
import com.mnitools.GetNowDate;
import com.model.count.Draw_chart;
import com.model.count.LineChart;
import com.model.count.PieChart;
import com.model.stream.LSManager;
import com.yyyy.yyyy.R;

@SuppressWarnings({ "unused" })
public class Index_ContorlHelper {
	private DataBase dataBase;
	private String currentString;// ��ǰ����
	Context context;
	LinearLayout[][] linearLayoutChild;
	int i = 0;

	/**
	 * ���캯�����õ�LocalActivityManager��DataBase
	 * 
	 * @param manager
	 * @param dataBase
	 */
	@SuppressLint("SimpleDateFormat")
	public Index_ContorlHelper(Context context, DataBase dataBase) {
		this.context = context;
		this.dataBase = dataBase;
		// ���쵱ǰ���� xxxx-xx
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		this.currentString = format.format(currentDate);
	}

	/**
	 * �˺��������������ݵ�ǰ����һ�ν����жϽ���������Ϊ
	 * 
	 * @param current
	 * @param passed
	 */
	
	public void getDecide(int current, int passed) {
		if (current == 1 || passed == 1) { // ��ˮ
//			getFrameOfThisYear();
			 updateStreamUI(current);
		}
		if (current == 0) { // ����,������else if
			updatejzUI();
		}
		/**
		 * author LLL 
		 * content:ͳ�� 
		 * time:4.18
		 * ��pieView��TJ_DrawHelper��ViewBase������model����
		 */
		if (current == 2) {
			/**
			 * �����ѷ���ͼ
			 */
			
			//��ȡ��ǰ����
			GetNowDate nowDate = new GetNowDate();
			String now_date = nowDate.getNowDate("yyyy-MM");
			PieChart pie = new PieChart(now_date);
			Draw_chart.draw_Chart(pie);
			LineChart line= new LineChart(now_date);
			Draw_chart.draw_Chart(line);

			
		}
	}

	/**
	 * ������ˮ����
	 * 
	 * @param current
	 */
	private void updateStreamUI(int current) {
		LinearLayout linearLayout = (LinearLayout) Stream_Activity.streamActivity
				.findViewById(R.id.lin);
		// ��������ˮ���棬����
		if (current == 1) {
			LS_DAO ls_DataBaseHelper = new LS_DAO(
					context);
			LSManager lsManager = new LSManager(ls_DataBaseHelper);
			
			String[] jString = currentString.split("-");
			int number = Integer.parseInt(jString[1]);// �õ���ǰ����
			System.out.println("��ǰ������" + number);

			lsManager.getFrame(number);
		}
		// ������ˮ����,�����ˮ����
		else {
			linearLayout.removeAllViews();
		}
	}

	/**
	 * ������˽���
	 */
	private void updatejzUI() {
		JZ_DAO jz_DataBaseHelper = new JZ_DAO();
		jz_DataBaseHelper.updateBudgetRemain();
	}
	
	
	
}
