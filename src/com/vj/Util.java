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

}
