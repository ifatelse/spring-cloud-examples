package com.zuul.cn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : asus
 * @version : 1.0
 * @Description : 自定义Zuul的过滤器
 * @Date : 2018/5/3 13:51
 * @Copyright : Copyright (c) 2018 All Rights Reserved
 */
public class MyFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /*
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     *  pre：可以在请求被路由之前调用
     *  routing：在路由请求时候被调用
     *  post：在routing和error过滤器之后被调用
     *  error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /*
     * 通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
     * 返回true，所以该过滤器总是生效。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*
     * 过滤器的具体逻辑
     *
     * 需要注意:
     *     通过 ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
     * 然后通过 ctx.setResponseStatusCode(401)设置了其返回的错误码，
     * 当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");// 获取请求的参数
        if(token == null){
            logger.warn("access token is empty");
            ctx.setSendZuulResponse(false);         //不对其进行路由
            ctx.setResponseStatusCode(401);         //响应码
            ctx.setResponseBody("token is empty");  //响应值
            return null;
        }
        logger.info("access token ok");
        return null;
    }
}
