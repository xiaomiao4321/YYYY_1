package com.mnitools;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.widget.Toast;

import com.activity.JZ_Activity;
import com.inteface.INetWork;

/**
 * ����ͨ��HTTPЭ��������л�������
 * 
 * @author wcj
 *
 */
public class NetWorkCommunicate implements INetWork {
	private HttpURLConnection con = null;// ���Ӷ���

	/**
	 * ��������
	 */
	@Override
	public boolean connect(String urlString) {
		// TODO Auto-generated method stub
		boolean tag = false;
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("����urlʧ��");
		}
		try {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-java-serialized-object");
			con.setRequestMethod("POST");
			con.setConnectTimeout(3000);
			System.out.println("��������");
			con.connect();//����
			tag = true;// ���ӳɹ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ӷ�����ʧ��");
			
			Looper.prepare();
			new Handler(Looper.getMainLooper());
			Toast.makeText(JZ_Activity.jzActivity, "�ף����翪С����~",
					Toast.LENGTH_SHORT).show();
			Looper.loop();
			con.disconnect();
			
		}
		return tag;
	}

	/**
	 * ���Ͷ���
	 */
	@Override
	public <T extends Serializable> boolean sendObject(T object) {
		// TODO Auto-generated method stub
		boolean tag = false;
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(con.getOutputStream());

			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			int rsponseCode = con.getResponseCode();
			System.out.println("������:" + rsponseCode);
			if (rsponseCode == 200) {// ���ͳɹ�
				objectOutputStream.close();
				tag = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��������������쳣");
		}
		return tag;
	}

	/**
	 * ���շ��ض���
	 */
	@Override
	public Object receiveObject() {
		// TODO Auto-generated method stub
		InputStream ist = null;
		Object obj = null;
		ObjectInputStream objectInputStream = null;
		try {
			ist = con.getInputStream();
			objectInputStream = new ObjectInputStream(ist);
			obj = objectInputStream.readObject();
			System.out.println(obj);
			objectInputStream.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�Ҳ�����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("������ʱ�����쳣");
		} finally {
			con.disconnect();// �Ͽ���������
		}
		return obj;
	}

}
