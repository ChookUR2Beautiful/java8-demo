package com.example.java8demo.java8Api;

import com.example.java8demo.appleFilter.Apple;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Function 接收一个对象,返回另一个对象
 * @author jianjian
 */
public class FunctionTests {

    @Test
    public void test(){
        Apple apple = new Apple();
        apple.setColor("green");
        apple.setWeight(151);
        List<Apple> paramList = Arrays.asList(apple);
        List<String> map = map(paramList, Apple::getColor);
        Assert.assertEquals("green",map.get(0));
    }


    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
            List<R> result =new ArrayList<>();
        for (T s:list
             ) {
            result.add(f.apply(s));
        }
        return result;
    }


}

    
    