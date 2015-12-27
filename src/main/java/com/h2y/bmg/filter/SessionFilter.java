package com.h2y.bmg.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: h2ybmg
 * @what:
 * @author: duanxg
 * @update: 2014/10/13 17:03
 * @Email: 
 */
public class SessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //		// 不过滤的uri
        //		Set<String> noFilterSet = new HashSet<String>();
        //		noFilterSet.add("index.jsp");
        //		noFilterSet.add(".gif");
        //		noFilterSet.add("jpg");
        //
        //		//请求的uri
        //		String uri = request.getRequestURI();
        //		//System.out.println(uri.indexOf("mpay"));
        //		//uri中包含background时才进行过滤
        //		if (uri.indexOf("mpay") != -1) {
        //			// 是否过滤
        //			boolean doFilter = true;
        //			for (String s : noFilterSet) {
        //				if (uri.indexOf(s) != -1) {
        //					// 如果uri中包含不过滤的uri，则不进行过滤
        //					doFilter = false;
        //					break;
        //				}
        //			}
        //			if (doFilter) {
        //				//执行过滤
        //				filterChain.doFilter(request, response);
        //				//				}
        //			} else {
        //				// 如果不执行过滤，则继续
        //				filterChain.doFilter(request, response);
        //			}
        //		} else {
        //			// 如果uri中不包含background，则继续
        //			filterChain.doFilter(request, response);
        //		}
        filterChain.doFilter(request, response);
    }
}