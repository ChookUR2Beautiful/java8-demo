package com.example.java8demo.bufferedReaderRound;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jianjian
 */
public class BufferedReaderTests {

    @Test
    public void test() throws Exception {
        String s = processFile();
        System.out.println(s);

        processFile(bufferedReader -> bufferedReader.readLine());
        System.out.println(s);
    }

    /**
     * 方法里包括了行为
     * @return
     * @throws IOException
     */
    public static String processFile() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jianjian\\default-soapui-workspace.xml"))){
            return br.readLine();
        }
    }


    /**
     * 将br的行为参数化
     * @param p
     * @return
     * @throws Exception
     */
    public static String processFile(BufferedReaderProcessor p) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jianjian\\default-soapui-workspace.xml"))){
            return p.proess(br);
        }
    }



}

    
    