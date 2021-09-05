package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(value = "/board/read")
public class ReadController extends HttpServlet {

    //ListController에 getInt()만들었던게 여기에서도 필요함 -> 가져다 쓰는 방법은 상속과 조합 두가지 방법이 있음.
    private Integer getInt(String str){
        try {
            int value = Integer.parseInt(str);
            if (value <= 0) {
                return null;
            }
            return value;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer bno = Integer.parseInt(request.getParameter("bno"));
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));


        PageDTO pageDTO = PageDTO.builder().build();

        //null값이면 디폴드값이 출력
        if(page!=null){pageDTO.setPage(page);}
        if(size!=null){pageDTO.setSize(size);}

        //게시물 가져오기
        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        //택배보내기
        request.setAttribute("boardDTO",boardDTO);
        request.setAttribute("pageDTO",pageDTO);

        request.getRequestDispatcher("/WEB-INF/board/read.jsp").forward(request,response);
    }
}
