package com.vj.dao;

import java.sql.SQLException;
import java.util.Date;

import com.vj.entity.Book;

public class BookDAO extends BaseDAO<Book> {

  private static BookDAO dao = new BookDAO();

  private BookDAO() {
    super();
  }

  public static BookDAO getDAO() {
    return dao;
  }

  @Override
  public void save(Book e) {
    // TODO Auto-generated method stub

    try {
      String sql =
          String
              .format(
                  "insert into book(writer, title, isbn, url, description, createDate) values ('%s', '%s','%s','%s','%s', datetime('now'))",
                  e.writer, e.title, e.isbn, e.url, e.description
//                  ,new Date(System.currentTimeMillis())
                  );// datetime('now')
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      conn.commit();
      System.out.println(String.format(" Book save done: %s .", e));
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  @Override
  public void update(Book e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(Book e) {
    // TODO Auto-generated method stub

  }

  public int getIdFromTitle(String title) {
    // TODO Auto-generated method stub
    int bid = 0;
    try {
      String sql = String.format("select id from Book where title = '%s' order by id desc", title);
      rs = conn.createStatement().executeQuery(sql);
      if(rs.next()) {
        bid = rs.getInt("id");
      }
      if(rs.next()) {
        // !! should be only 1 result return !!
        System.out.println("!! EXCEPTION ATTENTAION, MORE THAN ONE RECORD !!");
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return bid;
  }

}
