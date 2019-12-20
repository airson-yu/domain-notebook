package com.airson.domain.notebook.controller;

import com.airson.domain.notebook.config.Const;
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
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/")
    public String im() {
        return "im";
    }

}
