/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.shiro.filter;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyspace.xysvr.function.manager.user.shiro.MgrShiroUser;

/**
 * 后台管理控制并发登录人数过滤器。
 * 
 * @author ChenFangjie(2015年2月5日 下午3:29:02)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class MgrKickoutSessionControlFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(MgrKickoutSessionControlFilter.class);

    private boolean kickoutAfter = false; // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户
    private int maxSession = 1; // 同一个帐号最大会话数默认为1
    private String kickoutUrl; // 被踢出后重定向到的地址

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("xysvr2-mgr:shiro_kickout_session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = super.getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            // 如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        // String username = (String) subject.getPrincipal();
        MgrShiroUser shiroUser = (MgrShiroUser) subject.getPrincipal();
        String username = "mgr-kickout-" + shiroUser.getUsername();
        Serializable sessionId = session.getId();

        Deque<Serializable> deque = this.cache.get(username);

        // 同步控制
        synchronized (username) {
            if (deque == null) {
                deque = new LinkedList<Serializable>();
            }
        }

        // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("mgrkickout") == null) {
            deque.push(sessionId);
            this.cache.put(username, deque); // set
        }

        // 如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > this.maxSession) {
            Serializable kickoutSessionId = null;
            if (this.kickoutAfter) { // 如果踢出后者
                kickoutSessionId = deque.removeFirst();
            }
            else { // 否则踢出前者
                kickoutSessionId = deque.removeLast();
            }

            this.cache.put(username, deque); // set

            try {
                Session kickoutSession = this.sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    // 设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("mgrkickout", Boolean.TRUE);
                }
            }
            catch (Exception e) { // ignore exception
                logger.warn(e.getMessage());
            }
        }

        // 如果被踢出了，直接退出，重定向到踢出后的地址或向客户端返回json数据
        if (session.getAttribute("mgrkickout") != null) {
            // 会话被踢出了
            try {
                subject.logout();
            }
            catch (Exception e) { // ignore
                logger.warn(e.getMessage());
            }
            super.saveRequest(request);
            WebUtils.issueRedirect(request, response, this.kickoutUrl);
            logger.debug(this.kickoutUrl);

            return false;
        }

        return true;
    }

}
