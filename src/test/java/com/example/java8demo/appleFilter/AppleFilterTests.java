package com.example.java8demo.appleFilter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从固定方法到方法参数化过程
 * @author jianjian
 */
public class AppleFilterTests {

        @Test
        public void test(){
            Apple apple = new Apple();
            apple.setColor("green");
            apple.setWeight(151);

            List<Apple> paramList = Arrays.asList(apple);

            List<Apple> apples = filterGreenApples(paramList);
            Assert.assertTrue(apples.size()==1);

            List<Apple> green = filterApplesByColor(paramList, "green");
            Assert.assertTrue(green.size()==1);


            List<Apple> height = filterApples(paramList, new AppleHeavyHeightPredicate());
            Assert.assertTrue(height.size()==1);

            List<Apple> color = filterApples(paramList, new AppleGreenColorPredicate());
            Assert.assertTrue(color.size()==1);

            //将行为参数化
            List<Apple> height1 = filterApples(paramList,a -> a.getWeight()>150 );
            Assert.assertTrue(height1.size()==1);

            List<Apple> color1 = filterApples(paramList,a -> "green".equals(a.getColor()));
            Assert.assertTrue(color1.size()==1);

        }

    /**
     * 1.方法内只筛选green的苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
            List<Apple> result = new ArrayList<>();
            for (Apple apple:inventory
                 ) {
                if("green".equals(apple.getColor())){
                    //仅仅选出绿苹果
                    result.add(apple);
                }

            }
            return result;
        }


    /**
     * 2.把颜色作为参数
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory,String color){
            List<Apple> result = new ArrayList<>();
            for (Apple apple:inventory
                 ) {
                if(apple.getColor().equals(color)){
                    result.add(apple);
                }
            }
            return result;
        }


    /**
     * 3.策略模式
     */
    public static List<Apple> filterApples(List<Apple> inventory,ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple:inventory
        ) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }



    public class AppleHeavyHeightPredicate implements ApplePredicate{

            @Override
            public boolean test(Apple apple) {
                return apple.getWeight()>150;
            }
        }


        public class AppleGreenColorPredicate implements ApplePredicate{
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        }






}

    
    