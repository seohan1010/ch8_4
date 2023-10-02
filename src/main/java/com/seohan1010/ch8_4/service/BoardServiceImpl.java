package com.seohan1010.ch8_4.service;

import com.seohan1010.ch8_4.mapper.BoardMapper;
import com.seohan1010.ch8_4.to.BoardDto;
import com.seohan1010.ch8_4.to.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;


    @Override
    public int getCount()throws Exception{
     return boardMapper.count();
    }


    @Override
    public List<BoardDto> searchBoardList(SearchCondition sc) throws Exception {

        return boardMapper.searchBoardList(sc);

    }


    @Override
    public List<BoardDto> findBoardList(Map map) throws Exception {
        return boardMapper.selectBoardList(map);
    }

    @Override
    public void registerBoard(BoardDto boardDto) throws Exception {
        boardMapper.insertBoard(boardDto);
    }

    @Override
    public BoardDto findBoardDetail(Long bno) throws Exception {
        return boardMapper.selectBoardDetail(bno);
    }


    @Override
    public void modifyBoard(BoardDto boardDto) throws Exception {
        boardMapper.updateBoard(boardDto);
    }

    @Override
    public void modifyBoardLike(Long bno) throws Exception {
        boardMapper.updateBoardLike(bno);
    }

    @Override
    public void removeBoardDetail(Long bno) throws Exception {
        boardMapper.deleteBoardDetail(bno);
    }


}
