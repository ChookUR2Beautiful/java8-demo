package com.example.java8demo.java8Api;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer  方法接收一个对象,返回空
 * @author jianjian
 */
public class ConsumerTests {

    @Test
    public void test(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        ArrayList<Integer> newList = new ArrayList<>();
        forEach(list,c->{newList.add(c);});
        Assert.assertTrue(newList.size()==5);
    }


    public static <T> void forEach(List<T> list, Consumer<T> c){
        for (T t:list){
            c.accept(t);
        }
    }










}

    
    