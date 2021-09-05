package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "logfilter", urlPatterns = {"/*"}) // 전체에 적용
public class LogFilter implements Filter { //브라우저 ---필터---> 컨트롤러

    //필터의 시작부분 : 한 번만 실행한다.
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    log.info("--------------------");
    log.info("init filter");
    log.info("--------------------");
    }

    //필터에서 실제로 동작하는 부분
    //FilterChain chain : filter에 걸린 내용을 다음으로 보낼건지 말건지 결정
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("doFilter............");
        log.info("doFilter............");
        log.info("doFilter............");

        //한글체크필터 : 예전에는 doPost마다 적용했어야함. 지금은 다 여기를 거쳐서 한글깨지지않고 나가는 것.
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request,response);
    }

    //필터의 마지막부분 한 번만 실행된다.
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
