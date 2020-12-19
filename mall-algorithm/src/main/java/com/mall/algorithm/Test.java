package com.mall.algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @Author Zhangnana
 * @DATE 2020/12/14 14:10
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf("c".equalsIgnoreCase("C") ? -1 : 1);
        BigDecimal multiply = bigDecimal1.multiply(new BigDecimal(3900));
        double v = multiply.doubleValue();
        BigDecimal A = new BigDecimal(3);
        bigDecimal.add(A);
        System.out.println(v);
        Test test = new Test();
        test.sdf();


    }

    public void sdf(){
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("dat.dat");
        try {
            int available = inputStream.available();
            byte[] inputbuffer = new byte[available];
            inputStream.read(inputbuffer);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
