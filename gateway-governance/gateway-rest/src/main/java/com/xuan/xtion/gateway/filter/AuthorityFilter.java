package com.xuan.xtion.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xuanwu.xtion.common.exception.AppErrorCode;
import com.xuanwu.xtion.common.response.RestHelper;
import com.xuanwu.xtion.common.util.JsonUtil;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthorityFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        context.setResponseGZipped(true);
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        context.setResponseBody(JsonUtil.getJson(RestHelper.failure(AppErrorCode.AUTHORITY_ERROR.getErrorMsg()).getBody()));
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }
}
