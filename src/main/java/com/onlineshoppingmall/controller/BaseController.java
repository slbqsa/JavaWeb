package com.onlineshoppingmall.controller;

import com.onlineshoppingmall.error.EmError;
import com.onlineshoppingmall.error.IllegalException;
import com.onlineshoppingmall.reply.CommonReply;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static  final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String, Object> responses = new HashMap<>();
        if(ex instanceof IllegalException) {
            IllegalException illegalException = (IllegalException) ex;
            responses.put("errCode", illegalException.getErrorCode());
            responses.put("errMsg", illegalException.getErrorMassage());
//            return CommonReply.create(responses,"fail");

        }else {
            responses.put("errCode", EmError.ILLEGAL_VALUED.getErrorCode());
            responses.put("errMsg", EmError.ILLEGAL_VALUED.getErrorMassage());

        }
        return CommonReply.create(responses,"fail");


    }
}
