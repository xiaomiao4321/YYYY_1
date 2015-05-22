package com.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CloudData implements Serializable {
	/**
	 * ���л���
	 */
	private static final long serialVersionUID = 1L;
	private String userName;	//�û���
	private String userID;		//�û�id
	private float budgetRemain;	//����Ԥ�����
	private float budget;		//����Ԥ��
	private float income;	//����
	private String date;	//ͬ������
	private ArrayList<String> budgetByKindList = new ArrayList<String>();	//���·���Ԥ��
	private ArrayList<String> streamCountList = new ArrayList<String>();	//������ˮ��Ϣ
	
	
	/**
	 * �õ�����
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * ��������
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	
	/**
	 * �õ��û���
	 * @return
	 */
	public String getUserName(){
		return userName;
	}
	
	
	/**
	 * �����û���
	 * @return
	 */
	public void setUserName(String username){
		this.userName = username;
	}
	
	
	/**
	 * �õ��û�id
	 * @return
	 */
	public String getUserID(){
		return userID;
	}
	
	
	/**
	 * �����û�id
	 * @return
	 */
	public void setUserNameID(String userid){
		this.userID = userid;
	}
	
	/**
	 * �õ�����Ԥ��ʣ��
	 * @return
	 */
	public float getBugetRemain(){
		return budgetRemain;
	}
	
	/**
	 * ���ñ���Ԥ��ʣ��
	 * @return
	 */
	public void setBudgetRemain(float budgetRemain){
		this.budgetRemain = budgetRemain;
	}
	
	/**
	 * �õ�������Ԥ��
	 * @return
	 */
	public float getBuget(){
		return budget;
	}
	
	/**
	 * ���ñ�����Ԥ��
	 * @return
	 */
	public void setBudget(float budget){
		this.budget = budget;
	}
	
	/**
	 * �õ����������ܶ�
	 * @return
	 */
	public float getincome(){
		return income;
	}
	
	/**
	 * ���ñ��������ܶ�
	 * @return
	 */
	public void setincome(float income){
		this.income = income;
	}
	
	/**
	 * ���һ������Ԥ����Ϣ 
	 * @param item ��ϸ�ʽ:  	���&Ԥ��&Ԥ�����
	 */
	public void addBudgetByKindList(String item){
		budgetByKindList.add(item);
	}
	
	/**
	 * �õ��������з���Ԥ����Ϣ
	 */
	public ArrayList<String> getBudgetByKindList(){
		return streamCountList;
	}
	
	/**
	 * ���һ����ˮ��Ϣ
	 * @param item ��ϸ�ʽ�� 	���ѽ��&�������&����ʱ��&�����֧��
	 */
	public void addStreamCountList(String item) {
		streamCountList.add(item);
	}
	
	/**
	 * �õ���ˮlist
	 * @return
	 */
	public ArrayList<String> getStreamCountList(){
		return streamCountList;
	}
}
