package pl.com.softproject.sample.przelewy24.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.com.softproject.sample.przelewy24.util.DigestUtils;

import java.util.Map;
import java.util.Random;

/**
 * Copyright 2015-06-23 the original author or authors.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class P24ServiceImplTest {

    @Autowired
    private P24Service p24Service;

    @Test
    public void testTestConnection() throws Exception {
        System.out.println("!!!");

        p24Service.testConnection();


    }

    @Test
    public void testMD5() {

        String res = DigestUtils.md5Digest("abcdefghijk|9999|2500|PLN|a123b456c789d012");
        System.out.println(res);

    }

    @Test
    public void testTrnRegister() {

        Random generator = new Random();

        p24Service.trnRegister(String.valueOf(generator.nextInt()), 11.50, "testowa płatność 2", "adrian@soft-project.pl");

    }

    @Test
    public void testTrnVerify() {

        p24Service.trnVerify("1723594116", 11.50, 18507346);

    }

    @Test
    public void testQuery() throws Exception {

        String query = "error=0&token=AB978C2433-C52122-1BAE6B-E76C60EFA2";

        Map<String, String> res = ((P24ServiceImpl) p24Service).splitQuery(query);

        System.out.println(res);

    }
}