package com.example.java8demo.java8Api;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author jianjian
 */
public class IntPredicateTests {

    @Test
    public void test(){

        //无装箱
        IntPredicate evenNumbers = (int i) -> i%2==0;
        Assert.assertTrue(evenNumbers.test(1000));

        //有装箱
        Predicate<Integer> oddNumbers = (Integer i) -> i%2==1;
        Assert.assertFalse(oddNumbers.test(1000));
    }


}

    
    