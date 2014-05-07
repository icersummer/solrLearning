package com.vj.dao;

import java.sql.SQLException;

import com.vj.entity.Author;

public class AuthorDAO extends BaseDAO<Author> {
  
  private AuthorDAO authorDAO = new AuthorDAO();
  
  private AuthorDAO(){
    super();
  }
  
  public AuthorDAO getDAO() {
    return authorDAO;
  }

  @Override
  public void save(Author e) {
    // TODO Auto-generated method stub
    try {
      String sql = String.format("insert into e values (%s, %s)", e.name, e.gengre);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  @Override
  public void update(Author e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void delete(Author e) {
    // TODO Auto-generated method stub
    
  }

}
