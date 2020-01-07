package com.airson.domain.notebook.controller;

import com.airson.domain.notebook.config.Const;
import com.airson.domain.notebook.handler.ImHandler;
import com.airson.domain.notebook.vo.Message;
import com.airson.domain.notebook.vo.WebSocketRequest;
import com.airson.domain.notebook.vo.WebSocketResponse;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * TODO
 *
 * @author airson
 */
//@Api(value = "IM相关接口", tags = "IM")
@Component
public class WebSocketMsgController {

    @Autowired
    private ImHandler imHandler;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*@MessageMapping(Const.WS_MSG_ENDPOINT)
    @SendTo(Const.WS_MSG_TOPIC)
    public WebSocketResponse greeting(WebSocketRequest message) throws Exception {
        // 模拟延时，以便测试客户端是否在异步工作
        //Thread.sleep(1000);
        WebSocketResponse response = new WebSocketResponse();
        response.setContent("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
        return response;
    }*/

}
