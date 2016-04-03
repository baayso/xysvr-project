/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.websocket.interceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 
 * An interceptor to copy information from the Shiro HTTP session to the "handshake attributes" map to made available via{@link WebSocketSession#getAttributes()}.
 * 
 * <p>
 * Copies a subset or all Shiro HTTP session attributes and/or the HTTP session id under the key {@link #SHIRO_SESSION_ID_ATTR_NAME}.
 * 
 * <p>
 * 参考 {@linkplain org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor}
 * 
 * @author ChenFangjie(2015年3月13日 下午6:06:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class ShiroSessionHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * The name of the attribute under which the Shiro HTTP session id is exposed when {@link #setCopyHttpSessionId(boolean) copyHttpSessionId} is "true".
     */
    public static final String SHIRO_SESSION_ID_ATTR_NAME = "SHIRO.SESSION.ID";

    private final Collection<String> attributeNames;

    private boolean copyAllAttributes;

    private boolean copyShiroSessionId = true;

    public ShiroSessionHandshakeInterceptor() {
        this.attributeNames = Collections.emptyList();
        this.copyAllAttributes = true;
    }

    public ShiroSessionHandshakeInterceptor(Collection<String> attributeNames) {
        this.attributeNames = Collections.unmodifiableCollection(attributeNames);
        this.copyAllAttributes = false;
    }

    public Collection<String> getAttributeNames() {
        return this.attributeNames;
    }

    public void setCopyAllAttributes(boolean copyAllAttributes) {
        this.copyAllAttributes = copyAllAttributes;
    }

    public boolean isCopyAllAttributes() {
        return this.copyAllAttributes;
    }

    public void setCopyShiroSessionId(boolean copyShiroSessionId) {
        this.copyShiroSessionId = copyShiroSessionId;
    }

    public boolean isCopyShiroSessionId() {
        return this.copyShiroSessionId;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            if (this.isCopyShiroSessionId()) {
                attributes.put(SHIRO_SESSION_ID_ATTR_NAME, session.getId());
            }
            Collection<Object> keys = session.getAttributeKeys();
            keys.forEach(key -> {
                if (this.isCopyAllAttributes() || this.getAttributeNames().contains(key)) {
                    attributes.put((String) key, session.getAttribute(key));
                }
            });
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }

}
