package com.airson.domain.notebook.service;

import com.airson.domain.notebook.dao.mapper.ArticleMapper;
import com.airson.domain.notebook.dao.po.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper mapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Article load(Long id) {
        return mapper.load(id);
    }


}
