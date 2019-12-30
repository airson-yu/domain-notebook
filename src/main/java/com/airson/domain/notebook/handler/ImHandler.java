package com.airson.domain.notebook.handler;

import com.airson.domain.notebook.config.Const;
import com.airson.domain.notebook.service.ArticleService;
import com.airson.domain.notebook.tools.Result;
import com.airson.domain.notebook.vo.Message;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @author airson
 */
@Component
public class ImHandler {

    @Autowired
    private ArticleService service;
    @Autowired
    private KafkaTemplate  kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {Const.MQ_TOPIC_IM}, groupId = Const.MQ_GROUP_IM)
    private void processMessage(String content) {
        // ...
        logger.debug("imTopic content:{}", content);
    }

    public JSONObject send_message(Message message) {
        JSONObject json = new JSONObject();
        if (null == message || StringUtils.isEmpty(message.getContent())) {
            logger.info("message empty");
            json.put("success", false);
            json.put("msg", "message empty");
            Result.failure(json, "消息不能为空", "msg_empty");
            return json;
        }
        logger.debug("send message:{}", message);

        json.put("message", message);

        kafkaTemplate.send(Const.MQ_TOPIC_IM, JSON.toJSONString(message));

        Result.success(json);
        return json;
    }

}
