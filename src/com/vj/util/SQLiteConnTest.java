package com.vj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnTest {

  private static StringBuffer buffer = new StringBuffer(); // to store output
  // string
  private String dbPath = "F:/gitrepo/solrLearning/db/solrLearning.db";

  void test() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      Class.forName("org.sqlite.JDBC");
      // conn =
      // DriverManager.getConnection("jdbc:sqlite:/F:/gitrepo/SQLiteP/test.db");
      conn = DriverManager.getConnection("jdbc:sqlite:/" + dbPath);
      conn.setAutoCommit(false);
      stmt = conn.createStatement();

      // CRATE TABLE
      stmt.executeUpdate("create table table001(id number, name varchar(32))");
      System.out.println("建表table001成功!");

      // INSERT DATA
      long startOfInsert = System.currentTimeMillis();
      int flag = 0;
      for (int i = 0; i < 10000; i++) {
        System.out.print("插入条目i/n");
        flag = stmt.executeUpdate("INSERT INTO table001 VALUES(" + i + ", '我爱中国" + i + "')");
        System.out.println(flag);
      }
      conn.commit();
      String tmp01 = "插入10000条耗时：" + (System.currentTimeMillis() - startOfInsert);
      buffer.append(tmp01 + "\n");
      // System.out.println(tmp01);

      long startOfQueryWithoutIndex = System.currentTimeMillis();
      System.out.println("不建索引查询:");
      rs = stmt.executeQuery("SELECT id, name FROM table001 where id>5");

      int rId = 0;
      String rName = "";
      while (rs.next()) {
        rId = rs.getInt("id");
        rName = rs.getString("name");
        // System.out.printf("rs.getInt(id)=%s, rs.getString(name)=%s, %n",
        // rId, rName);
        System.out.printf("[ %s : %s  ] ; ", rId, rName);
        if (rId % 100 == 0)
          System.out.println();
      }
      String tmp02 = "无索引查询耗时：" + (System.currentTimeMillis() - startOfQueryWithoutIndex);
      buffer.append(tmp02 + "\n");

      if (rs != null) {
        rs.close();
        rs = null;
      }

      System.out.println("建索引:");
      stmt.executeUpdate("CREATE INDEX table001_idx on table001(id)");
      stmt.executeUpdate("CREATE INDEX table001_idx2 on table001(name)");
      conn.commit();

      System.out.println("建索引后的查询:");
      long startOfQueryWithIndex = System.currentTimeMillis();
      rs = stmt.executeQuery("SELECT id, name FROM table001 where id > 5 ");

      int rId2 = 0;
      String rName2 = "";
      while (rs.next()) {
        rId2 = rs.getInt("id");
        rName2 = rs.getString("name");
        System.out.printf("[ %s : %s  ] ; ", rId2, rName2);
        if (rId2 % 100 == 0)
          System.out.println();
      }
      String tmp03 = "索引查询耗时：" + (System.currentTimeMillis() - startOfQueryWithIndex);
      buffer.append(tmp03 + "\n");

      stmt.executeUpdate("drop table table001");
      System.out.println("删除表table001成功!");
      conn.commit();
    } catch (ClassNotFoundException cnfe) {
      System.out.println("Can't find class for driver: " + cnfe.getMessage());
      System.exit(-1);
    } catch (SQLException sqle) {
      System.out.println("SQLException :" + sqle.getMessage());
      System.exit(-1);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        stmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("SQLException in finally :" + e.getMessage());
        System.exit(-1);
      }
    }
  }

  public static void main(String[] args) {
    SQLiteConnTest conn = new SQLiteConnTest();
    conn.test();
    System.out.print("Success!!");
    System.out.println(buffer);
  }
}
