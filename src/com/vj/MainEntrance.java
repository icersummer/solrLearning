package com.vj;

import com.vj.index.Indexer;

/**
 * start the Graphical UI by calling MainFrame <br>
 * 
 */
public class MainEntrance {

//	private static final long serialVersionUID = 7541413646719558546L;

	public static void main(String[] args) {
		mainWithoutUI();
	}

	/**
	 * execute this MAIN to load default index files (containing Fake Name).
	 * will have UI to index single object. load 5 files by default (in
	 * com.vj.index.data)
	 */
	private static void mainWithoutUI() {

		Util.deleteAllExistingIndex();

		Indexer indexer = new Indexer();
		indexer.indexFile("a.txt.transform.properties");
		indexer.indexFile("b.txt.transform.properties");
		indexer.indexFile("c.txt.transform.properties");
		indexer.indexFile("d.txt.transform.properties");
		indexer.indexFile("e.txt.transform.properties");
	}

}
