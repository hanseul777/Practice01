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
@WebServlet(name = "ModifyController", value = "/board/modify")
public class ModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer bno = Integer.parseInt(request.getParameter("bno"));
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if(page!=null) {pageDTO.setPage(page);}
        if(size!=null) {pageDTO.setSize(size);}

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO",boardDTO);
        request.setAttribute("PageDTO",pageDTO);

        request.getRequestDispatcher("/WEB-INF/board/modify.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer bno = Integer.parseInt(request.getParameter("bno"));
        String modifyTitle = request.getParameter("title");
        String modifyContent = request.getParameter("content");

        BoardDTO dto = BoardDTO.builder()
                .bno(bno)
                .title(modifyTitle)
                .content(modifyContent).build();

        BoardService.INSTANCE.modify(dto);

        request.setAttribute("title",dto.getTitle());
        request.setAttribute("content",dto.getContent());

        response.sendRedirect("/board/list");

    }
}
