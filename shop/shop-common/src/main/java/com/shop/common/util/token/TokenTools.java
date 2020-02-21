package com.shop.common.util.token;

import com.auth0.jwt.JWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: Token的工具类
 * @author: csc
 * @create: 2019/12/23 9:41
 */
public class TokenTools {
    public static String getTokenUserId() {
        String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 生成token放入session
     *
     * @param request
     * @param tokenServerkey
     */
    public static void createToken(HttpServletRequest request, String tokenServerkey) {
        String token = TokenProccessor.getInstance().makeToken();
        request.getSession().setAttribute(tokenServerkey, token);
    }


    /**
     * 移除token
     *
     * @param request
     * @param tokenServerkey
     */
    public static void removeToken(HttpServletRequest request, String tokenServerkey) {
        request.getSession().removeAttribute(tokenServerkey);
    }


    /**
     * 判断请求参数中的token是否和session中一致
     *
     * @param request
     * @param tokenClientkey
     * @param tokenServerkey
     * @return
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request, String tokenClientkey, String tokenServerkey) {
        String token_client = request.getParameter(tokenClientkey);
        if (StringUtils.isEmpty(token_client)) {
            return false;
        }
        String token_server = (String) request.getSession().getAttribute(tokenServerkey);
        if (StringUtils.isEmpty(token_server)) {
            return false;
        }

        if (!token_server.equals(token_client)) {
            return false;
        }
        return true;
    }

}
