package com.vj.search;

import junit.framework.Assert;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IndexSearcherTest {
	
	IndexSearcher searcher = null;
	QueryResponse rsp = null;
	
	@BeforeClass
	public static void  beforeClass(){
		
	}
	
	@Before
	public void before(){
		searcher = new IndexSearcher();
	}
	
	@After
	public void after(){
		
	}
	
	@AfterClass
	public static void afterClass(){
		
	}
	
	@Test
	public void testQueryByKeyword_1() throws SolrServerException{// ""
		rsp = searcher.queryByKeyword("");
		SolrDocumentList sdl = rsp.getResults();
		long found = sdl.getNumFound();
		Assert.assertEquals(0, found);
	}
	
	@Test
	public void testQueryByKeyword_2(){// *
		rsp = searcher.queryByKeyword("*");
		SolrDocumentList sdl = rsp.getResults();
		long found = sdl.getNumFound();
		Assert.assertNotSame(0, found);
	}
	
	@Test
	public void testQueryByKeyword_3(){// *world
		rsp = searcher.queryByKeyword("*world");
		SolrDocumentList sdl = rsp.getResults();
		long found = sdl.getNumFound();
		Assert.assertTrue(found > 0);
	}
	
	@Test
	public void testQueryByKeyword_4(){// abc or abc* or a*bc
		rsp = searcher.queryByKeyword("chang");
		SolrDocumentList sdl = rsp.getResults();
		long found = sdl.getNumFound();
		Assert.assertTrue(found > 0);
	}
	
	@Test
	public void testQueryByKeyword_5(){// abc or abc* or a*bc
		rsp = searcher.queryByKeyword("world*");
		SolrDocumentList sdl = rsp.getResults();
		long found = sdl.getNumFound();
		Assert.assertTrue(found > 0);
	}
}
