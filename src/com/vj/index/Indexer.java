package com.vj.index;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.vj.Util;

public class Indexer {

	private SolrServer server = Util.SERVER;

	/**
	 * index a object, return result tag : 0-fail, 1-success
	 * 
	 * @param obj
	 * @return
	 */
	// TODO
	public int index(Object obj) {

		return 0;
	}

	public int indexFile(String fileName) {
		try {
			String id = fileName;
	
			fileName = "src/com/vj/index/data/" + fileName;
			Properties p = new Properties();
			p.load(new FileReader(fileName));

			String name = p.getProperty(Util.SCHEMA_NAME);
			String address = p.getProperty(Util.SCHEMA_ADDRESS);
			String Phone = p.getProperty(Util.SCHEMA_PHONE);
			String Email_Address = p.getProperty(Util.SCHEMA_EMAIL_ADDRESS);
			String Username = p.getProperty(Util.SCHEMA_USERNAME);
			String Password = p.getProperty(Util.SCHEMA_PASSWORD);
			String Birthday = p.getProperty(Util.SCHEMA_BIRTHDAY);
			String MasterCard = p.getProperty(Util.SCHEMA_MASTER_CARD);
			String Expires = p.getProperty(Util.SCHEMA_EXPIRES); // TODO  date type

			SolrInputDocument doc = new SolrInputDocument();
			doc.addField(Util.FIELD_ID, id);
			doc.addField(Util.FIELD_NAME, name);
			doc.addField(Util.FIELD_ADDRESS, address);
			doc.addField(Util.FIELD_PHONE, Phone);
			doc.addField(Util.FIELD_EMAIL_ADDRESS, Email_Address);
			doc.addField(Util.FIELD_USERNAME, Username);
			doc.addField(Util.FIELD_PASSWORD, Password);
			doc.addField(Util.FIELD_BIRTHDAY, Birthday);
			doc.addField(Util.FIELD_MASTER_CARD, MasterCard);
			doc.addField(Util.FIELD_EXPIRE_DATE, Expires);
			

			UpdateResponse ursp = server.add(doc);
			server.commit();
			
			int status = ursp.getStatus(); // 0 : good.
			
			return status;			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
