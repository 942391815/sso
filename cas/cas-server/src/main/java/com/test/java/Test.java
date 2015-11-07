package com.test.java;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test extends AbstractUsernamePasswordAuthenticationHandler {

	private JdbcTemplate jdbcTemplate;
	@Override 
	protected HandlerResult authenticateUsernamePasswordInternal(
			UsernamePasswordCredential credential) throws GeneralSecurityException,
			PreventedException {
		String password = credential.getPassword();
		String username = credential.getUsername();
		System.out.println(password+"\t"+username);
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from user where name='"+username+"' and pass='"+password+"'");
		if(queryForList!=null&&queryForList.size()>0){
			  return createHandlerResult(credential, new SimplePrincipal(username), null);
		}
		return null;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
