package com.vj.index;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import com.vj.Util;
import com.vj.entity.Book;

public class IndexWithDynamicField {

  private static final Logger LOGGER = Logger.getLogger(IndexWithDynamicField.class.toString());

  @Test
  // dynamic field: <danymicField name="*_vj" type="book_text" indexed="true" stored="true" />
  public void indexWithDynamicField_vj() throws SolrServerException, IOException {

    SolrServer ss = Util.SERVER;
    SolrInputDocument doc = new SolrInputDocument();
    int x = new Random().nextInt(100000);
    doc.addField("id", new Random().nextInt(999999));
    doc.addField("title_vj", "title peace" + x);
    doc.addField("writer_vj", "writer " + x);
    doc.addField("description_vj", "description " + x);
    // doc.addField("book_isbn", isbn);
    // doc.addField("book_url", url);
    String className = Book.class.getName();
    doc.addField("classname_vj", className);

    UpdateResponse rsp = ss.add(doc);
    int status = rsp.getStatus();
    LOGGER.info("status = " + status);
    ss.commit();

    Assert.assertTrue(false);
  }
}
