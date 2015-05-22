package com.model.street;

import com.activity.Publish_Activity;
import com.activity.Street_Activity;
import com.bean.StreetMessageBean;
import com.inteface.INetWork;
import com.mnitools.NetWorkCommunicate;

import android.content.Intent;

/**
 * ����״̬
 * @author wcj
 *
 */
public class PublishConnecter {
	private INetWork communicater;

	public PublishConnecter() {
		this.communicater = new NetWorkCommunicate();
	}
	
	/**
	 * ���������������ӷ�ʽ
	 * @param netWork
	 */
	public <T extends INetWork> void setNetWork(T netWork){
		this.communicater = netWork;
	}
	
	/**
	 * ������Ϣ����
	 * @param messageBean
	 */
	public void send(StreetMessageBean messageBean) {
		// TODO Auto-generated method stub
		String urlString = "http://192.168.191.1:8080/Bill/servlet/PublishServlet";
		String ok = "";
		if (communicater.connect(urlString))
			if (communicater.sendObject(messageBean)) {
				ok = (String) communicater.receiveObject();
			}
		if (ok.equals("1")) {
			// ��ת��ȥˢ��
			Intent intent = new Intent(Publish_Activity.publish_Activity,
					Street_Activity.class);
			Publish_Activity.publish_Activity.startActivity(intent);
		}
	}
}
