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

  public abstract void save(E e);

  public abstract void update(E e);

  public abstract void delete(E e);

}
