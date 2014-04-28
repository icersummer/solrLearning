package com.vj.search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.vj.Util;

public class IndexSearcher {

	private SolrServer server = Util.SERVER;

	/**
	 * @param obj SolrQuery
	 * @return obj SolrDocumentList
	 */
	//TODO
	public Object search(Object obj) {
		
		return null;
	}
	
	/**
	 * different cases: "", *, *abc, abc
	 * @param keyword
	 * @return
	 * @throws SolrServerException
	 */
	public QueryResponse queryByKeyword (String keyword)  {
		QueryResponse rsp = null;
		try {
			if(keyword.equals("")){
				//TODO  skip and warning ?
				return null;
			} else if(keyword.equals("*")) {
				// query all
				rsp = queryAll();
			} else if(keyword.length() > 1 && keyword.startsWith("*")) {
				//TODO query by leading *, which is not supported by Solr by default
				return null;
			} else {
				//TODO normal search
					rsp = queryByNormal(keyword);
	
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rsp;
	}
	
	//	 <!-- field for the QueryParser to use when an explicit fieldname is absent -->
	//	 <defaultSearchField>keywords_en</defaultSearchField>
	private QueryResponse queryByNormal(String keyword) throws SolrServerException {
		SolrQuery query = new SolrQuery("fn_name:"+keyword);
		QueryResponse rsp = server.query(query);
		return rsp;
	}

	private QueryResponse queryAll() throws SolrServerException {
		SolrQuery q = new SolrQuery("*:*");
		QueryResponse rsp = server.query(q);		
		return rsp;
	}
	
	/**
	 * query ALL with each field, to verify the total count is what we expect.
	 * @throws SolrServerException
	 */
	public void verifyWhatTotalInIndex() throws SolrServerException {
		QueryResponse rsp;
		for(String field : Util.fieldsSet) {
			rsp = queryByParam(field);
			//TODO loop rsp to print out all values
			
		}
	}
	
	private QueryResponse queryByParam(String fieldName) throws SolrServerException {
		SolrQuery q = new SolrQuery(fieldName+":*");
		QueryResponse rsp = server.query(q);
		return rsp;
	}
}
