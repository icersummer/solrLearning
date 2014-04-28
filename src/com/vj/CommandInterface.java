package com.vj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.vj.search.IndexSearcher;

public class CommandInterface {
	
	public static void main(String[] args) {
		CommandInterface ui = new CommandInterface();
		ui.start();
	}
	
	private void start() {
		try {
			out("Welcome to CommandInterface:");
			while(true){				
				printMenu();
				
				InputStreamReader istream = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(istream);
				out("Type in your option:");
				String option = br.readLine().trim();
				switch(option) {
				case "1":
					out("Type in your keyword:");
					String keyword = br.readLine().trim();
					// search
					out("Searching with " + keyword);
					IndexSearcher searcher = new IndexSearcher();
					QueryResponse rsp = searcher.queryByKeyword(keyword);
					out(String.format("Get %d results, detail below:", rsp.getResults().getNumFound()));
					printout(rsp.getResults());
					break;
				case "2":
					out("Searching ALL...");
					QueryResponse rsp2 = new IndexSearcher().queryByKeyword("*");
					out(String.format("Get %d results, detail below:", rsp2.getResults().getNumFound()));
					printout(rsp2.getResults());
					break;
				case "3":
					out("To be done...");
					//TODO
				case "9":
					System.exit(0);
					break;
				default:
					out("TODO");
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printMenu() {
		// TODO Auto-generated method stub
		out("1. Normal search");
		out("2. Search all");
		out("3. Delete all index");
		out("4. TODO");
		out("9. Quit");
	}

	private void printout(SolrDocumentList results) {
		// TODO Auto-generated method stub
		out("***********************************************************************");
		for(SolrDocument doc : results) {
			Collection<String> fieldNames = doc.getFieldNames();
			for(String fieldName : fieldNames) {
				String fieldValue = (String) doc.getFieldValue(fieldName);
				out(String.format("fieldName [%s], fieldValue [%s];", fieldName, fieldValue));
			}
			out(" ------------------------- ");
		}
		out("***********************************************************************");
	}

	private static void out(Object o){
		System.out.println(o);
	}

}
