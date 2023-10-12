package com.seohan1010.ch8_4.controller;


import com.seohan1010.ch8_4.mapper.BoardMapper;
import com.seohan1010.ch8_4.service.BoardService;
import com.seohan1010.ch8_4.to.BoardDto;
import com.seohan1010.ch8_4.to.PageHandler;
import com.seohan1010.ch8_4.to.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardMapper boardMapper;

    @RequestMapping(value = "/board/all", method = RequestMethod.GET)
    public List<BoardDto> selectAll() throws Exception {

        List<BoardDto> b = boardMapper.selectAll();
        return b;
    }


    // 프론트에서 유효하지 못한 값들이 넘어오면 에러 코드와 함께 빈배열을 반환한다.
    // 페이지 핸들러로 검색된 게시판의 totalCnt값을 넘겨줘서 계산한 값들을 백단에서 같이 넘겨준다. 검색한 후에는 검색된 결과에 대한
    // 페이징이 가능하게 하는 프론트 단의 로직이 필요하다.
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<BoardDto>> searchBoardList(@RequestBody SearchCondition sc) throws Exception {
        List<BoardDto> list = null;
        System.out.println("<<<<<<<<<<<<<<<< sc = " + sc);


        // 검색된 게시글에 들어가는 offset과 limit은 검색된 게시글의 갯수를 세는 쿼리문의 offset , limit 과 같아야 한다.
        Integer page = sc.getPage();
        Integer pageSize = sc.getPageSize();
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        SearchCondition sc2 = new SearchCondition();
        sc2.setPage(page);
        sc2.setPageSize(pageSize);
        sc2.setOption(sc.getOption());
        sc2.setKeyword(sc.getKeyword());
        int totalCnt = boardService.searchBoardListCnt(sc);
        PageHandler ph = new PageHandler(totalCnt, sc);


        try {
            list = boardService.searchBoardList(sc);
            System.out.println(list == null ? ">>>>>>>>>>>>>no data found" : list);
            if (list.size() == 0)
                return new ResponseEntity<List<BoardDto>>(list, HttpStatus.BAD_REQUEST); // 204번 에러 코드
            return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK); // 200번 코드
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<BoardDto>>(list, HttpStatus.BAD_REQUEST); // 400번 코드
        }

    }

    // 프론트에서 넘어온 값이 DB에 유효하지 않은 값이면 406번 코드를 반환

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerBoard(@RequestBody BoardDto boardDto) {
        System.out.println("<<<<<<<<<<<< boardDto : " + boardDto);
        try {
            boardService.registerBoard(boardDto);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK); // 200번 코드를 반환
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE); // 406번 코드를 반환
        }

    }

    //okay condition
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findBoardList(Integer page, Integer pageSize) throws Exception {
        List<BoardDto> list = null;

        Map map = new HashMap();
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;


        try {

            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, new SearchCondition("","",page,pageSize));

            // 해당 범위에 있는 게시물을 가져오기위함
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);
            list = boardService.findBoardList(map);

            // map안에 있는 데이터를 정리
            map.clear();

            // db에서 가져온 게시물 리스트와 네비게이션을 위한 데이터를 프론트로 보냄
            map.put("list", list);
            map.put("ph", pageHandler);

            System.out.println(" <<<<<<<<<<<<<<< map = " + map);
            //list에 데이터가 없으면은 204번 코드를 반환                           // NO_CONTENT를 사용하면은 데이터가 아예 프론트로 안간다.
            if (list.size() == 0)
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST); // 204번 코드를 반환
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK); // 200번 코드를 반환
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST); // 400번 코드를 반환
        }

    }


    // 아래의 url에서 {bno}에 값을 아예주지 않으면은 아래의 메서드를 찾지 못한다.
    // 없는 게시물에 대한 요청을 보낼수 없으므로 아래의 메서드 또한 유효하다.
    @RequestMapping(value = "/detail/{bno}", method = RequestMethod.GET)
    public ResponseEntity<BoardDto> findBoardDetail(@PathVariable("bno") Long bno) throws Exception {
        System.out.println("<<<<<<<<<<< bno : " + bno);
        BoardDto boardDto = null;
        try {
            boardDto = boardService.findBoardDetail((Long) bno);
            if (boardDto == null) return new ResponseEntity<BoardDto>(boardDto, HttpStatus.NOT_FOUND); // 404번 코드를 반환
            System.out.println("<<<<<<<< boardDto : " + boardDto);
            return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK); // 200번 코드를 반환

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<BoardDto>(boardDto, HttpStatus.BAD_REQUEST); // 204번 코드를 반환
        }

    }


    //아래의 메서드는 null이 넘어 오더라도 sql exception이 발생하지 않아서 프론트에 200번 코드를 보낸다.
    // ---> 없는 게시물에서 modify를 할수는 없으므로 아래의 메서드는 유효한거 같다.
    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> modifyBoard(@RequestBody BoardDto boardDto) {

        try {
            boardService.modifyBoard(boardDto);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK); // 200번 코드를 반환
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_MODIFIED); // 304번 코드를 반환
        }

    }


    // delete는 잘 사용하지 않는다고 한다. 다음 프로젝트 에서는 delete를 다른 메서드로
    // 구현 해보자
    // delete는 업데이트로 구현했다.
    @RequestMapping(value = "/board/{bno}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeBoard(@PathVariable("bno") Long bno) {

        try {
            boardService.removeBoardDetail(bno);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK); // 200번 코드를 반환
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);  // 406번 코드를 반환
        }

    }


}
