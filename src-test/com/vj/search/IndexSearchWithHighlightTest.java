package com.vj.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.vj.Util;
import com.vj.entity.Book;
import com.vj.entity.JO;

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
  
  @Test
  public void whatIsTeaser() throws SolrServerException{
//    String url = Util.SOLR_SERVER_URL + "/select/?q=book_title:Edition&hl=true&hl.fl=book_title";
    SolrQuery q = new SolrQuery();
    q.setQuery("book_title:Edition");
    q.setHighlight(true);
    q.set("hl.fl", "book_title");
    SolrServer server = Util.SERVER;
    QueryResponse rsp = server.query(q);
    SolrDocumentList docs = rsp.getResults();
    System.out.println(docs.getNumFound());
//    Vector<JO> results = new Vector<JO>();
    Vector results = new Vector();
    for(SolrDocument doc : docs){
      String id = (String)doc.getFieldValue("id");
//      JO jo = null;//new JO();
      //TODO use reflection to determine Book, Author, etc
      Book jo = new Book();
      // m , is the final result wrapper in Collection
      Map<String, Object> m = new HashMap<String, Object>();
      String teaser = getTeaser(rsp, docs, id);
      if(teaser != null) {
        //TODO
        m.put("body", modifyTeaser(teaser));
      }
      
      // loop over all fields
      Collection<String> allFields = doc.getFieldNames();
      Iterator<String> itAllFields = allFields.iterator();
      while(itAllFields.hasNext()){
        String fieldName = itAllFields.next();
        m.put(fieldName, doc.getFieldValue(fieldName));
      }
      
      // add className to each returned row, used in the GroupBy header
      if(doc.getFieldValue("book_classname") != null) {
        m.put("object_classname", doc.getFieldValue("book_classname"));
      }
      results.add(m);
    }
    
//    return results;
    
    Assert.assertFalse(true);
  }

  private String getTeaser(QueryResponse rsp, SolrDocumentList docs, String id) {
    // TODO Auto-generated method stub
    String teaser = null;
    Map<String, List<String>> teasersMap = null;
    Map<String, Map<String, List<String>>> hl = rsp.getHighlighting();
    if(hl != null) {
      teasersMap = hl.get(id);
    }
    StringBuffer buffer = new StringBuffer();
    if(teasersMap != null && !teasersMap.isEmpty()){
      List<List<String>> teasers = new ArrayList<List<String>>(teasersMap.values());
      List<String> teaserList = teasers.get(0);
      if(teaserList != null && !teaserList.isEmpty()){
        for(String highlight : teaserList) {
          if(buffer.length() > 0)
            buffer.append("......");
          // teaser = teaserList.get(0);
          buffer.append(highlight);
        }
      }
      teaser = buffer.toString();
    }
    return teaser;
  }

  private Object modifyTeaser(String teaser) {
    // replace all whitespace characters except space
    if(teaser != null){
      return teaser.replaceAll("[\\t\\n\\x0B\\f\\r]", " ");
    }else{
        return null;
    }
  }

}
