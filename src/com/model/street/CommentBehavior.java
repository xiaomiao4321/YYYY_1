package com.model.street;

import com.bean.CommentBean;
import com.bean.CommentBeanList;
import com.inteface.INetWork;
import com.mnitools.NetWorkCommunicate;

public class CommentBehavior {
	private INetWork communicater;
	
	public CommentBehavior(){
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
	 * ����������Ϣ
	 * @param imessageid ԭʼ��״̬id
	 * @return
	 */
	public CommentBeanList getComments(String imessageid){
		String urlString = "";
		CommentBeanList commentBeanList= null;
		if(communicater.connect(urlString))
			if(communicater.sendObject(imessageid)){
				commentBeanList = (CommentBeanList)communicater.receiveObject();
			}
		return commentBeanList;
	}
	
	/**
	 * ��������
	 * @param commentBean ������Ϣ
	 * @return
	 */
	public boolean sendComment(CommentBean commentBean){
		boolean tag = false;
		String urlString = "";
		String ok = null;
		if(communicater.connect(urlString))
			if(communicater.sendObject(commentBean)){
				ok = (String)communicater.receiveObject();
			}
		if(ok.equals("1"))
			tag = true;
		return tag;
	}
}
