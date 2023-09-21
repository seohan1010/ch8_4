package com.seohan1010.ch8_4.mapper;

import com.seohan1010.ch8_4.to.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {


   public abstract List<BoardDto> selectBoardList()throws Exception;
   public abstract void insertBoard(BoardDto boardDto)throws Exception;
   public abstract BoardDto selectBoardDetail(Long bno)throws Exception;
   public abstract void updateBoard(BoardDto boardDto)throws Exception;
   public abstract void updateBoardLike(Long bno)throws Exception;
   public abstract void deleteBoardDetail(Long bno)throws Exception;


}
