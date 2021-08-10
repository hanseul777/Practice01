package org.zerock.practice01;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "calcController", value = "/calc")
@Log4j2
public class calcController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doGet..................");
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/calcInput.jsp");

        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doPost...............");

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/calcResult.jsp");

        dispatcher.forward(request,response);

        String num1Str = request.getParameter("num1Str");
        String num2Str = request.getParameter("num2Str");
        String oper = request.getParameter("oper");

        log.info("num1Str : " + num1Str);
        log.info("num2Str : " + num2Str);
        log.info("oper : " + oper);

    }
}
