package com.airson.domain.notebook.controller;

import com.airson.domain.notebook.vo.Message;
import com.airson.domain.notebook.vo.UserSession;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * https://www.jianshu.com/p/2c9be4641d43
 * https://www.jianshu.com/p/840fded397be
 *
 * @author airson
 */
@ServerEndpoint(value = "/ws/im/{token}")
@Component
public class WebSocketServer {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @PostConstruct
    public void init() {
        logger.debug("websocket init");
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {

        Object userSessionObj = redisTemplate.opsForValue().get(token);
        if (null != userSessionObj) {
            UserSession userSession = (UserSession) userSessionObj;
            userSession.setWsSessionId(session.getId());
            userSession.setSession(session);
            redisTemplate.opsForValue().set(session.getId(), token);
        }
        sessionSet.add(session);
        int cnt = onlineCount.incrementAndGet(); // 在线数加1
        logger.info("有连接加入，当前连接数为：{},{}", cnt, token);
        sendMessage(session, "连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        Object tokenObj = redisTemplate.opsForValue().get(session.getId());
        sessionSet.remove(session);
        int cnt = onlineCount.decrementAndGet();
        if (null != tokenObj) {
            redisTemplate.delete(session.getId());
            logger.debug("remove wsSession:{}", session.getId());
            Object userSessionObj = redisTemplate.opsForValue().get(tokenObj);
            if (null != userSessionObj) {
                UserSession userSession = (UserSession) userSessionObj;
                userSession.setSession(null);
                userSession.setWsSessionId(null);
                logger.debug("remove userSession:{}", tokenObj);
            }
        }
        logger.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.debug("onMessage:{}", message);
        if (StringUtils.isEmpty(message)) {
            // invalid
        } else {
            Message msg = JSON.parseObject(message, Message.class);
            Long receiver = msg.getReceiver();
            Object userSessionObj = redisTemplate.opsForValue().get(receiver);
            if (null == userSessionObj) {
                logger.debug("onMessage receiver offline：{}", receiver);

            } else {
                UserSession userSession = (UserSession) userSessionObj;
                sendMessage(userSession.getSession(), message);
            }

        }
        sendMessage(session, "收到消息，消息内容：" + message);

    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("onError:{},session id:{}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        try {
            //session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("sendMessage fail:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void broadCastInfo(String message) throws IOException {
        for (Session session : sessionSet) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     *
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void sendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : sessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(session, message);
        } else {
            logger.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }

}