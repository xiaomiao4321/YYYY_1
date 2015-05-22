package com.model.street;

import java.util.ArrayList;
import com.bean.SrStreetBeanList;
import com.bean.StreetMessageBean;
import com.inteface.INetWork;
import com.mnitools.NetWorkCommunicate;

//import android.os.StrictMode;

public class StreetConnecter{
	private String adrees = "�й�ʯ�ʹ�ѧ";
	private INetWork communicater;
	public StreetConnecter() {
		// TODO Auto-generated constructor stub
		this.communicater = new NetWorkCommunicate();
	}
	
	/**
	 * ���������������ӷ�ʽ
	 * @param netWork
	 */
	public <T extends INetWork> void setNetWork(T netWork){
		this.communicater = netWork;
	}
	
	public void connect() {
		// TODO Auto-generated method stub
		String urlString = "http://192.168.191.1:8080/Bill/servlet/StreetServlet";
		if(communicater.connect(urlString))
			if(communicater.sendObject(adrees)){
				Object obj = communicater.receiveObject();
				SrStreetBeanList streetBean = (SrStreetBeanList)obj;//ת��Ϊ���л�����
				ArrayList<StreetMessageBean> messageBeans = streetBean.getList();//��ȡ�б�
				GUIManager guiManager = new GUIManager();
				guiManager.createStreetGUI(messageBeans);//��ʾ������
			}
//		URL url;
//		try {
//			url = new URL("http://192.168.191.1:8080/Bill/servlet/StreetServlet");
//
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
//			System.out.println("���ӳɹ�!");
//			// ��������
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
//					new BufferedOutputStream(con.getOutputStream()));
//			objectOutputStream.writeObject(adrees);// ���͵�ַ
//			objectOutputStream.flush();
//			objectOutputStream.close();
//			System.out.println("���ͳɹ�");
//			
//			//��ȡ���ؽ����Ϣ
//			ObjectInputStream objectInputStream = new ObjectInputStream(
//					new BufferedInputStream(con.getInputStream()));
//			Object obj = objectInputStream.readObject();
//			
//			SrStreetBeanList streetBean = (SrStreetBeanList)obj;//ת��Ϊ���л�����
//			ArrayList<StreetMessageBean> messageBeans = streetBean.getList();//��ȡ�б�
//			System.out.println("�յ�������Ϣ");
//			objectInputStream.close();
//			
//			//��ʾ������
//			GUIManager guiManager = new GUIManager();
//			guiManager.createStreetGUI(messageBeans);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			StreetGUI streetGUI = new StreetGUI(Street_Activity.rootlinearLayout);
//			streetGUI.createErrorGUI();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
