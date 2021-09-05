package org.zerock.bitboard.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardService {
    INSTANCE;

    public List<BoardDTO> getList(PageDTO pageDTO) throws RuntimeException{
        log.info(pageDTO);

        return BoardDAO.INSTANCE.list(pageDTO);
    }

    public BoardDTO read(Integer bno) throws RuntimeException{
        return BoardDAO.INSTANCE.select(bno);
    }

    public Integer register(BoardDTO boardDTO) throws RuntimeException{
        return BoardDAO.INSTANCE.insert(boardDTO);
    }


}
