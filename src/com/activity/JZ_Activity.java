package com.activity;

/**
 * 1.��Ӳ���+��ť�¼������ڲ���
 * 2.���ȷ����ť
 * @author wcj
 * @time 15-3-31��
 */

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dao.JZ_DAO;
import com.dao.YS_DAO;
import com.inteface.IInputCheck;
import com.logic.BackgroundColor;
import com.mnitools.InputCheck;
import com.model.cloud.CloudSendHelper;
import com.yyyy.yyyy.R;

public class JZ_Activity extends Activity{

	public static TextView budgetRemain;
	private TextView kind;
	public TextView consume;
	private TextView setting;
	private Button number_1;
	private Button number_2;
	private Button number_3;
	private Button number_4;
	private Button number_5;
	private Button number_6;
	private Button number_7;
	private Button number_8;
	private Button number_9;
	private Button number_0;
	private Button syButton; // ���԰�ť
	private TextView number_in;
	private TextView number_out;
	private Button button_ok;
	private Button number_float;
	private Button number_clear;
	public static String consumString = "";
	public static TextView consumed;
	public static LinearLayout linearLayout;
	private int inOrOut = 0; // 0����֧����1�������룬Ĭ��֧��
	public static int consumekind = 2; // ������������Ĭ��Ϊʳ��
	private ArrayList<String> kindList = new ArrayList<String>();
	public static Activity jzActivity;
	private TextView zyj;
	private TextView zq;
	private TextView jd;//�Ӵ�����ť��LLL��
	private ProgressDialog pd;

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("hehe������Resume");
	};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jz1);
		jzActivity = this;
		System.out.println("JZ������");
		System.out.println("JZ�߳�:" + Thread.currentThread().getId());
		/**
		 * �������
		 */
		consume = (TextView) this.findViewById(R.id.consume);
		budgetRemain = (TextView) this.findViewById(R.id.budgetRemain);
		number_0 = (Button) this.findViewById(R.id.number_0);
		number_1 = (Button) this.findViewById(R.id.number_1);
		number_2 = (Button) this.findViewById(R.id.number_2);
		number_3 = (Button) this.findViewById(R.id.number_3);
		number_4 = (Button) this.findViewById(R.id.number_4);
		number_5 = (Button) this.findViewById(R.id.number_5);
		number_6 = (Button) this.findViewById(R.id.number_6);
		number_7 = (Button) this.findViewById(R.id.number_7);
		number_8 = (Button) this.findViewById(R.id.number_8);
		number_9 = (Button) this.findViewById(R.id.number_9);
		number_in = (TextView) this.findViewById(R.id.button_in);
		number_out = (TextView) this.findViewById(R.id.button_out);
		number_float = (Button) this.findViewById(R.id.number_float);
		number_clear = (Button) this.findViewById(R.id.number_clear);
		button_ok = (Button) this.findViewById(R.id.ok);
		consumed = (TextView) this.findViewById(R.id.comsumed);
		linearLayout = (LinearLayout) this.findViewById(R.id.background);
		zyj = (TextView) this.findViewById(R.id.zyj);
		setting = (TextView) this.findViewById(R.id.setting);
		zq = (TextView) this.findViewById(R.id.zq);
		//�ҵ������ť
		jd = (TextView) this.findViewById(R.id.jd);
		// ���԰�ť
		syButton = (Button) this.findViewById(R.id.sy);
		kind = (TextView) this.findViewById(R.id.kind);

		kindList.add("���㷹��");
		kindList.add("�������");
		kindList.add("���㷹��");
		kindList.add("˹��ª��");
		kindList.add("̤����Ь");

		final IInputCheck inputCheck = new InputCheck(consume, consumString);//������ӿڻص�
		inputCheck.setLisener_number(number_0, "0");
		inputCheck.setLisener_number(number_1, "1");
		inputCheck.setLisener_number(number_2, "2");
		inputCheck.setLisener_number(number_3, "3");
		inputCheck.setLisener_number(number_4, "4");
		inputCheck.setLisener_number(number_5, "5");
		inputCheck.setLisener_number(number_6, "6");
		inputCheck.setLisener_number(number_7, "7");
		inputCheck.setLisener_number(number_8, "8");
		inputCheck.setLisener_number(number_9, "9");
		inputCheck.setLisener_clear(number_clear);
		inputCheck.setLisener_float(number_float, ".");
		
		/**
		 * ��ǮĿ�����
		 */
		zq.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("�������ǮĿ��");
				pd = ProgressDialog.show(JZ_Activity.this, "�Ա�",
						"�Ա�װ�������У����Ժ󡭡�");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.currentThread().sleep(5000);
							pd.dismiss();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		/**
		 * @author LLL
		 * �����������¼�
		 */
		jd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	           Intent intent = new Intent();
	           intent.setClass(JZ_Activity.this, Borrow_Return.class);
	           startActivity(intent);
			}
		});

		/**
		 * ���ð�ť�¼�
		 */
		setting.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(jzActivity, User_Activity.class);
				startActivity(intent);
			}
		});

		/**
		 * ���ѽ�
		 */
		zyj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JZ_Activity.this,
						Street_Activity.class);
				startActivity(intent);
			}
		});

		/**
		 * ����ͬ����ť
		 */
		syButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CloudSendHelper cloudSendHelper = new CloudSendHelper();
				try {
					try {
						if (cloudSendHelper.checkAndSend()) {
							// Toast.makeText(JZ_Activity.jzActivity, "ͬ���ɹ�!",
							// Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(JZ_Activity.jzActivity, "ͬ��ǰ���¼Ŷ��",
									Toast.LENGTH_LONG).show();
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		/**
		 * ��������¼�
		 */
		kind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(JZ_Activity.this,
						SelectPicPopupWindow.class));
			}
		});

		/**
		 * ������¼�
		 */
		number_in.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_in.setTextColor(Color.RED);
				number_out.setTextColor(Color.WHITE);
				inOrOut = 1;
				consume.setTextColor(Color.GREEN);
			}
		});

		/**
		 * ֧�����¼�
		 */
		number_out.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_in.setTextColor(Color.WHITE);
				number_out.setTextColor(Color.RED);
				inOrOut = 0;
				consume.setTextColor(Color.RED);
				System.out.println("�л���֧�����inOrOutӦ��=0��ʵ��Ϊ" + inOrOut);
			}
		});

		/**
		 * ��ťok���¼�
		 */
		button_ok.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString();
				if (consumString.length() > 0) {
//					ContentValues values = new ContentValues();
					SimpleDateFormat sDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");	
					String date = sDateFormat.format(new java.util.Date());
					float consume1 = Float.parseFloat(consumString);
					String kind = null;
//					values.put("consume", consume1);					// �������ݣ� ע��ֵ������Ҫƥ��
					// ��ArrayList�����ҵ����͵���������
					if (inOrOut == 0)
						kind = kindList.get(consumekind);
//						values.put("kind", kindList.get(consumekind));
					else {
						kind = "����";
//						values.put("kind", "����");
					}
//					values.put("date", date);
//					values.put("inorout", inOrOut);
//					values.put("id", consumekind);
//					Index_Activity.db.insert("stream", null, values);
					JZ_DAO.insertStream(consume1, kind, date, inOrOut, consumekind);
					consumString = "";					// �������������
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
					YS_DAO ys_DataBaseHelper = new YS_DAO();

					// �����֧�������Ԥ�����������������
					if (inOrOut == 0) {
						// ͬʱ�������ݿ�2��Ԥ���
						System.out.println("����ǰ");
						ys_DataBaseHelper.update(consume1, consumekind);
						System.out.println("���º�");
						// ������ʾ���
						Float remain = Float.parseFloat(budgetRemain.getText()
								.toString());
						remain = remain - consume1;
						budgetRemain.setText(remain.toString());
						System.out.println("����ɹ�");
						// db.close();
					} else {
						// ���������
						ys_DataBaseHelper.updatein(consume1);
						System.out.println("����ɹ�");
						// db.close();
					}
				}
				BackgroundColor backgroundColor = new BackgroundColor();
				backgroundColor.refreshback();
				// ��������
				String consumed = new DecimalFormat("0.0")
						.format(Index_Activity.budget - Index_Activity.remain);
				JZ_Activity.consumed.setText(consumed);
				inputCheck.setViewString("");
				Toast.makeText(JZ_Activity.this, "�ɹ�����һ��!", Toast.LENGTH_LONG)
						.show();
			}
		});
		/*
		 * ��ť����Ԥ��İ�ť
		 */
		budgetRemain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(JZ_Activity.this, YS_Activity.class);
				startActivity(intent);
			}
		});
	}
}
