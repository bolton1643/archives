package com.sw.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = ActionContext.getContext().getSession();
		Object username = session.get("user");
		if (username == null) {
			return "page/login.jsp";
		} else {
			return invocation.invoke();
		}
	}

}
