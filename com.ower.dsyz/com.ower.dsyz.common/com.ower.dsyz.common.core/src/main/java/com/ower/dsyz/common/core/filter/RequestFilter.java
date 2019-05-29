package com.ower.dsyz.common.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.util.json.Jackson;
import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.holder.CurrentThreadHolder;
import com.ower.dsyz.common.core.response.CustomResponse;


@WebFilter(filterName = "requestFilter",urlPatterns = {"/*"})
public class RequestFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("requestFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestId = request.getParameter("requestId");
		String userId = ((HttpServletRequest)request).getHeader("userId");
		CurrentThreadHolder.setRequestId(requestId);
		CurrentThreadHolder.setUserId(userId);
		if(StringUtils.isBlank(requestId)){
			String resp = Jackson.toJsonString(CustomResponse.error(ErrorCodeConstants.PARAM_ERROR, "requestId不能为空"));
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(resp);
			response.getWriter().flush();
			response.getWriter().close();
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("requestFilter destory");
	}

}
