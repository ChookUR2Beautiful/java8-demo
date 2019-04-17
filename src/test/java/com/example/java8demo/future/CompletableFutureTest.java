package com.example.java8demo.future;

import com.example.java8demo.appleFilter.Apple;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author jianjian
 */
public class CompletableFutureTest {

    List<Apple> apples = Arrays.asList(new Apple("green"),new Apple("red"),new Apple("blue"));

    @Test
    public void test() {
        Future<Apple> greenAppleAsync = getGreenAppleAsync();
        Future<Apple> greenAppleSupplyAsnc = getGreenAppleSupplyAsnc();
        Apple redApple = doSomethingElse();
        try {
            Apple apple = greenAppleAsync.get(1, TimeUnit.SECONDS);
            Apple apple1 = greenAppleSupplyAsnc.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("异步线程抛出了异常");
        } catch (ExecutionException e) {
            System.out.println("捕获了异步中主动抛出的异常");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("超时异常");
        }
    }

    @Test
    public void test1(){
        long start = System.nanoTime();
        List<String> apples = findApples();
        System.out.println((System.nanoTime()-start)/1_000_000);
    }


    public List<String> findApples(){
        //并行获取颜色
        List<CompletableFuture<String>> completableFutures = apples.stream().map(apple -> CompletableFuture.supplyAsync(() -> getColor(apple))).collect(Collectors.toList());
        //join 等待所有操作结束
        List<String> collect = completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return collect;
    }


    private String getColor(Apple apple){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String color = apple.getColor()+" "+ LocalTime.now().toString();
        System.out.println(color);
        return color;
    }

    private Apple doSomethingElse() {
        Apple apple = new Apple();
        apple.setColor("red");
        System.out.println(apple);
        return apple;
    }


    public Future<Apple> getGreenAppleAsync() {
        CompletableFuture<Apple> completableFuture = new CompletableFuture<>();
        //在另一个线程中以异步方式执行计算
        new Thread(() -> {
            try {
                Apple apple = new Apple();
                apple.setColor("green");

                Thread.sleep(1000);

                System.out.println(apple);
                completableFuture.complete(apple);
            } catch (InterruptedException e) {
                //主动在线程里抛出异常
                completableFuture.completeExceptionally(e);
            }
        });
        //无需等待直接返回
        return completableFuture;
    }


    /**
     * 使用工程方法创建future
     * @return
     */
    public Future<Apple> getGreenAppleSupplyAsnc() {
        CompletableFuture<Apple> completableFuture = CompletableFuture.supplyAsync(() -> {
            Apple apple = new Apple();
            apple.setColor("green");
            try {
                Thread.sleep(1000);
                System.out.println(apple);
            } catch (InterruptedException e) {
            }
            return apple;
        });
        return completableFuture;
    }


}

    
    