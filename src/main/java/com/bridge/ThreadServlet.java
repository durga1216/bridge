package com.bridge;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thread.Threadtest;

/**
 * Servlet implementation class ThreadServlet
 */
@WebServlet("/ThreadServlet")
public class ThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		start(15);
	}
	public void start(int time){
		ArrayList<String> ar=new ArrayList<String>();
		int stime=time*60*1000;
		try{
			while(true){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);	
			    //Get all the valid Trigger data
			    PreparedStatement st=con.prepareStatement("select t1.tempid from trig_all t1 join home t2 on t1.tempid=t2.tempid where t2.state='Active' && t2.time='"+time+"'");
			    ResultSet rs1=st.executeQuery();
			    while(rs1.next()){
			    	ar.add(rs1.getString("tempid"));
			    }
				for(int i=0;i<ar.size();i++){
					MainThreadClass th=new MainThreadClass(ar.get(i));
					th.start();
				}
				try {
					Thread.sleep(stime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			
		}
	}

}
