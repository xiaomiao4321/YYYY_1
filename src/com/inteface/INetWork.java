package com.inteface;

import java.io.Serializable;

/**
 * �������ݴ���淶
 * @author wcj
 *
 */
public interface INetWork {
	abstract boolean connect(String urlString);//������������
	abstract <T extends Serializable>  boolean sendObject(T object);//Ҫ���͵Ķ���������л�
	abstract Object receiveObject();//���շ�������
}
