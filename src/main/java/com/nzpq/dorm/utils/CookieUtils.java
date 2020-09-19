package com.nzpq.dorm.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static void addCookie(String cookieName, int time,String stuCode,String password,
                        HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = getCookieByName(cookieName,request);
        if(cookie == null){
            cookie = new Cookie(cookieName,stuCode+"_"+password);
        }else{
            cookie.setValue(stuCode+"_"+password);
        }
        cookie.setMaxAge(time);//设置cookie有效时间
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

    }

    public static Cookie getCookieByName(String cookieName,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
