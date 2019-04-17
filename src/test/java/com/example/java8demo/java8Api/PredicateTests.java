package com.example.java8demo.java8Api;

import com.example.java8demo.appleFilter.Apple;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate 函数式接口
 * @author jianjian
 */
public class PredicateTests {


    @Test
    public void test(){
        Apple apple = new Apple();
        apple.setColor("green");
        apple.setWeight(151);
        List<Apple> paramList = Arrays.asList(apple);
        List<Apple> apples = filterApples(paramList, p -> "green".equals(p.getColor()));
        Assert.assertTrue(apples.size()==1);
    }



    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple:inventory
        ) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


}

    
    