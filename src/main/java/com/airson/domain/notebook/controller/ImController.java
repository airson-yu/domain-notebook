package com.airson.domain.notebook.controller;

import com.airson.domain.notebook.config.Const;
import com.airson.domain.notebook.dao.po.Article;
import com.airson.domain.notebook.service.ArticleService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @author airson
 */
@Controller
public class ImController {

    @Autowired
    private ArticleService service;
    @Autowired
    private KafkaTemplate  kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/im")
    public String im() {

        return "im";

    }

    @KafkaListener(topics = {Const.MQ_TOPIC_IM}, groupId = Const.MQ_GROUP_IM)
    public void processMessage(String content) {
        // ...
        logger.debug("imTopic content:{}", content);
    }

    @ResponseBody
    @RequestMapping(value = "/send")
    public JSONObject send(HttpServletRequest request, HttpSession session, String message) {
        logger.debug("send message:{}", message);

        JSONObject json = new JSONObject();
        json.put("message", message);

        kafkaTemplate.send(Const.MQ_TOPIC_DEMO, message);

        return json;

    }

}
