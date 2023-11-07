package kr.co.fns.chat.core.aop.exception;


import kr.co.fns.chat.base.dto.GeneralResponse;
import kr.co.fns.chat.base.service.MessageSourceService;
import kr.co.fns.chat.core.aop.exception.custom.FantooException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class CustomExceptionAdvice {

    @Autowired
    MessageSourceService me;

    @Autowired
    ExceptionAdvice exceptionAdvice;


    /**
     * Fantoo 서비스에서 정의한 에러코드 Advice
     */
    @ExceptionHandler(FantooException.class)
    public ResponseEntity fantooHandler(final FantooException e, WebRequest request) {
//        ApiResult<Object> apiResult = new ApiResult<>();
//        apiResult.setErrorCode(String.valueOf(e.getErrorCode().getCode()));

        String message = me.getLocaleMsg(String.valueOf(e.getErrorCode().getCode()), Locale.KOREAN); // apiResult.getCode(), Locale.KOREAN);
        if(!ObjectUtils.isEmpty(e.getAddMessage())) {
            if(message.indexOf("{}") > -1) {
                message = message.replace("{}", e.getAddMessage());
            } else {
                message += " (" + e.getAddMessage() + ")";
            }
        }
        HashMap<String, Object> hashMap = exceptionAdvice.errorHashMap(e, request, null);
        log.info("[Error] FantooException   Code: {} , Msg: {}", e.getErrorCode().getCode(), message);
        GeneralResponse response = new GeneralResponse<>(hashMap);
        response.setCode(String.valueOf(e.getErrorCode().getCode()));
        response.setMsg(message);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(response);
    }



}
