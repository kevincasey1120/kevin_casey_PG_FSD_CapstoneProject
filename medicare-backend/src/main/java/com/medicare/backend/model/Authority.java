package com.medicare.backend.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author fsd developer:  kevin casey
 *
 */
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String authority;
	
	/**
	 * @param authority
	 */
	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
