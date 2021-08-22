package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

@Log4j2
public class BoardDAOTests {
    @Test
    public void testInsert() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("title")
                .content("contnet")
                .writer("writer")
                .build();
        BoardDAO.INSTANCE.insert(boardDTO);
    }
    @Test
    public void testSelect(){
        log.info("test...........................");
        log.info("test...........................");
        log.info("test...........................");
        log.info(BoardDAO.INSTANCE.select(1));
    }

    @Test
    public void testList(){
        log.info("test...........................");
        log.info("test...........................");
        log.info("test...........................");
//        PageDTO pageDTO = PageDTO.builder().build();//디폴트값으로 들어감
        PageDTO pageDTO = PageDTO.builder().page(3).build(); // 입력한 페이지가 출력

        //pageDTO의 형식에 맞춰서 boardDTO값을 반복해서 출력
        BoardDAO.INSTANCE.list(pageDTO).forEach(boardDTO -> log.info(boardDTO));
    }

    @Test
    public void testDelete(){
        BoardDAO.INSTANCE.delete(2);
    }

    @Test
    public void testUpdate(){
        BoardDTO boardDTO =
                BoardDTO.builder().title("testTitle")
                        .content("testContent")
                        .build();
        BoardDAO.INSTANCE.update(boardDTO);
    }
}
