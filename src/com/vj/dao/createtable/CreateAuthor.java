package com.vj.dao.createtable;

import java.sql.SQLException;

import com.vj.dao.BaseDAO;

public class CreateAuthor extends BaseDAO {
  
  private static String tableName = "author";

  public static void main(String[] args) {
    CreateAuthor a = new CreateAuthor();
//    a.dropTable(tableName);
    a.createTable(tableName);
  }

  private void dropTable(String tableName) {
    try {
      stmt = conn.createStatement();
      String sql = String.format("drop table %s", tableName);
      stmt.execute(sql);
      conn.commit();
      System.out.println(tableName + " deleted.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  private void createTable(String tableName) {
    // TODO Auto-generated method stub
    try {
      stmt = conn.createStatement();
      String sql = String.format("create table %s (id integer primary key autoincrement, name varchar(100), gengre varchar(100))", tableName);
      stmt.execute(sql);
      conn.commit();
      System.out.println(tableName + " created.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void save(Object e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void update(Object e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Object e) {
    // TODO Auto-generated method stub
    
  }

}
