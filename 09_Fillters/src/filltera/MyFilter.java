package filltera;/*
author :Himal
version : 0.0.1
*/

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/customer","/item"})
//@WebFilter(urlPatterns = "/*") //all servelet wlt set krnn
public class MyFilter implements Filter {

    MyFilter(){
        System.out.println("Constructor");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter");

        filterChain.doFilter(servletRequest,servletResponse);//servelet ekt ywnna
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    //construtor
    //init
    //pattern krnl thiyn url ekt req kroth do fillter invoked wenwa
}
