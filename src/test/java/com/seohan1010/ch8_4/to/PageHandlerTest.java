package com.seohan1010.ch8_4.to;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PageHandlerTest {


    @Test
    public void test(){

        PageHandler ph = new PageHandler(250,1);
        ph.print();
        System.out.println("<<<<<<< ph = " + ph);
        assertTrue(ph.beginPage==1);



    }




    @Test
    public void test2(){

        PageHandler ph = new PageHandler(255,25);

        ph.print();
        System.out.println("ph = "+ph);
        assertTrue(ph.beginPage == 21);
        assertTrue(ph.endPage==26);

    }

}