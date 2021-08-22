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

        log.info("BoardService getList........................");
        log.info(pageDTO);

        return BoardDAO.INSTANCE.list(pageDTO);
    }


}
