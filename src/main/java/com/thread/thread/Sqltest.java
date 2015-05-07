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

//			 String sql = "CREATE TABLE IF NOT EXISTS `minddots_qb_company_info` (\n" +
//					 "  `CompanyName` varchar(500) DEFAULT NULL,\n" +
//					 "  `CompanyStartDate` varchar(500) DEFAULT NULL,\n" +
//					 "  `FiscalYearStartMonth` varchar(500) DEFAULT NULL,\n" +
//					 "  `CompanyAddr` varchar(500) DEFAULT NULL,\n" +
//					 "  `PrimaryPhone` varchar(500) DEFAULT NULL,\n" +
//					 "  `Email` varchar(500) DEFAULT NULL,\n" +
//					 "  `domain` varchar(500) DEFAULT NULL,\n" +
//					 "  `CreateTime` varchar(500) DEFAULT NULL,\n" +
//					 "  `Country` varchar(500) DEFAULT NULL,\n" +
//					 "  `SupportedLanguages` varchar(500) DEFAULT NULL,\n" +
//					 "  `num` int(50) NOT NULL AUTO_INCREMENT,\n" +
//					 "  PRIMARY KEY (`num`)\n" +
//					 ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			//String sql="insert into mind_data (data1,data2) values('sample data','sample data')";
			 // String sql =
			// "insert into Mdots_test (id,first,last,age) values ('1','susee','minddotss','23')";
			String sql = "Select * from minddots_qb_company_info";
			//stmt.executeUpdate(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("CompanyAddr"));
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
