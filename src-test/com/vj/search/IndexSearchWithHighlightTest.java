package com.vj.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.vj.Util;

public class IndexSearchWithHighlightTest {

  @Test
  public void testHighlight() throws ClientProtocolException, IOException {
    HttpClient httpclient = new DefaultHttpClient();
    String url = Util.SOLR_SERVER_URL + "/select/?q=book_title:Edition&hl=true&hl.fl=book_title";
    // url += "&wt=json"; // get JSON format result
    HttpGet httpGet = new HttpGet(url);
    HttpResponse rsp = httpclient.execute(httpGet);
    StatusLine statusLine = rsp.getStatusLine();
    System.out.println(statusLine);
    HttpEntity entity = rsp.getEntity();
    if (statusLine.getStatusCode() >= 300) {
      throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
    }
    if (entity == null) {
      throw new ClientProtocolException("Response contains no content");
    }

    ContentType contentType = ContentType.getOrDefault(entity);
    Charset charset = contentType.getCharset();
    BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
    String str;
    while ((str = br.readLine()) != null) {
      System.out.println(str);
    }

    Assert.assertFalse("fail", true);

  }

}
