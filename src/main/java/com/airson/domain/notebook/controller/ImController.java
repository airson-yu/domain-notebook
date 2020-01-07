package com.airson.domain.notebook.controller;

import com.airson.domain.notebook.handler.ImHandler;
import com.airson.domain.notebook.tools.Result;
import com.airson.domain.notebook.vo.Login;
import com.airson.domain.notebook.vo.Message;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * TODO
 *
 * @author airson
 */
@Validated
@Controller
//@Api(value = "IM相关接口", tags = "IM")
@RequestMapping(value = "/v1/im")
public class ImController {

    @Autowired
    private ImHandler imHandler;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //@ApiOperation(value = "IM页面", tags = "IM")
    @RequestMapping(value = "")
    public String im() {
        return "im";
    }

    @ResponseBody
    @ApiOperation(value = "IM登录", tags = "IM")
    @RequestMapping(value = "/login")
    public JSONObject login(@Valid Login login, BindingResult result) {
        JSONObject json = new JSONObject();
        imHandler.login(json, login);
        return Result.success(json);
    }

    @ResponseBody
    @ApiOperation(value = "表单发送IM", tags = "IM")
    @RequestMapping(value = "/form/send")
    public JSONObject form_send(HttpServletRequest request, HttpSession session, @RequestParam Message message) {
        return imHandler.send_message(message);
    }

    @ResponseBody
    @ApiOperation(value = "JSON发送IM", tags = "IM")
    @RequestMapping(value = "/json/send")
    public JSONObject json_send(HttpServletRequest request, HttpSession session, @RequestBody Message message) {
        return imHandler.send_message(message);
    }


}
