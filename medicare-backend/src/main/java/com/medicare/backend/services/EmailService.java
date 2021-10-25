package com.medicare.backend.services;


public interface EmailService {
	public String sendElasticEmail(String from, String fromName, String subject, String body);
}
