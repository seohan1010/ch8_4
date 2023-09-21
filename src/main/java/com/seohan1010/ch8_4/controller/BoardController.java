package com.seohan1010.ch8_4.controller;


import com.seohan1010.ch8_4.service.BoardService;
import com.seohan1010.ch8_4.to.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins={"http://localhost:3000","*"})
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;


    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerBoard(@RequestBody BoardDto boardDto) {

        try {
            boardService.registerBoard(boardDto);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ResponseEntity<List<BoardDto>> findBoardList() throws Exception {
        List<BoardDto> list = null;
        try {
            list = boardService.findBoardList();
            return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<BoardDto>>(list, HttpStatus.NO_CONTENT);
        }

    }


    @RequestMapping(value = "/detail/{bno}", method = RequestMethod.GET)
    public ResponseEntity<BoardDto> findBoardDetail(@PathVariable("bno") Long bno) throws Exception {
        System.out.println("<<<<<<<<<<< bno : " + bno);
        BoardDto boardDto = null;
        try {
            boardDto = boardService.findBoardDetail((Long) bno);
            if (boardDto == null) return new ResponseEntity<BoardDto>(boardDto, HttpStatus.NOT_FOUND);
            System.out.println("<<<<<<<< boardDto : " + boardDto);
            return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<BoardDto>(boardDto, HttpStatus.NO_CONTENT);
        }

    }


    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> modifyBoard(@RequestBody BoardDto boardDto){

        try {
            boardService.modifyBoard(boardDto);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_MODIFIED);
        }

    }



    // delete는 잘 사용하지 않는다고 한다. 다음 프로젝트 에서는 delete를 다른 메서드로
    // 구현 해보자
    @RequestMapping(value = "/board/{bno}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeBoard(@PathVariable("bno") Long bno) {

        try {
            boardService.removeBoardDetail(bno);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


}
