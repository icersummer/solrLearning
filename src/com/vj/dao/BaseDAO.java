package com.vj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vj.entity.Author;

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

}
