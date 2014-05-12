package com.vj.dao.createtable;

import java.sql.SQLException;

import com.vj.dao.BaseDAO;

public class CreateBook extends BaseDAO {

  private static String tableName = "Book";


  public static void main(String[] args) {
    CreateBook a = new CreateBook();
    // a.dropTable(tableName);
    a.createTable(tableName);
  }

  /*
   * create table book( id integer primary key autoincrement, title text, writer text, description
   * text, isbn text, url text, createDate date )
   */
  private void createTable(String tableName) {
    // TODO Auto-generated method stub
    try {
      stmt = conn.createStatement();
      String sql =
          String
              .format(
                  "create table %s (id integer primary key autoincrement, title text, writer text, isbn text, url text, description text, createDate timestamp )",
                  tableName);
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
