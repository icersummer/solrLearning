package com.vj.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 
//refer:http://hc.apache.org/httpcomponents-client-4.3.x/quickstart.html 
//http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/fundamentals.html#d5e199
public class HttpClientTest_bak {
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://google.com.hk");
//		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		HttpResponse response1 = httpclient.execute(httpGet);
		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST either fully consume the response content  or abort request
		// execution by calling CloseableHttpResponse#close().

		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity1 = response1.getEntity();
		    {
		    	StatusLine statusLine = response1.getStatusLine();
		    	if(statusLine.getStatusCode() >= 300){
		    		throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		    	}
		    	if(entity1==null){
		    		throw new ClientProtocolException("Response contains no content");
		    	}
			    System.out.println("content 1 : "+entity1.getContent());
			    ContentType contentType = ContentType.getOrDefault(entity1);
			    Charset charset = contentType.getCharset();
			    Reader reader = new InputStreamReader(entity1.getContent(), charset);
			    BufferedReader br = new BufferedReader(reader);
			    String str;
			    while( (str = br.readLine()) != null){
			    	System.out.println(str);
			    }
		    }
		    
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity1);
		} finally {
//		    response1.close();
		}

		HttpPost httpPost = new HttpPost("http://targethost/login");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		HttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
//		    response2.close();
		}
		
	}

}
