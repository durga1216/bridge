package com.thread.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.bridge.Util;

public class Sqltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con=DriverManager.getConnection("jdbc:mysql://106.51.133.146:3306/mynddotz_susee","mynddotz_susi","MindPulpy12");
		    PreparedStatement st=con.prepareStatement("insert into xerodata (name,desc) values ('susee','success')");
		    st.executeUpdate();
            
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
