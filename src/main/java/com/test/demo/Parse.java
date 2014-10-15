package com.test.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String test="{\"details\":{\"name\":\"susee\",\"date\":\"01,05,92\"}}";
			String ars="[{\"name\":\"susee\",\"date\":\"01,05,92\"},{\"name\":\"susee\",\"date\":\"01,05,92\"}]";
			try {
				JSONObject ob=new JSONObject(test);
				JSONArray ar=new JSONArray(ars);
				for(int i=0;i<ar.length();i++){
					
				}
				String res=ob.getString("name");
				System.out.println(res);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
