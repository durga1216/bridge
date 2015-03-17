package com.thread.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bridge.Util;

public class Sqltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con=DriverManager.getConnection("jdbc:mysql://127.12.212.2:3306/bridge", "admin7R9w6e8", "5n4gq2Pz4q_b");
		    PreparedStatement paa=con.prepareStatement("insert into testpol (error,str,tempid) values('sfsasfas','remote','login')");
			paa.executeUpdate();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			Date d1 = format.parse("2015-03-01");
//			Date d2 = format.parse("2015-03-02");
//			long diff = d2.getTime() - d1.getTime();
//			long diffDays = diff / (24 * 60 * 60 * 1000);
//            System.out.println(diffDays);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
