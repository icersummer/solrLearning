package com.vj.entity;

import java.util.Date;

public class Book extends JO {

  public String title;
  public String writer;
  public String description;
  public String isbn;
  public String url;
  public Date createDate;

  // TODO
  public Book(String title, String writer, String description, String isbn, String url) {
    super();
    this.title = title;
    this.writer = writer;
    this.description = description;
    this.isbn = isbn;
    this.url = url;
//    this.createDate = createDate;
  }


  public Book() {
    // TODO Auto-generated constructor stub
    super();
  }


  @Override
  public String toString() {
    // TODO Auto-generated method stub
    String a = String.format("[id = %d, title = %s, writer = %s, isbn=%s, url=%s, createDate=%s, description = %s,]",
        id, title, writer,isbn,url,createDate,description);
    return a;
  }


}



/*-- table Book
 create table book(
 id integer primary key autoincrement,
 title text,
 writer text,
 description text,
 isbn text,
 url text,
 createDate date
 )*/
