package com.inteface;

public interface IBasicDAO {
	abstract boolean connectDataBase(String dbName);
	abstract Object selectCursor(String sql);//����ֱ�ӷ��ؾ������ͣ��������
	abstract float selectFloat(String sql);
	abstract int selectInt(String sql);
	abstract String selectString(String sql);
	abstract boolean insert(String sql);
	abstract boolean update(String sql);
	abstract boolean drop(String sql);
	abstract boolean isConnect();
}
