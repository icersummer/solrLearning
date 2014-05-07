package com.vj.entity;

public class Author {
  public long id;
  public String name;
  public String gengre;
  // TODO
  
  @Deprecated
  public static void main(String[] args) {
    System.out.println(Author.class.getName());
    
    String className = Author.class.getName();
    String tableName = className.substring(className.lastIndexOf(".") + 1);
    System.out.println(tableName);
  }
}
