package com.example.java8demo.future;

import com.example.java8demo.appleFilter.Apple;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author jianjian
 */
public class FutureTests {

    @Test
    public void test(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Apple> future = executorService.submit(new Callable<Apple>() {
            @Override
            public Apple call() throws Exception {
                return doSomeLongComputation();
            }
        });
        Apple redApple = doSomethingElse();
        try {
            //判断任务是否执行完毕
            boolean done = future.isDone();
            //获取异步结果,2秒超时
            Apple apple = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("异步线程抛出了异常");
        } catch (ExecutionException e) {
            System.out.println("线程在等待过程中被中断");
        } catch (TimeoutException e) {
            System.out.println("在Future对象完成前超时");
        }

    }

    private Apple doSomethingElse() {
        Apple apple = new Apple();
        apple.setColor("red");
        System.out.println(apple);
        return apple;
    }

    /**
     * 线程阻塞1秒 返回
     * @return
     */
    private Apple doSomeLongComputation() {
        Apple apple = new Apple();
        apple.setColor("green");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(apple);
        return apple;
    }


}

    
    