package com.vj.futureTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class FutureTest {

	@Test
	public void test001() {
		Future future = null;
		ThreadPoolExecutor tpe = null;
		future = tpe.submit(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		});
	}
	
	void m002(){
	    ExecutorService executor = Executors.newSingleThreadExecutor();   
	    FutureTask<String> future =   
	           new FutureTask<String>(new Callable<String>() {//使用Callable接口作为构造参数   
	             public String call() {
	               //真正的任务在这里执行，这里的返回值类型为String，可以为任意类型   
	            	 return null;   
	           }});   
	    executor.execute(future);   
	    //在这里可以做别的任何事情   
	    try {   
	        String result = future.get(5000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果   
	    } catch (InterruptedException e) {   
	    	future.cancel(true);   
	    } catch (ExecutionException e) {   
	    	future.cancel(true);   
	    } catch (TimeoutException e) {   
	    	future.cancel(true);   
	    } finally {   
	        executor.shutdown();   
	    }  

	}

}


class MyFuture implements Future<String> {

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}
	
}