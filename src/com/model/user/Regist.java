package com.model.user;

import com.activity.Index_Activity;
import com.activity.User_Activity;
import com.dao.basic.SQLString;
import com.inteface.INetWork;
import com.mnitools.NetWorkCommunicate;

import android.content.Intent;
import android.widget.Toast;

public class Regist {
	private String idAndPassword;
	private INetWork communicater;
	public Regist() {
		this.communicater = new NetWorkCommunicate();
	}

	/**
	 * ʹ���������ӷ�ʽ
	 * @param netWork
	 */
	public <T extends INetWork> void setNetWork(T netWork){
		this.communicater = netWork;
	}
	
	public void getNewID() {
//		int tag = -1;
//		String sql = "select tag from user";
//		ISelect selecter = new SelectInt(Index_Activity.db);
//		tag = (int)selecter.select(sql);
		if (!IsLogin.isLogin()) {
			connect();
			updateState(idAndPassword);
			Intent intent = new Intent(User_Activity.user_Activity, Index_Activity.class);
			User_Activity.user_Activity.startActivity(intent);
		} else {
			Toast.makeText(User_Activity.user_Activity, "�Ѿ���ȡ!��Ҫ̰������-_-||",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * ���ӷ����������id
	 */
	public void connect() {
		String urlString = "http://192.168.191.1:8080/Bill/servlet/RegistServlet";
		if(communicater.connect(urlString))
			if(communicater.sendObject("")){
				idAndPassword = (String)communicater.receiveObject();
			}
//		URL url = null;
//		try {
//			url = new URL("http://192.168.191.1:8080/Bill/servlet/RegistServlet");
//		} catch (MalformedURLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			// ����
//
//			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//					.detectDiskReads().detectDiskWrites().detectNetwork()
//					.penaltyLog().build());
//			System.out.println("��ʼ����");
//			URLConnection con = url.openConnection();
//			System.out.println("������");
//			con.setDoInput(true);
//			con.setDoOutput(true);
//			con.setConnectTimeout(5000);
//			System.out.println("��������");
//			con.connect();
//			// ��������
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
//					new BufferedOutputStream(con.getOutputStream()));
//			objectOutputStream.writeObject("");
//			objectOutputStream.flush();
//			objectOutputStream.close();
//			System.out.println("���ͳɹ�");
//
//			ObjectInputStream objectInputStream = new ObjectInputStream(
//					new BufferedInputStream(con.getInputStream()));
//			idAndPassword = (String) objectInputStream.readObject();
//			System.out.println(idAndPassword);
//			objectInputStream.close();
//			System.out.println("��ȡ����id��Ϊ:"+ idAndPassword);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * �������ݿ��
	 * 
	 * @param idAndPassword
	 */
	public void updateState(String idAndPassword) {
//		String sql = "update user set id = " + idAndPassword;
//		UpdateSQL updateSQL = new UpdateSQL(Index_Activity.db);
//		updateSQL.updateSQLite(sql);
//		sql = "update user set tag = 1";
//		updateSQL.updateSQLite(sql);
		String sql = SQLString.getUpdateStateId_Re(idAndPassword);
		Index_Activity.basicDAO.update(sql);
		sql = SQLString.getUpdateStateTag_Re();
		Index_Activity.basicDAO.update(sql);
		System.out.println("id��Ϊ"+ idAndPassword);
	}
}
