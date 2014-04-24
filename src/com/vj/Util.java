package com.vj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class Util {

	public static final int INDEX_SUCCESS = 1;

	public static final int INDEX_FAIL = 1;

	public static final String SOLR_SERVER_URL = "http://192.168.71.145:8080/solr";

	public static SolrServer SERVER = new HttpSolrServer(SOLR_SERVER_URL);

	public static final String SCHEMA_NAME = "name";
	public static final String SCHEMA_ADDRESS = "address";
	public static final String SCHEMA_PHONE = "Phone";
	public static final String SCHEMA_EMAIL_ADDRESS = "EmailAddress";
	public static final String SCHEMA_USERNAME = "Username";
	public static final String SCHEMA_PASSWORD = "Password";
	public static final String SCHEMA_BIRTHDAY = "Birthday";
	public static final String SCHEMA_MASTER_CARD = "MasterCard";
	public static final String SCHEMA_EXPIRES = "Expires";

	public static final String FIELD_MASTER_CARD = "fn_master_card";
	public static final String FIELD_EXPIRE_DATE = "fn_expire_date";
	public static final String FIELD_BIRTHDAY = "fn_birthday";
	public static final String FIELD_PASSWORD = "fn_password";
	public static final String FIELD_USERNAME = "fn_username";
	public static final String FIELD_EMAIL_ADDRESS = "fn_email_address";
	public static final String FIELD_PHONE = "fn_phone";
	public static final String FIELD_ADDRESS = "fn_address";
	public static final String FIELD_NAME = "fn_name";
	public static final String FIELD_ID = "id";
	

}
