package com.seohan1010.ch8_4.service;

import com.seohan1010.ch8_4.mapper.BoardMapper;
import com.seohan1010.ch8_4.to.BoardDto;
import com.seohan1010.ch8_4.to.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;


    public List<BoardDto> searchBoardList(SearchCondition sc) throws Exception {

        return boardMapper.searchBoardList(sc);

    }


    @Override
    public List<BoardDto> findBoardList() throws Exception {
        return boardMapper.selectBoardList();
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
