package com.vj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * get a next Sequence from Database <br>
 *
 * deprecated, to use [id integer primary key autoincrement] instead
 */
@Deprecated
public class IdGenerator {


  private static String dbPath = "F:/gitrepo/solrLearning/db/solrLearning.db";
  private static Connection conn = null;
  private static Statement stmt = null;
  private static ResultSet rs = null;
  private static String idSequenceSql = "select idSequence.nextVal from dual";

  static {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:/" + dbPath);
      conn.setAutoCommit(false);
      stmt = conn.createStatement();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static long getNextId() {
    try {
      rs = stmt.executeQuery(idSequenceSql);
      long nextId = rs.getLong(1);
      return nextId;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return 0L;
  }
}
