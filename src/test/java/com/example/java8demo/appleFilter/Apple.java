package com.example.java8demo.appleFilter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author jianjian
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Apple {

    @NonNull
    private String color;

    private Integer weight;



}

    
    