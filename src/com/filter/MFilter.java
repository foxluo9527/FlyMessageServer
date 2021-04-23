package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.po.Token;
import com.util.TokenUtil;
import org.apache.http.util.TextUtils;

/**
 * Servlet Filter implementation class DomoFilter
 */
@WebFilter("/*")
public class MFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		response.setCharacterEncoding("utf-8");
		HttpServletRequest r = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		r.setCharacterEncoding("UTF-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "*");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = r.getRequestURL().toString();
		System.out.println(url);
		if (url.contains("/SMS")||url.contains("/user/register")||url.contains("/user/login")
				||url.contains(".jpg")||url.contains(".png")||url.contains(".mp3")||url.contains(".txt")
				||url.contains(".JPG")||url.contains(".PNG")||url.contains(".MP3")||url.contains(".TXT")
				||url.contains(".html")||url.contains(".xml")||url.contains(".js")||url.contains(".css")
				||url.contains(".eot")||url.contains(".svg")||url.contains(".ttf")||url.contains(".woff")
				||url.contains(".woff2")||url.contains(".otf")||url.contains(".less")||url.contains(".scss")
				||url.contains(".json")||url.contains(".htm")||url.contains(".gif")||url.contains(".ico")) {
			chain.doFilter(request, response);
		}else if (url.contains("/user")||url.contains("/webSocketServer")||url.contains("/message")||url.contains("/friend")||url.contains("/group")||url.contains("/community")) {
			String token=r.getParameter("loginToken");
			Token loginToken;
			loginToken = TokenUtil.getTokenUser(token);
			JSONObject result=new JSONObject();
			if (loginToken!=null) {
				if (loginToken.getEndTime()<new Date().getTime()) {
					result.put("code", 512);
					result.put("msg", "µÇÂ¼³¬Ê±£¬ÇëÖØÐÂµÇÂ¼");
				}else {
					TokenUtil.resetTimeOut(loginToken.getTokenString(), 60*6);
					chain.doFilter(request, response);
					return;
				}
			}else {
				result.put("code", 511);
				result.put("msg", "ÇëÏÈµÇÂ¼");
			}
			PrintWriter out=response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			result=null;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
