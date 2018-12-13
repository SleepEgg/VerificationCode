package com.big3.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class codeCheck extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
//        开始验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if(code != null && !"".equals(code) &&
                sessionCode != null && !"".equals(sessionCode)) {
            if(code.equalsIgnoreCase(sessionCode)){
            response.getWriter().println("验证码正确！");
        }else{
            response.getWriter().println("验证码错误！");
            }
        }
        else{
            response.getWriter().println("验证失败！请重试。");
        }
    }
}
