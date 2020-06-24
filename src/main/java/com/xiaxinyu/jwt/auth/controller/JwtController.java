package com.xiaxinyu.jwt.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaxinyu.jwt.auth.core.JwtToken;
import com.xiaxinyu.jwt.auth.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    /**
     * 登录并获取token
     *
     * @param userName
     * @param passWord
     * @return
     */
    @PostMapping("/login")
    public Object login(String userName, String passWord) {
        JSONObject jsonObject = new JSONObject();
        // 检验用户是否存在(为了简单，这里假设用户存在，并制造一个uuid假设为用户id)
        String userId = UUID.randomUUID().toString();
        // 生成签名
        String token = JwtUtils.sign(userId);
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("userName", userName);
        userInfo.put("passWord", passWord);
        jsonObject.put("token", token);
        jsonObject.put("user", userInfo);
        return jsonObject;
    }

    /**
     * 该接口需要带签名才能访问
     *
     * @return
     */
    @JwtToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}