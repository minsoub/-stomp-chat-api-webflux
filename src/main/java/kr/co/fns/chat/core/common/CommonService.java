package kr.co.fns.chat.core.common;


import kr.co.fns.chat.base.service.MessageSourceService;
import kr.co.fns.chat.core.aop.exception.ErrorCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service("CommonService")
public class CommonService {


    @Autowired
    MessageSourceService me;




    /**
     * Json 형태의 String을 Json으로 변환하여 손쉽게 get 한다.
     * google 의 json-simple 사용.
     */
    @SneakyThrows(ParseException.class)
    public JSONObject getStringToJson(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(jsonString);

        JSONObject jsonObj = (JSONObject) obj;

        return jsonObj;
    }


    /**
     * 단순 Header 조회용.
     */
    // [Debug] Header 조회
    public void printStartLine(HttpServletRequest request) {
        log.info("--- REQUEST-LINE - start ---");
        log.info("request.getMethod() = " + request.getMethod()); //GET
        log.info("request.getProtocal() = " + request.getProtocol()); //HTTP/1.1
        log.info("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        log.info("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        log.info("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        log.info("request.getQueryString() = " +
                request.getQueryString());
        log.info("request.isSecure() = " + request.isSecure()); //https사용 유무
        log.info("--- REQUEST-LINE - end ---");
    }


    // [Debug] Header 모든 정보
    public void printHeaders(HttpServletRequest request) {
        log.info("--- Headers - start ---");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        log.info(headerName + ": "+request.getHeader(headerName)));

        log.info("--- Headers - end ---");
    }

    // [Debug] Header 편리한 조회
    public void printHeaderUtils(HttpServletRequest request) {
        log.info("--- Header 편의 조회 start ---");

        log.info("[Host 편의 조회]");
        log.info("request.getServerName() = " + request.getServerName()); //Host 헤더
        log.info("request.getServerPort() = " + request.getServerPort()); //Host 헤더


        log.info("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> log.info("locale = " + locale));
        log.info("request.getLocale() = " + request.getLocale());



        log.info("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                log.info(cookie.getName() + ": " + cookie.getValue());
            }
        }


        log.info("[Content 편의 조회]");
        log.info("request.getContentType() = " + request.getContentType());
        log.info("request.getContentLength() = " + request.getContentLength());
        log.info("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        log.info("--- Header 편의 조회 end ---");
    }



    /**
     * 에러코드 조회
     */
    public Map<String, List<HashMap<String, Object>>> getErrorCode(String code) {

        // 에러코드 전체 List 배열로 가져온다.
        ErrorCode[] errorCodes = ErrorCode.values();

        // 오름차순 정렬을 위한 comparator
        Comparator<String> comparator = String::compareTo;
        Map<String, List<HashMap<String, Object>>> hashMap = new TreeMap<>(comparator);


        // 에러코드 enum 값을 꺼내가면서 비교
        for (ErrorCode errorCode : errorCodes) {
            // ERROR_로 시작하는 값만 해당
            if (errorCode.toString().split("\\.")[1].startsWith("ERROR")) {

                List<HashMap<String, Object>> errorCodeList = new ArrayList<>();
                HashMap<String, Object> errorCodeMap = new LinkedHashMap<>();

                // httpStatus 값을 기준으로 묶어준다.
                String key = errorCode.getStatus().value() + " (" + errorCode.getStatus().getReasonPhrase() + ")";

                // errorCode 의 code 값
                String codeNumber = errorCode.getCode();

                // code 값에 해당하는 message
                String message = me.getLocaleMsg(errorCode.getCode(), Locale.KOREA);

                // 조회하려는 code 값이 있으면 해당 code 값만 조회
                if(code != null) {
                    if(codeNumber.equals(code)) {
                        errorCodeMap.put(codeNumber, message);
                        errorCodeList.add(errorCodeMap);
                        hashMap.put(key, errorCodeList);
                        break;
                    }
                } else {
                    if (hashMap.containsKey(key)) {
                        errorCodeMap.put(codeNumber, message);
                        hashMap.get(key).add(errorCodeMap);

                    } else {
                        errorCodeMap.put(codeNumber, message);
                        errorCodeList.add(errorCodeMap);
                        hashMap.put(key, errorCodeList);

                    }
                }
            }
        }
        return hashMap;
    }


}
