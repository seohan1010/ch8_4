package com.seohan1010.ch8_4.service;

import com.seohan1010.ch8_4.to.BoardDto;
import com.seohan1010.ch8_4.to.SearchCondition;

import java.util.List;

public interface BoardService {

   public abstract List<BoardDto> searchBoardList(SearchCondition sc)throws Exception;

   public abstract List<BoardDto> findBoardList() throws Exception;

    public abstract void registerBoard(BoardDto boardDto) throws Exception;

    public abstract BoardDto findBoardDetail(Long bno) throws Exception;

    public abstract void modifyBoard(BoardDto boardDto) throws Exception;

    public abstract void modifyBoardLike(Long bno) throws Exception;

    public abstract void removeBoardDetail(Long bno) throws Exception;
}
