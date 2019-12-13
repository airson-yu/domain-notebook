package com.airson.domain.notebook.controller;

import com.airson.domain.notebook.dao.po.Article;
import com.airson.domain.notebook.service.ArticleService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleController {

    @Autowired
    private ArticleService service;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/load_article")
    public JSONObject load_article(HttpServletRequest request, HttpSession session, Long id) {
        logger.debug("load_article:{}", id);
        JSONObject json = new JSONObject();
        Article obj = service.load(id);
        json.put("article", obj);
        return json;

    }

}
