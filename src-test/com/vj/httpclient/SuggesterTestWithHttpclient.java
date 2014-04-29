package com.vj.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

/*
 * configuration of the Suggester is:
 * in solrconfig.xml, add:
1.
<searchComponent class="solr.SpellCheckComponent" name="vjsuggester">
	<lst name="spellchecker">
	<str name="name">vjsuggester</str>
	<str name="classname">org.apache.solr.spelling.suggest.Suggester</str>
	<str name="lookupImpl">org.apache.solr.spelling.suggest.tst.TSTLookup</str>
	<str name="field">fn_name</str>
	<!-- no idea why the threshold always throw exception -->
	<!--<str name="threshold">2</str>-->
	</lst>
</searchComponent>

2.
<requestHandler class="org.apache.solr.handler.component.SearchHandler" name="/vjsuggester">
	<lst name="defaults">
	<str name="spellcheck">true</str>
	<str name="spellcheck.dictionary">vjsuggester</str>
	<str name="spellcheck.count">10</str>
	</lst>
	<arr name="components">
	<str>vjsuggester</str>
	</arr>
</requestHandler>
 */
public class SuggesterTestWithHttpclient {

	//http://192.168.71.145:8080/solr/vjsuggester/?q=c
	@Test
	public void testSuggester() throws ClientProtocolException, IOException{
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://192.168.71.145:8080/solr/vjsuggester/?q=c");
		HttpResponse rsp = httpclient.execute(httpGet);
		StatusLine statusLine = rsp.getStatusLine();
		System.out.println(statusLine);
		HttpEntity entity = rsp.getEntity();
		if(statusLine.getStatusCode() >= 300){
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		if(entity==null){
			throw new ClientProtocolException("Response contains no content");
		}
		
		ContentType contentType = ContentType.getOrDefault(entity);
		Charset charset = contentType.getCharset();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		String str;
		while((str=br.readLine())!=null){
			System.out.println(str);
		}
		
		Assert.assertFalse("fail", true);
	}
}
