package com.model.count;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.activity.Count_Activity;
import com.dao.TJ_DAO;
import com.inteface.Chart;
import com.yyyy.yyyy.R;

public class PieChart implements Chart {
	ArrayList<Float> consume_type_list;
	TJ_DAO tj_DataBaseHelper;
	Activity context = Count_Activity.countActivity;
	float percent[] = { 0, 0, 0, 0 };
	//��ʱ����л�ͼ
	String date;
	public PieChart(String date) {	
		this.date = date;
	}
	/**
	 * �����ݿ��ȡÿ�����Ļ�����Ǯ����ʱ����в�ѯ
	 */
	public CategorySeries getSeries(String date) {
		System.out.println("lilingling"+date);
		CategorySeries series = new CategorySeries("���ѷ���");
		consume_type_list = new ArrayList<Float>();
		TJ_DAO tj_DataBaseHelper = new TJ_DAO();
		consume_type_list = tj_DataBaseHelper.getTypeConsume(date);
		for(int i = 0;i < 4;i++){
			percent[i] = (float) consume_type_list.get(i);
		}
		series.add("��", percent[0]);
		series.add("ʳ", percent[1]);
		series.add("ס", percent[2]);
		series.add("��", percent[3]);
		return series;
	}
	/**
	 * �Խ�Ҫ���ı�ͼ������������
	 */
	public DefaultRenderer getRenderer(){
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setMargins(new int[] { 10, 20, 30, 0 });// ����ͼ��֮��ļ�ӵ�λΪpx
		renderer.setLabelsTextSize(30);// ���ñ�ǩ�������С
		renderer.setLabelsColor(Color.WHITE);
		renderer.setDisplayValues(true);// �Ƿ���ʾֵ
		renderer.setChartTitle("����������");
		renderer.setLegendTextSize(30);// ����ͼ���������С
		renderer.setZoomEnabled(false);
		// ���ÿ�����������ٷֱȲ�����ÿһ����������
		SimpleSeriesRenderer r1 = new SimpleSeriesRenderer();
		r1.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r1);// ���ñ�ͼ��ɫ����
		SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
		r2.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r2);// ���ñ�ͼ��ɫ��ʳ
		SimpleSeriesRenderer r3 = new SimpleSeriesRenderer();
		r3.setColor(Color.RED);
		renderer.addSeriesRenderer(r3); // ���ñ�ͼ��ɫ��ס
		SimpleSeriesRenderer r4 = new SimpleSeriesRenderer();
		r4.setColor(Color.YELLOW);
		renderer.addSeriesRenderer(r4); // ���ñ�ͼ��ɫ����
		return renderer;
	}
	@Override
	public void draw() {
		CategorySeries series = getSeries(date);
		DefaultRenderer renderer = getRenderer();
		GraphicalView pieView = ChartFactory.getPieChartView(context, series,
				renderer);
		LinearLayout pieViewLayout = (LinearLayout) context
				.findViewById(R.id.pieView1);
		if (pieViewLayout != null) {
			pieViewLayout.removeAllViews();// �����ԭ����ͼ
		}
		if (percent[0] == 0 && percent[1] == 0 && percent[2] == 0
				&& percent[3] == 0) {
			Toast.makeText(context, "û�����Ѽ�¼", Toast.LENGTH_SHORT).show();
		} else {
			
			// ȷ��������С
			LayoutParams show = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);		
			pieViewLayout.addView(pieView, show);// ����ͼ��Ϊ�Ӳ��ּ��뵽��������
		}
	}
}
