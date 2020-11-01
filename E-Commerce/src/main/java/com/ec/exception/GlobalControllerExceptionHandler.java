/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.exception;

import com.ec.controller.ProductController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ec.response.CommonResponse;

/**
 *
 * @author lsgp
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @ExceptionHandler
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e) {

        Map<Object, Object> map = new HashMap<>();
        LOGGER.error("Exception trace --> ", e);
        CommonResponse commonResponse = new CommonResponse(3010, "Exception" + "-" + e.getMessage());
        map.put("response", commonResponse);
        return map;

    }

}
