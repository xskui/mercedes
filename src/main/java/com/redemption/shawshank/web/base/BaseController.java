package com.redemption.shawshank.web.base;

import com.sun.tools.internal.ws.wsdl.document.http.HTTPConstants;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : xingshukui .
 * Date : 2017/7/6.
 * Desc :
 */
public abstract class BaseController {

	private final static Logger log = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 统一异常处理
	 * @param request
	 * @param response
	 * @param exception
	 */
	@ExceptionHandler
	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		log.error("统一异常处理：", exception);
		request.setAttribute("ex", exception);
		if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
			request.setAttribute("requestHeader", "ajax");
		}
		// shiro没有权限异常
		if (exception instanceof UnauthorizedException) {
			return "403";
		}
		// shiro会话已过期异常
		if (exception instanceof InvalidSessionException) {
			return "error";
		}
		return "error";
	}


}
