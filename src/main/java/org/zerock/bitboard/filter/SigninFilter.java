package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "signin", urlPatterns = {"/board/register", "/board/read"})
public class SigninFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SigninFilter...............");
        log.info("SigninFilter...............");
        log.info("SigninFilter...............");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("SigninFilter---------run----------");

        //ServletRequest와 ServletResponse는 HttpServletRequest와 HttpServletResponse보다 더 넓은 범위의 개념 -> 다운캐스팅해야한다.
        //HttpServletRequest가 브라우저가 보낸 정보를 모두 담아두고 -> session이 처리하는 것
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //브라우저가 가지고 있는 세션값(req.getSession())과 세션저장소에 들어있는 세션값(데이터값)을 비교
        // 두 값이 동일하다면 로그인을 한 상태인 것
        HttpSession session = req.getSession();

        //두 값이 다르다면(로그인값이 들어와있지 않다면) 로그인을 하지 않은 것
        if(session.getAttribute("member") == null) {
            res.sendRedirect("/login");
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
