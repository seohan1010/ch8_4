package com.seohan1010.ch8_4.mapper;

import com.seohan1010.ch8_4.service.BoardService;
import com.seohan1010.ch8_4.service.BoardServiceImpl;
import com.seohan1010.ch8_4.to.BoardDto;
import com.seohan1010.ch8_4.to.SearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    BoardService boardService;


    // 테스트 성공
    @Test
    public void test(){
        System.out.println("<<<<< boardMapper = " + boardMapper);
    }


    @Test
    @DisplayName("searchBoardList")
    public void searchBoardList()throws Exception{

        String keyword ="writer1";
        String option="W";
        SearchCondition sc = new SearchCondition();
        sc.setKeyword(keyword);
        sc.setOption(option);
        List<BoardDto> list =   boardMapper.searchBoardList(sc);


        System.out.println(list==null?"<<<<<<<<<<<<<< no data found":list);
        System.out.println("<<<<<<<<<<<<<<<<<< ");
        list.forEach(System.out::println);
        System.out.println(">>>>>>>>>>>>>>>>>> ");
    }



    // 테스트 성공
    @Test
    @DisplayName("select BoardList test")
    public void selectBoardListTest()throws Exception{

        List<BoardDto> list = boardMapper.selectBoardList();
        assertNotNull(list);
        list.forEach(System.out::println);

    }


    // 값이 DB에 안들어간다. ---> 내가 쿼리문을 잘못 작성했다.
    @Test
    public void insertTest()throws Exception{

    String title = "test title";
    String writer ="test writer";
    String content = "test content";



    BoardDto b= new BoardDto();
    b.setTitle(title);
    b.setWriter(writer);
    b.setContent(content);
        System.out.println("<<<<<<<<<<<<<<<<< b.getTitle() = " + b.getTitle());
        boardMapper.insertBoard(b);


    }

    // 테스트 성공
    @Test
    public void selectBoardDetailTest()throws Exception{

            Long bno = 5618L;

            BoardDto b= boardMapper.selectBoardDetail(bno);

        assertNotNull(b);
        System.out.println(b == null?"<<<<<<< there is no data" :b);

    }

    // 테스트성공
    @Test
    public void updateBoardTest()throws Exception{

        Long bno=5732L;
        String title = "modified title";
        String content = "modified content";

        Map<String, Object> map = new HashMap<>();
        map.put("bno",bno);

        BoardDto b = new BoardDto();
        b.setBno(bno);
        b.setTitle(title);
        b.setContent(content);

        boardMapper.updateBoard(b);
        BoardDto b2 = boardMapper.selectBoardDetail(bno);
        System.out.println(b2==null?"<<<<<<<there is no data":b2);
        assertEquals(b2.getTitle(),title);

    }


    // 테스트 성공
    @Test
    public void deleteBoardDetailTest()throws Exception{
        Long bno = 5619L;
        Map<String, Object> map = new HashMap<>();
        map.put("bno",bno);
        boardMapper.deleteBoardDetail(bno);
        BoardDto b = boardMapper.selectBoardDetail(bno);
        System.out.println(b == null?"<<<<<<<<<<< there is no data at data base": b);
        assertNull(b);
    }



    //-----------------------------------------------------------------

                                //BoardServiceImpl Test

    //테스트 성공
    @Test
    public void implTest(){
        System.out.println("<<<<<<<<<<<<<<<< boardService = " + boardService);
    }

    //테스트 성공
    @Test
    public void serviceSelectTest()throws Exception{
       List<BoardDto> b = boardService.findBoardList();
       assertNotNull(b);
       b.forEach(System.out::println);
    }


    @Test
    public void servicetSelectBoardDetailTest()throws Exception{
        Long bno = 5618L;
      BoardDto b = boardService.findBoardDetail(bno);
            assertNotNull(b);
        System.out.println(b==null?"<<<<<<<<<<< there is no data" : b);
    }

    //테스트 성공
    @Test
    public void serviceRegisterTest()throws Exception{

        String title ="test title";
        String writer = "test writer";
        String content = "test writer";

        BoardDto b = new BoardDto();
        b.setTitle(title);
        b.setWriter(writer);
        b.setContent(content);


        boardService.registerBoard(b);
    }


    //테스트 성공



    @Test
    public void serviceUpdateTest()throws Exception{
        String title ="modified title";
        String writer = "modified writer";
        String content = "modified content";
        Long bno=5620L;

        BoardDto b = new BoardDto();
        b.setTitle(title);
        b.setWriter(writer);
        b.setContent(content);
        b.setBno(bno);

        boardService.modifyBoard(b);
       BoardDto b2 =  boardService.findBoardDetail(bno);
       assertEquals(b2.getTitle(),title);
       System.out.println(b2==null?"<<<<<<< there is no data":b2);

    }


    //테스트 성공
    @Test
    public void serviceRemoveBoardTest()throws Exception{

        Long bno=5620L;

        boardService.removeBoardDetail(bno);

      BoardDto b = boardService.findBoardDetail(bno);
      assertNull(b);
        System.out.println(b==null?"<<<<<<<<< there is no data":b);

    }


}