package com.vj;

import java.util.concurrent.BlockingQueue;

import com.vj.entity.JO;

public class IndexThread implements Runnable {

  BlockingQueue<JO> itemsToIndex;

  public IndexThread(BlockingQueue<JO> itemsToIndex) {
    // TODO Auto-generated constructor stub
    this.itemsToIndex = itemsToIndex;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    System.out.println("IndexThread start running ... ");
    while (true) {
      try {
        JO jo = itemsToIndex.take();
        System.out.println("Item get in BlockingQueue : " + jo);
        System.out.println("Processed done.");
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }// end while(true)
  }

}
