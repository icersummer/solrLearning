package com.vj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO<E> {

  private String dbPath = "F:/gitrepo/solrLearning/db/solrLearning.db";
  public Connection conn = null;
  public Statement stmt = null;
  public ResultSet rs = null;

  public BaseDAO() {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:/" + dbPath);
      conn.setAutoCommit(false);
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * the ResultSet need to be closed.
   * @param e
   */
  public ResultSet queryAll(E e){
    try {
      // com.vj.entity.Author
      String className = e.getClass().getName();
      String tableName = className.substring(className.lastIndexOf(".") + 1);
      String sql = "select * from " + tableName;
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      return rs;
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    return null;
    
  }
  public abstract void save(E e);

  public abstract void update(E e);

  public abstract void delete(E e);

  /**
   * Closes a Statement (unless it is null). NOTE, this method logs and ignores any SQLException
   * thrown by the operation.
   * 
   * @param stmt the Statement to be closed.
   */
  public static void safeClose(Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        System.out.println("SQLException from Statement.close(): " + e);
        e.printStackTrace();
      }
    }
  }

  /**
   * Closes a ResultSet (unless it is null). NOTE, this method logs and ignores any SQLException
   * thrown by the operation.
   * 
   * @param rs the ResultSet to be closed.
   */
  public static void safeClose(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        System.out.println("SQLException from ResultSet.close(): " + e);
        e.printStackTrace();
      }
    }
  }

}
