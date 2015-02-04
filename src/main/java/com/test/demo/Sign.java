package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import sun.misc.BASE64Encoder;

public class Sign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String url = "http://test.api.zepo.in/store/63/orders?page=1&pageSize=10";
			String bas = "63";
			String base="GET\n/store/@@63@@/orders?page=1&pageSize=10";
			String[] bbs=base.split("@@");
			bbs[1]=bas;
			String bas1="";
			for(int k=0;k<bbs.length;k++){
					bas1+=bbs[k];
				}
			base=URLEncoder.encode(bas1, "UTF-8");
			System.out.println(base+"\nGET%0A%2Fstore%2F63%2Forders%3Fpage%3D1%26pageSize%3D10\n");
			String ckey = "D4134D6E225CF52AE2896D8F2DC99";
			String skey = "17A851B52571CD941516C5C16EB2C";
			String auth = "Authorization: ZEPO D4134D6E225CF52AE2896D8F2DC99:NDAyYTg1NmM2NWJiNmNkODM4MGU2MzZkNjM0MTY1ZDZmZjVhMzliNw==";
			String sign = computeSignature(base, skey);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
			String auth1 = "Authorization: ZEPO D4134D6E225CF52AE2896D8F2DC99:"+sign;
			HttpClient cli = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			get.addHeader(auth1, "");
			HttpResponse res = cli.execute(get);
			BufferedReader bf = new BufferedReader(new InputStreamReader(res
					.getEntity().getContent()));
			String line = "";
			String str = "";
			while ((line = bf.readLine()) != null) {
				str += line;
			}
			System.out.println(str);
		} catch (Exception e) {
		}
	}
	private static String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKey secretKey = null;
		byte[] keyBytes = keyString.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        String orig = new String(Hex.encodeHex(mac.doFinal(baseString.getBytes())));
         byte[] encoded = Base64.encodeBase64(orig.getBytes()); 
        return new String(encoded);
	}
}
