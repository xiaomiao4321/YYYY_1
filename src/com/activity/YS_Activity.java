package com.activity;

/**
 * 15-4-7
 * 	����������ɰ�ť�¼�
 * @author wcj
 */
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.dao.YS_DAO;
import com.logic.BackgroundColor;
import com.yyyy.yyyy.R;

@SuppressLint("NewApi")
public class YS_Activity extends Activity {
	// ����button��ʽ
	private Drawable drawable1;
	// ��ʾ��Ԥ����ֵ
	private TextView number_ys;
	// �༭��
	private EditText input;
	// ���ѡ����
	private RadioGroup select;
	private RadioButton clothes;
	private TextView show_clothes;
	private RadioButton eat;
	private TextView show_eat;
	private RadioButton house;
	private TextView show_house;
	@SuppressWarnings("unused")
	private RadioButton walk;
	private TextView show_walk;
	private ArrayList<TextView> show_list;// ����Ԥ�����ʾ
//	private TextView item_view;// ��Ԥ�����ʾview
	private RadioButton rb;
	// ����
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
	private Button number_float;
	private Button number_clear;
	// ȷ��
	private Button sure;
	private Button back;
	private String inputString = "";// ¼���ÿһ����ַ�������
	private String initString = "";// ��ʼֵ���ַ�������
	private String yss_total;// ��Ԥ����ַ�������
	
	//���ó�ʼ��Ϊ0������ֱ�Ӵ����ݿ��������ʼ����
	private Float ys_total;
	private float init_ys;// ÿһ��ĳ�ʼֵ��float��
	private float ys_item;// ¼���ÿһ���ֵ��float��

	// �����ͨ��list������list�ĳ�������������
	private int kind[] = { 1, 2, 3, 4 };
	private float budget[] = { 0 , 0 ,0 , 0};

	private ArrayList<Integer> kind_list = new ArrayList<Integer>();
	private YS_DAO yS_handler;
	private Cursor cursor;
	// private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ys);
		// Ԥ���ܶ�
		number_ys = (TextView) this.findViewById(R.id.number_ys);
		// �����
		input = (EditText) this.findViewById(R.id.input);
		// ���ѡ��
		select = (RadioGroup) this.findViewById(R.id.select);
		clothes = (RadioButton) this.findViewById(R.id.clothes);
		show_clothes = (TextView) this.findViewById(R.id.show_clothes);
		eat = (RadioButton) this.findViewById(R.id.eat);
		show_eat = (TextView) this.findViewById(R.id.show_eat);
		house = (RadioButton) this.findViewById(R.id.house);
		show_house = (TextView) this.findViewById(R.id.show_house);
		walk = (RadioButton) this.findViewById(R.id.walk);
		show_walk = (TextView) this.findViewById(R.id.show_walk);

		// ����
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
		number_float = (Button) this.findViewById(R.id.number_float);
		number_clear = (Button) this.findViewById(R.id.number_clear);
		// ȷ��
		sure = (Button) this.findViewById(R.id.sure);
		back = (Button) this.findViewById(R.id.back);
		/*
		 * ѡ��Ԥ�����
		 */
		/*
		 * ����button��ʽ
		 */
		drawable1 = getResources().getDrawable(R.drawable.button);
		number_0.setBackground(drawable1);
		number_1.setBackground(drawable1);
		number_2.setBackground(drawable1);
		number_3.setBackground(drawable1);
		number_4.setBackground(drawable1);
		number_5.setBackground(drawable1);
		number_6.setBackground(drawable1);
		number_7.setBackground(drawable1);
		number_8.setBackground(drawable1);
		number_9.setBackground(drawable1);
		number_float.setBackground(drawable1);
		number_clear.setBackground(drawable1);
		sure.setBackground(drawable1);
		back.setBackground(drawable1);
		// ��show_view�ŵ�һ��list��
		show_list = new ArrayList<TextView>();

		show_list.add(show_clothes);
		show_list.add(show_eat);
		show_list.add(show_house);
		show_list.add(show_walk);

		// ��Ӧ������͵�list ע�⣺һ��Ҫһһ��Ӧ
		kind_list.add(1);
		kind_list.add(2);
		kind_list.add(3);
		kind_list.add(4);
		yS_handler = new YS_DAO();
		// �����ݿ��л�ȡÿ�����͵�Ԥ��ֵ
		cursor = yS_handler.read_budget();
		// ��ȡ�α������
		int i = 0;
		System.out.println("�н��" + cursor.getCount());
		while (cursor.moveToNext()) {
			show_list.get(i).setText(
					Float.toString(cursor.getFloat(cursor
							.getColumnIndex("budget"))));
			i++;
		}
		cursor.close();
		// �����ݿ��ȡ��������Ԥ����ʾ
		String totalbudget = yS_handler.read_totalbudget();
		number_ys.setText(totalbudget);
		ys_total = Float.parseFloat(totalbudget);
		select.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int radioButtonId = group.getCheckedRadioButtonId();

				rb = (RadioButton) YS_Activity.this.findViewById(radioButtonId);
				rb.setTextColor(Color.WHITE);
				sure.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						inputString = input.getText().toString();
						rb.setTextColor(Color.BLACK);
						if (rb == clothes) {
							// ������Ľ����ʾ�ڡ��ԡ�λ��
							initString = show_clothes.getText().toString();

							init_ys = Float.parseFloat(initString);

							budget[0] = init_ys;
							kind[0] = 1;
							if (inputString.length() < 1) {
								show_clothes.setText("0");
							} else {
								show_clothes.setText(input.getText());
							}
							// ����Ԥ����뵽��Ԥ����

						} else if (rb == eat) {
							// ������Ľ����ʾ�ڡ��桱λ��
							initString = show_eat.getText().toString();
							init_ys = Float.parseFloat(initString);

							budget[1] = init_ys;
							kind[1] = 2;

							if (inputString.length() < 1) {
								show_eat.setText("0");
							} else {
								show_eat.setText(input.getText());
							}
							// ����Ԥ����뵽��Ԥ����

						} else if (rb == house) {
							// ������Ľ����ʾ�ڡ��桱λ��
							initString = show_house.getText().toString();

							init_ys = Float.parseFloat(initString);

							budget[2] = init_ys;
							kind[2] = 3;

							if (inputString.length() < 1) {
								show_house.setText("0");
							} else {
								show_house.setText(input.getText());
							}
							// ����Ԥ����뵽��Ԥ����

						} else {
							initString = show_walk.getText().toString();
							init_ys = Float.parseFloat(initString);

							budget[3] = init_ys;
							kind[3] = 4;

							if (inputString.length() < 1) {
								show_walk.setText("0");
							} else {
								show_walk.setText(input.getText());
							}
						}
						if (inputString.length() < 1) {
							ys_item = 0.0f;
						} else {
							ys_item = Float.parseFloat(inputString);
						}

						ys_total = ys_total - init_ys + ys_item;

						yss_total = String.valueOf(ys_total);// ��Ԥ��ת��Ϊ�ַ���

						number_ys.setText(yss_total);

						// ��budget[]��kind[]���뵽���ݿ���
						//yS_handler.add(budget, kind);

						inputString = "";
						input.setText(inputString.toCharArray(), 0,
								inputString.length());

					}
				});

			}
		});
		/**
		 * ��ť0���¼�
		 */
		number_0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "0";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});

		/**
		 * ��ť1���¼�
		 */
		number_1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "1";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť2���¼�
		 */
		number_2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "2";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť3���¼�
		 */
		number_3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "3";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť4���¼�
		 */
		number_4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "4";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť5���¼�
		 */
		number_5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "5";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť6���¼�
		 */
		number_6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "6";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť7���¼�
		 */
		number_7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "7";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť8���¼�
		 */
		number_8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "8";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť9���¼�
		 */
		number_9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "9";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * ��ť.���¼�
		 */
		number_float.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (inputString.length() > 0) {
					inputString = input.getText().toString() + ".";
					input.setText(inputString.toCharArray(), 0,
							inputString.length());
				}
			}
		});
		/**
		 * ��ťC���¼�
		 */
		number_clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = "";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ���Ȳ��뵽���ݿ⣬�ٽ�����activity
				YS_DAO ysHelper = new YS_DAO();
				// �õ��ж��ٸ����
				int numberOfKind = show_list.size();
				float[] budget = new float[numberOfKind];
				int[] kind = new int[numberOfKind];
				for (int i = 0; i < numberOfKind; i++) {
					// ��ȡTextView�е�Ԥ����ʾ��������
					budget[i] = Float.parseFloat(show_list.get(i).getText()
							.toString());
					kind[i] = kind_list.get(i);
				}
				Index_Activity.remain += ys_total - Index_Activity.budget;
				Index_Activity.budget = ys_total;
				// ������浽���ݿ�ɹ��������activity(������Ԥ��ͷ�Ԥ��)
				if (ysHelper.add(budget, kind) && ysHelper.addtotal(ys_total)) {
					YS_Activity.this.finish();
				}
				BackgroundColor backgroundColor = new BackgroundColor();
				backgroundColor.refreshback();
			}
		});
	}
}
