package org.zerock.bitboard.controller;

import com.ibm.dtfj.corereaders.PageCache;
import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.dto.PageMaker;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "BoardListCotroller", value = "/board/list")

public class BoardListCotroller extends HttpServlet {
    //3. page는 숫자일 수도 있고 문자로 들어올 수도 있는 경우를 대비해 메서드 만들기
    private Integer getInt(String str){ // 예외처리부분에서 return = null로 받기 위해서는 int는 불가함 : Integer사용
        try {
            int value = Integer.parseInt(str);
            //8. 만약 파라미터값을 음수로 넣을 때를 대비
            if(value <= 0){
                return null;
            }

            return value;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //2. 파라미터 수집 -> 4. page의 파라미터를 수집할 때 메서드를 이용해서 수집
        //String pageStr = request.getParameter("page");
        Integer page = getInt(request.getParameter("page"));

        //5. size의 파라미터도 수집
        Integer size = getInt(request.getParameter("size"));

        //6. 수집한 파라미터를 가지고 데이터를 쌓기 시작
        PageDTO pageDTO = PageDTO.builder().build();

        if(page!=null) { pageDTO.setPage(page);}
        if (size!=null) { pageDTO.setSize(size);}

        //7. log로 확인 : 파라미터가 없을 때와 있을 때를 모두 확인해보기
        log.info("==============STEP 1==================");
        log.info(pageDTO);

        //9. service를 호출 / pageDTO던지면 dtoList나온다.
        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("=========== step2 ============");
        log.info(dtoList);

        request.setAttribute("dtoList",dtoList);

        //10.pageMaker 가져오기
        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),1230);
        request.setAttribute("pageMaker",pageMaker);

        //1. 데이터 보낼 경로 설정
        request.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(request,response);
    }
}
