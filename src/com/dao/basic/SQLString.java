package com.dao.basic;

public class SQLString {

	/*
	 * JZ_DAO
	 */
	public static String getUpdateBudgetRemain_JZ(String currentString) {
		String sql = "select remain,totalbudget from tabletotalbudget where month = '"
				+ currentString + "'";
		return sql;
	}

	public static String getInsertStream(float consum1, String kind,
			String date, int inOrOut, int consumekind) {
		String sql = "insert into stream values(" + consum1 + ", '" + kind
				+ "'," + consumekind + ",'" + date + "'," + inOrOut + ")";
		return sql;
	}

	/*
	 * LS_DAO
	 */
	public static String getSelectAllAccount_LS(String dateString) {
		String sql = "select consume, kind, date, inorout from stream where date >= '"
				+ dateString
				+ "' and date < date('"
				+ dateString
				+ "', '+1 month') order by date desc";
		return sql;
	}

	/*
	 * SySearch_DAO
	 */
	public static String getSearchId_Sy() {
		String sql = "select id from user";
		return sql;
	}

	public static String getSearchStreamCount_Sy(String time) {
		String sql = "select * from stream where date > '" + time + "'";
		return sql;
	}

	public static String getSearchBudget_Sy(String datetime) {
		String sql = "select * from tabletotalbudget where month = '"
				+ datetime + "'";
		return sql;
	}

	public static String getSearchincome_Sy(String datetime) {
		String sql = "select * from consumein where month = '" + datetime + "'";
		return sql;
	}

	public static String getSearchBudgetByKind_Sy(String datetime) {
		String sql = "select * from tablebudget where month = '" + datetime
				+ "'";
		return sql;
	}

	public static String getSytime_Sy() {
		String sql = "select sytime from time";
		return sql;
	}

	public static String getUpdateTime_Sy(String currenString) {
		String sql = "update time set sytime = '" + currenString + "'";
		return sql;
	}

	/*
	 * TJ_DAO
	 */
	// 获取总的已经花的钱
	public static String getTotalConsumeString(String date) {
		
		String sql = "select totalbudget-remain as total_consume from tabletotalbudget where month ='"
				+ date + "'";
		return sql;
	}

	// 获取每一类别的总的花过的钱数
	public static String getTypeConsumeString(String date) {
		
		String sql = "select budget-remain as sum_consume from tablebudget where month = '"
				+ date + "'";
		return sql;
	}

	// 获取每一天的某一类别的已花钱数，作为折线图的纵坐标
	public static String getDayTypeConsumeString(int type, String date) {
		
		String sql = "select sum(consume)  as totoalConsume from stream where id = "
				+ type
				+ " and date >= '"
				+ date
				+ "-01"
				+ "' and date <= date('"
				+ date
				+ "-01','+1 month') group by strftime('%d',date)";

		return sql;
	}

	// 获取花钱的日期，作为折线图的横坐标
	public static String getDayString(int type, String date) {
		
		String sql = "select distinct strftime('%d',date)  as day from stream where id = "
				+ type
				+ " and date >= '"
				+ date
				+ "-01"
				+ "' and date <= date('" + date + "-01','+1 month')";

		return sql;
	}

	/*
	 * YS_DAO
	 */
	public static String getBudget_Ys(String currentString) {
		String sql = "select budget from tablebudget where month = '"
				+ currentString + "'";
		return sql;
	}

	public static String getTotalbudget_Ys(String currentString) {
		String sql = "select totalbudget from tabletotalbudget where month = '"
				+ currentString + "'";
		return sql;
	}

	public static String getAddBudget_Ys(float budget, int kind,
			String currentString) {
		String sql = "update tablebudget set budget = " + budget
				+ ", remain = remain - budget + " + budget + " where kind = "
				+ kind + " and month = '" + currentString + "'";
		return sql;
	}

	public static String getAddtotal_Ys(float totalbudget, String currentString) {
		String sql = "update tabletotalbudget set remain = remain - totalbudget + "
				+ totalbudget + " where month = '" + currentString + "'";
		return sql;
	}

	public static String getAddtotal1_Ys(float totalbudget, String currentString) {
		String sql = "update tabletotalbudget set totalbudget = " + totalbudget
				+ " where month = '" + currentString + "'";
		return sql;
	}

	public static String getDeltotal_Ys(float consume, String currentString) {
		String sql = "update tabletotalbudget set remain = remain -" + consume
				+ " where month = '" + currentString + "'";
		return sql;
	}

	public static String getUpdate_Ys(float consume, int kind,
			String currentString) {
		String sql = "update tablebudget set remain = remain -" + consume
				+ " where kind = " + kind + " and month = '" + currentString
				+ "'";
		return sql;
	}

	public static String getUpdatein_Ys(float in, String currentString) {
		String sql = "update consumein set mony = mony + " + in
				+ " where month = '" + currentString + "'";
		return sql;
	}
	/**
	 * Borrow_Return Manager
	 * @author LLL
	 * @return
	 */
	/**
	 * 根据类型获取总和
	 * @param kind
	 * @return
	 */
	public static String getTotal(int kind){
		String sql = "select sum(money) as totalborrow from borrow_manager where kind = "+kind; 
		return sql;
	}
	public static String  getList(int kind) {
		String sql = "select userId,money,return_time from borrow_manager where kind = "+kind;
		return sql;
	}
	
	
	/*
	 * Init
	 */
	public static String getLastdate_In() {
		String sql = "select lastdate from time";
		return sql;
	}

	public static String getUpdateLastdate_In(String currentString) {
		String sql = "update time set lastdate = " + currentString;
		return sql;
	}

	public static String getInitNewTotalBudget_In(String currentString) {
		String sql = "insert into tabletotalbudget(totalbudget,remain, month) values (0,0,'"
				+ currentString + "')";
		return sql;
	}

	public static String getInitNewincome_In(String currentString) {
		String sql = "insert into consumein(mony, month) values (0, '"
				+ currentString + "')";
		return sql;
	}

	public static String getInitNewKindBudget_In(int kind, String currentString) {
		String sql = "insert into tablebudget(budget, kind, remain, month) values(0, "
				+ kind + ", 0,'" + currentString + "')";
		return sql;
	}

	/*
	 * IsLogin
	 */
	public static String getIsLogin_Is() {
		String sql = "select tag from user";
		return sql;
	}

	/*
	 * Regist
	 */
	public static String getUpdateStateId_Re(String idAndPassword) {
		String sql = "update user set id = " + idAndPassword;
		return sql;
	}

	public static String getUpdateStateTag_Re() {
		String sql = "update user set tag = 1";
		return sql;
	}

	/*
	 * User_DAO
	 */
	public static String getUserId_Us() {
		String sql = "select id from user";
		return sql;
	}

	public static String getUserName_Us() {
		String sql = "select name from user";
		return sql;
	}

	public static String getALLMessages_Us() {
		String sql = "select * from user";
		return sql;
	}

	public static String getFreshButton_Co(int mainkind) {
		String sql = "select kindname from kind where firstid = " + mainkind;
		return sql;
	}
}
