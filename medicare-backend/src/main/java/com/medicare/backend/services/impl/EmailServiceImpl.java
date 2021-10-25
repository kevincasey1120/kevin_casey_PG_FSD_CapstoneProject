package com.medicare.backend.services.impl;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medicare.backend.repository.EmailRepository;
import com.medicare.backend.repository.UserRepository;
import com.medicare.backend.services.EmailService;

 /* @author fsd developer:  kevin casey
 *
 */
@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private EmailRepository emailRepository;
	
	//@Value("${jwt.elasticEmailAccountEmail}")
	//private static String elasticEmailAccountEmail;
	
	//@Value("${jwt.elasticEmailApiKey}")
	//private static String elasticEmailApiKey;
	
	
	@Override
	public String sendElasticEmail(String from, String fromName, String subject, String body) {
		String elasticEmailAccountEmail = "@yahoo.com";
		String elasticEmailApiKey = "17F66401CE3962949859414EF90FE28FF99A879F722FCD350D764B00A78A9CF7356DE9052E00EBCED56DF40042";
		try {
			// Construct the data
			String data = "userName=" + URLEncoder.encode("kevin.casey1973"+elasticEmailAccountEmail, "UTF-8");
			data += "&api_key=" + URLEncoder.encode("17F66401"+elasticEmailApiKey+"FF24DA", "UTF-8");
			data += "&from=" + URLEncoder.encode(from, "UTF-8");
			data += "&from_name=" + URLEncoder.encode(fromName, "UTF-8");
			data += "&subject=" + URLEncoder.encode(subject, "UTF-8");
			data += "&body_html=" + URLEncoder.encode(body, "UTF-8");
			data += "&to=" + URLEncoder.encode(elasticEmailAccountEmail, "UTF-8");

			// Send data
			URL url = new URL("https://api.elasticemail.com/mailer/send");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = rd.readLine();
			wr.close();
			rd.close();

			return data;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
