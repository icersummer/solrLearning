package com.vj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.vj.dao.AuthorDAO;
import com.vj.dao.BookDAO;
import com.vj.entity.Author;
import com.vj.entity.Book;
import com.vj.entity.JO;
import com.vj.search.IndexSearcher;

public class CommandInterface {

  private BlockingQueue<JO> itemsToIndex = new ArrayBlockingQueue<JO>(20);
//  java.util.concurrent.BlockingQueue<E>;
  
  public static void main(String[] args) {
    CommandInterface ui = new CommandInterface();
    ui.startIndexThread();
    ui.start();
  }

  /**
   * start a Thread : working as a non-stop thread to listen to index any data (new, update, delete); <br>
   * its main task: <br>
   * 1. Get Item from BlockingQueue (the Blocking Queue items come from Database Insertion/Updation/Deletion) ; <br>
   * 2. Index the item. <br>
   */
  private void startIndexThread() {
    // TODO Auto-generated method stub
    IndexThread it = new IndexThread(itemsToIndex);
    new Thread(it).start();
  }

  private void start() {
    try {
      out("Welcome to CommandInterface:");
      while (true) {
        printMenu();

        InputStreamReader istream = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(istream);
        out("Type in your option:");
        String option = br.readLine().trim();
        switch (option) {
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
            // TODO
          case "4":
            out("createNewAuthor...");
            createNewAuthor();
            break;
          case "5":
            out("createNewRandomAuthor...");
            createNewRandomAuthor();
            break;
          //TODO create book, city ; random or not
          case "6":
            out("create 6 Books...");
            create6Books();
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

  private void create6Books() {
    // TODO Auto-generated method stub
    for (int i = 1; i <= 6; i++) {
      try {
        String path = "src/com/vj/index/data/book/" + String.format("%d.properties", i);
        Properties p = new Properties();
        p.load(new FileReader(path));
        String title;
        String writer;
        String description;
        String isbn;
        String url;
        title = p.getProperty("title");
        writer = p.getProperty("writer");
        description = p.getProperty("description").replaceAll("'", " ");
        isbn = p.getProperty("isbn");
        url = p.getProperty("url");
        Book book = new Book(title, writer, description, isbn, url);
        BookDAO.getDAO().save(book);
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  private void printMenu() {
    // TODO Auto-generated method stub
    out("1. Normal search");
    out("2. Search all");
    out("3. Delete all index");
    out("4. Create New Author");
    out("5. Create New Author (Random)");
    out("6. Create 6 Books (only run 1 time !)");
    out("8. TODO");
    out("9. Quit");
  }

  private void printout(SolrDocumentList results) {
    // TODO Auto-generated method stub
    out("***********************************************************************");
    for (SolrDocument doc : results) {
      Collection<String> fieldNames = doc.getFieldNames();
      for (String fieldName : fieldNames) {
        String fieldValue = (String) doc.getFieldValue(fieldName);
        out(String.format("fieldName [%s], fieldValue [%s];", fieldName, fieldValue));
      }
      out(" ------------------------- ");
    }
    out("***********************************************************************");
  }

  private static void out(Object o) {
    System.out.println(o);
  }

  /**
   * create an Author with input from UI (age, genre)
   */
  private void createNewAuthor() {
    // TODO Auto-generated method stub
    out("to be done...");
  }

  /**
   * create an Author with : random generated AGE, random generated GENRE
   */
  private void createNewRandomAuthor() {
    // TODO Auto-generated method stub
    AuthorDAO adao = AuthorDAO.getDAO();
    String name = "name_";
    String genre = "genre_";
    long x = System.currentTimeMillis();
    Author author = new Author(0L, name+x, genre+x);
    adao.save(author);
    
    // add into Queue
    try {
      this.itemsToIndex.put(author);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
