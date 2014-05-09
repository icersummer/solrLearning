package com.vj.entity;

public class Author extends JO {

  public String name;
  public String gengre;

  // TODO

  /**
   * id_ can be null or 0L, it will not saved into database; we use the auto-generated id.
   * 
   * @param id_
   * @param name_
   * @param gengre_
   */
  public Author(long id_, String name_, String gengre_) {
    id = id_;
    name = name_;
    gengre = gengre_;
  }
  
  

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    String a = String.format("[id = %d, name = %s, gengre = %s]", id, name, gengre);
    return a;
  }



  @Deprecated
  public static void main(String[] args) {
    System.out.println(Author.class.getName());

    String className = Author.class.getName();
    String tableName = className.substring(className.lastIndexOf(".") + 1);
    System.out.println(tableName);
  }
}
