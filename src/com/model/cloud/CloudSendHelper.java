package com.model.cloud;

import java.net.MalformedURLException;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.activity.JZ_Activity;
import com.bean.CloudData;
import com.dao.SySearch_DAO;
import com.inteface.INetWork;
import com.mnitools.GetNowDate;
import com.mnitools.NetWorkCommunicate;
import com.model.user.IsLogin;

/**
 * �ƶ�ͬ��������ͬ������
 */
public class CloudSendHelper {

	private CloudData cloudData = new CloudData();
	private SySearch_DAO search;
	private Cursor cursor;
	private String currentStringShort;
	private IsLogin IsLogin;
	private INetWork communicater;

	@SuppressLint("SimpleDateFormat")
	public CloudSendHelper() {
		this.search = new SySearch_DAO();
		this.IsLogin = new IsLogin();
		this.communicater = new NetWorkCommunicate();
		currentStringShort = (new GetNowDate()).getNowDate("yyyy-mm");
	}

	/**
	 * �����������ӷ�ʽ��Ĭ��httpConnection���ӣ�
	 * @param netWorkWay ʵ����INetWork�����ӷ�ʽ
	 */
	public <T extends INetWork> void setNetWorkWay(T netWorkWay){
		this.communicater = netWorkWay;
	}
	/**
	 * ����Ƿ��½����½��ͬ�����������ѵ�½
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkAndSend() throws MalformedURLException,
			ClassNotFoundException {
		boolean ok = false;
		if (IsLogin.isLogin()) {
			ok = send();
		}
		return ok;
	}

	/**
	 * ���ӷ�����������cloudData����
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	@SuppressLint("ShowToast")
	public boolean send() throws MalformedURLException, ClassNotFoundException {
		boolean tag = false;
		new Thread() {
			public void run() {
				setcloudData();//��װ����
				String url = "http://192.168.191.1:8080/Bill/servlet/ReceiveServlet";
				if (communicater.connect(url))//�������ӳɹ�
					if (communicater.sendObject(cloudData)) {//�������ݳɹ�
						Object responseObject = communicater.receiveObject();//��ȡ���ض���
						System.out.println("���ض���" + responseObject.toString());
						
						Looper.prepare();
						new Handler(Looper.getMainLooper());
						Toast.makeText(JZ_Activity.jzActivity, "ͬ���ɹ�",
								Toast.LENGTH_SHORT).show();
						Looper.loop();
					}
			};
		}.start();
		tag = true;
		return tag;
	}

	/**
	 * �����ݶ����װ
	 * 
	 * @param cloudData
	 */
	public void setcloudData() {

		// ��װ�û�id
		cloudData.setUserNameID(search.searchId());

		// ��װ��ˮ
		cursor = search.searchStreamCount();
		String stream = "";
		String kind = "";
		String consume = "";
		String inorout = "";
		String date = "";
		while (cursor.moveToNext()) {
			consume = cursor.getString(cursor.getColumnIndex("consume"));
			date = cursor.getString(cursor.getColumnIndex("date"));
			inorout = cursor.getString(cursor.getColumnIndex("inorout"));
			kind = cursor.getString(cursor.getColumnIndex("kind"));
			stream = consume + "&" + kind + "&" + date + "&" + inorout;
			cloudData.addStreamCountList(stream);
		}

		// ��װÿ�·���Ԥ��
		cursor = search.searchBudgetByKind(currentStringShort);
		String budget = "";
		String remain = "";
		String all = "";
		while (cursor.moveToNext()) {
			budget = cursor.getString(cursor.getColumnIndex("budget"));
			kind = cursor.getString(cursor.getColumnIndex("kind"));
			remain = cursor.getString(cursor.getColumnIndex("remain"));
			all = kind + "&" + budget + "&" + remain;
			cloudData.addBudgetByKindList(all);
		}

		// ��װ��Ԥ��
		float totalbudget;
		float budgetRemain;
		cursor = search.searchBudget(currentStringShort);
		if (cursor.moveToNext()) {
			totalbudget = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("totalbudget")));
			budgetRemain = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("remain")));
			cloudData.setBudget(totalbudget);
			cloudData.setBudgetRemain(budgetRemain);
		} else {
			System.out.println("��ȡ��Ԥ��ʱδ�����κ�����");
		}

		// ��װ����
		float income;
		cursor = search.searchincome(currentStringShort);
		if (cursor.moveToNext()) {
			income = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("mony")));
			cloudData.setincome(income);
		} else {
			System.out.println("δ��ȡ������");
		}

		// ����ͬ��ʱ��
		search.updateTime();
		System.out.println("�������׼����ϣ�");
	}
}
