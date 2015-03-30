package com.thread.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bridge.Util;

public class Sqltest {

	public static void main(String[] args) {
		// TODO Auto-generated method git
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection(
							"jdbc:mysql://data.nextepbusinessbuilder.com:13306/accounting_api",
							"minddots", "4_ujuraG");
			Statement stmt = connection.createStatement();

			// String sql = "CREATE TABLE mind_data (`data1` VARCHAR( 500 ) NULL DEFAULT NULL , `data2` VARCHAR( 500 ) NULL DEFAULT NULL , `data3` VARCHAR( 500 ) NULL DEFAULT NULL ,`data4` VARCHAR( 500 ) NULL DEFAULT NULL ,`data5` VARCHAR( 500 ) NULL DEFAULT NULL ,`data6` VARCHAR( 500 ) NULL DEFAULT NULL ,`data7` VARCHAR( 500 ) NULL DEFAULT NULL ,`data8` VARCHAR( 500 ) NULL DEFAULT NULL ,`data9` VARCHAR( 500 ) NULL DEFAULT NULL ,`data10` VARCHAR( 500 ) NULL DEFAULT NULL)";
			//String sql="insert into mind_data (data1,data2) values('sample data','sample data')";
			 // String sql =
			// "insert into Mdots_test (id,first,last,age) values ('1','susee','minddotss','23')";
			String sql = "Select * from mind_data";
		///	stmt.executeUpdate(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("data1"));
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
}
