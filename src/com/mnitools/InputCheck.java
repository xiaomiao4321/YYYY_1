package com.mnitools;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.inteface.IInputCheck;

public class InputCheck implements IInputCheck {

	private String viewString = null;//��ʾ�ַ���
	private TextView editText = null;//������ʾtextView
	public InputCheck(TextView editText,String viewString) {
		// TODO Auto-generated constructor stub
		this.viewString = viewString;
		this.editText = editText;
	}	
	
	@Override
	public void setLisener_number(Button button,final String number) {
		// TODO Auto-generated method stub
		
		/**
		 * ��ť�¼�
		 */
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(viewString)) { // ��������Ƿ�Ϸ�
					viewString += number;
					editText.setText(viewString.toCharArray(), 0,
							viewString.length());
				}
			}
		});
	}

	@Override
	public void setLisener_float(Button button, final String point) {
		// TODO Auto-generated method stub
		// this.number = point;
		/**
		 * ��ť.���¼�
		 */
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(viewString)) {
					if (viewString.length() > 0
							&& (viewString.indexOf(".") < 0)) {
						viewString += point;
						editText.setText(viewString.toCharArray(), 0,
								viewString.length());
					}
				}
			}
		});
	}

	/**
	 * ��������Ƿ�Ϸ�
	 * 
	 * @param viewString
	 *            �༭���ַ���
	 * @return
	 */
	public boolean check(String viewString) {
		boolean TAG = false;
		// ������
		if (viewString.contains(".")) {
			if (viewString.length() - viewString.indexOf(".")< 3) {
				TAG = true;
			}
		} else {// ����
			if (viewString.length() < 6) {
				TAG = true;
			}
		}
		return TAG;
	}

	@Override
	public void setLisener_clear(Button button) {
		// TODO Auto-generated method stub
		/**
		 * ��ťC���¼�
		 */
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewString = "";
				editText.setText(viewString.toCharArray(), 0,
						viewString.length());
			}
		});
	}
	
	@Override
	public void setViewString(String string){
		this.viewString = string;
	}
}
