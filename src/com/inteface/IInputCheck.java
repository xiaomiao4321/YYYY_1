package com.inteface;

import android.widget.Button;

public interface IInputCheck {
	abstract void setLisener_number(Button button,String number);//����0~9�Ǽ��
	abstract void setLisener_float(Button button,String point);//����.ʱ���
	abstract void setLisener_clear(Button button);//�������ʱ���
	abstract boolean check(String consumeString);//����Ƿ�Ϸ�������
	abstract void setViewString(String string);//�����ַ���
}
