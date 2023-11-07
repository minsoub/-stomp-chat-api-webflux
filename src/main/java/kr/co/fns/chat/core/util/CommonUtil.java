package kr.co.fns.chat.core.util;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CommonUtil {

    /**
     * Client IP를 리턴한다.
     * java.net.preferIPv4Stack=true => 로컬인 경우 테스트 할 때 옵션을 추가해야 함.
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request){
        String clientIp = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNullOrEmpty(clientIp)|| "unknown".equalsIgnoreCase(clientIp)) {
            //Proxy 서버인 경우
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isNullOrEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isNullOrEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.isNullOrEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isNullOrEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("X-Real-IP");
        }
        if (StringUtil.isNullOrEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    public static String getRequestBody(HttpServletRequest request) {
        String reqBody = (String) request.getAttribute("requestBody");

        if (StringUtil.isNullOrEmpty(reqBody)) {
            log.debug("reqBody => {}", reqBody);
            if (!StringUtil.isNullOrEmpty(request.getQueryString())) {
                log.debug("getQueryString => {}", request.getQueryString());
                return URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
            } else {
                return "";
            }
        } else {
            return URLDecoder.decode(reqBody, StandardCharsets.UTF_8);
        }
//        String bodyJson = "";
//
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader br = null;
//        //한줄씩 담을 변수
//        String line = "";
//
//        try {
//            InputStream inputStream = request.getInputStream();
//            if (inputStream != null) {
//                br = new BufferedReader(new InputStreamReader(inputStream));
//                //더 읽을 라인이 없을때까지 계속
//                while ((line = br.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//
//                bodyJson = URLDecoder.decode(stringBuilder.toString(), StandardCharsets.UTF_8);
//                JSONParser jsonParser = new JSONParser();
//                JSONObject jsonObject = null;
//                try {
//                    log.info(bodyJson);
//                    //json 형태로 변환하기
//                    jsonObject = (JSONObject) jsonParser.parse(bodyJson);
//                } catch (ParseException e) {
//                    log.debug(e.getMessage());
//                    return URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
//                    //e.printStackTrace();
//                }
//                log.info(jsonObject.toJSONString());
//                return jsonObject.toJSONString();
//            }else {
//                log.info("Data 없음");
//                return URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return line;
    }
}
