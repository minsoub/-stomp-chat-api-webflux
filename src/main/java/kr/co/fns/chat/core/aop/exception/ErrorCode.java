package kr.co.fns.chat.core.aop.exception;


import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@ToString
public enum ErrorCode {

    /**
     * ********************************************************************************************************
     * Exception Start
     */

    /**
     * System Exception
     */
    HttpRequestMethodNotSupportedException(HttpStatus.NOT_FOUND, "404")
    , RuntimeException(HttpStatus.BAD_REQUEST, "400")
    , IllegalArgumentException(HttpStatus.BAD_REQUEST, "400")
    , AccessDeniedException(HttpStatus.UNAUTHORIZED, "401")
    , NoHandlerFoundException(HttpStatus.NOT_FOUND, "404")
    , NestedServletException(HttpStatus.NOT_FOUND, "404")
    , ResourceNotFoundException(HttpStatus.NOT_FOUND, "404")
    , ParseException(HttpStatus.BAD_REQUEST, "400")
    , ArrayIndexOutOfBoundsException(HttpStatus.BAD_REQUEST, "400")
    , NoSuchMessageException(HttpStatus.BAD_REQUEST, "400")
    , MailException(HttpStatus.BAD_REQUEST, "400")
    , NullPointerException(HttpStatus.BAD_REQUEST, "400")
    //, MethodArgumentNotValidException(HttpStatus.BAD_REQUEST, 400)
    , MethodArgumentTypeMismatchException(HttpStatus.NOT_FOUND, "404")
    , RequestRejectedException(HttpStatus.NOT_FOUND, "404")

    /**
     * Sql Exception
     */
    , BadSqlGrammarException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , InvalidResultSetAccessException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , DuplicateKeyException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , DataIntegrityViolationException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , DataAccessResourceFailureException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , CannotAcquireLockException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , DeadlockLoserDataAccessException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , CannotSerializeTransactionException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , SQLSyntaxErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "500") //Not error catch!?
    , DataAccessException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , SQLNonTransientException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    , SQLException(HttpStatus.INTERNAL_SERVER_ERROR, "500")
    /**
     * 외 모든 Exception
     */
    , Exception(HttpStatus.INTERNAL_SERVER_ERROR, "500")


    , HttpMessageNotReadableException(HttpStatus.BAD_REQUEST, "400")


    /**
     * Exception END
     * ********************************************************************************************************
     */







    /**
     * ********************************************************************************************************
     * Custom Exception Start
     * - 하기 경로의 errorCode와 매칭하여 사용
     * - resources/message/Resource Bundle 'messages'/messages_*.properties
     */
    , ERROR_401(HttpStatus.UNAUTHORIZED,"401")
    , ERROR_403(HttpStatus.FORBIDDEN,"403")

    /**
     * 1000 ~ 1999, Error code 는 회원 관련 메세지만 정의한다.
     */
    , ERROR_FE1000(HttpStatus.BAD_REQUEST,"FE1000")
    , ERROR_FE1001(HttpStatus.BAD_REQUEST,"FE1001")
    , ERROR_FE1002(HttpStatus.BAD_REQUEST,"FE1002")
    , ERROR_FE1003(HttpStatus.BAD_REQUEST,"FE1003")
    , ERROR_FE1004(HttpStatus.BAD_REQUEST,"FE1004")
    , ERROR_FE1005(HttpStatus.BAD_REQUEST,"FE1005")
    , ERROR_FE1006(HttpStatus.BAD_REQUEST,"FE1006")
    , ERROR_FE1007(HttpStatus.BAD_REQUEST,"FE1007")
    , ERROR_FE1008(HttpStatus.BAD_REQUEST,"FE1008")
    , ERROR_FE1009(HttpStatus.BAD_REQUEST,"FE1009")
    , ERROR_FE1010(HttpStatus.BAD_REQUEST,"FE1010")
    , ERROR_FE1011(HttpStatus.BAD_REQUEST,"FE1011")
    , ERROR_FE1012(HttpStatus.BAD_REQUEST,"FE1012")
    , ERROR_FE1013(HttpStatus.BAD_REQUEST,"FE1013")
    , ERROR_FE1014(HttpStatus.BAD_REQUEST,"FE1014")
    , ERROR_FE1015(HttpStatus.BAD_REQUEST,"FE1015")
    , ERROR_FE1016(HttpStatus.BAD_REQUEST,"FE1016")
    , ERROR_FE1017(HttpStatus.BAD_REQUEST,"FE1017")
    , ERROR_FE1018(HttpStatus.BAD_REQUEST,"FE1018")
    , ERROR_FE1019(HttpStatus.BAD_REQUEST,"FE1019")
    , ERROR_FE1020(HttpStatus.BAD_REQUEST,"FE1020")
    , ERROR_FE1021(HttpStatus.BAD_REQUEST,"FE1021")
    , ERROR_FE1022(HttpStatus.BAD_REQUEST,"FE1022")
    , ERROR_FE1023(HttpStatus.BAD_REQUEST,"FE1023")
    , ERROR_FE1024(HttpStatus.BAD_REQUEST,"FE1024")
    , ERROR_FE1025(HttpStatus.BAD_REQUEST,"FE1025")
    , ERROR_FE1026(HttpStatus.BAD_REQUEST,"FE1026")
    , ERROR_FE1027(HttpStatus.BAD_REQUEST,"FE1027")
    , ERROR_FE1028(HttpStatus.BAD_REQUEST,"FE1028")
    , ERROR_FE1029(HttpStatus.BAD_REQUEST,"FE1029")
    , ERROR_FE1030(HttpStatus.BAD_REQUEST,"FE1030")
    , ERROR_FE1031(HttpStatus.BAD_REQUEST,"FE1031")
    , ERROR_FE1032(HttpStatus.BAD_REQUEST,"FE1032")
    , ERROR_FE1033(HttpStatus.BAD_REQUEST,"FE1033")
    , ERROR_FE1034(HttpStatus.BAD_REQUEST,"FE1034")
    , ERROR_FE1035(HttpStatus.BAD_REQUEST,"FE1035")
    , ERROR_FE1036(HttpStatus.BAD_REQUEST,"FE1036")
    , ERROR_FE1037(HttpStatus.BAD_REQUEST,"FE1037")
    , ERROR_FE1038(HttpStatus.BAD_REQUEST,"FE1038")


//    , ERROR_FE1009(HttpStatus.BAD_REQUEST,1009)
//    , ERROR_FE1010(HttpStatus.BAD_REQUEST,1010)

//    , ERROR_1100(HttpStatus.BAD_REQUEST,1100)
//    , ERROR_1200(HttpStatus.BAD_REQUEST,1200)
//    , ERROR_1300(HttpStatus.BAD_REQUEST,1300)


    /**
     * 2000 ~ 2999, Error code 는 커뮤니티 관련 메세지만 정의한다.
     */
    , ERROR_FE2000(HttpStatus.BAD_REQUEST,"FE2000")
    , ERROR_FE2001(HttpStatus.BAD_REQUEST,"FE2001")
    , ERROR_FE2002(HttpStatus.BAD_REQUEST,"FE2002")
    , ERROR_FE2003(HttpStatus.BAD_REQUEST,"FE2003")
    , ERROR_FE2004(HttpStatus.BAD_REQUEST,"FE2004")
    , ERROR_FE2005(HttpStatus.BAD_REQUEST,"FE2005")
    , ERROR_FE2006(HttpStatus.BAD_REQUEST,"FE2006")
//    , ERROR_FE2007(HttpStatus.BAD_REQUEST,"FE2007")
    , ERROR_FE2008(HttpStatus.BAD_REQUEST,"FE2008")
//    , ERROR_FE2009(HttpStatus.BAD_REQUEST,"FE2009")
    , ERROR_FE2010(HttpStatus.BAD_REQUEST,"FE2010")
    , ERROR_FE2011(HttpStatus.BAD_REQUEST,"FE2011")
    , ERROR_FE2012(HttpStatus.BAD_REQUEST,"FE2012")
    , ERROR_FE2013(HttpStatus.BAD_REQUEST,"FE2013")
    , ERROR_FE2014(HttpStatus.BAD_REQUEST,"FE2014")
    , ERROR_FE2015(HttpStatus.BAD_REQUEST,"FE2015")
    , ERROR_FE2016(HttpStatus.BAD_REQUEST,"FE2016")
    , ERROR_FE2017(HttpStatus.BAD_REQUEST,"FE2017")
    , ERROR_FE2018(HttpStatus.BAD_REQUEST,"FE2018")
    , ERROR_FE2019(HttpStatus.BAD_REQUEST,"FE2019")
    , ERROR_FE2020(HttpStatus.BAD_REQUEST,"FE2020")
//    , ERROR_FE2021(HttpStatus.BAD_REQUEST,"FE2021")
    , ERROR_FE2022(HttpStatus.BAD_REQUEST,"FE2022")
    , ERROR_FE2023(HttpStatus.BAD_REQUEST,"FE2023")
    , ERROR_FE2024(HttpStatus.BAD_REQUEST,"FE2024")
    , ERROR_FE2025(HttpStatus.BAD_REQUEST,"FE2025")
    , ERROR_FE2026(HttpStatus.BAD_REQUEST,"FE2026")
    , ERROR_FE2027(HttpStatus.BAD_REQUEST,"FE2027")

    // mconfig 관련 에러FE
    , ERROR_FE2824(HttpStatus.BAD_REQUEST,"FE2824")
    , ERROR_FE2825(HttpStatus.BAD_REQUEST,"FE2825")
    , ERROR_FE2827(HttpStatus.BAD_REQUEST,"FE2827")
    , ERROR_FE2828(HttpStatus.BAD_REQUEST,"FE2828")
    , ERROR_FE2829(HttpStatus.BAD_REQUEST,"FE2829")
    , ERROR_FE2830(HttpStatus.BAD_REQUEST,"FE2830")
    , ERROR_FE2831(HttpStatus.BAD_REQUEST,"FE2831")
    , ERROR_FE2832(HttpStatus.BAD_REQUEST,"FE2832")
    , ERROR_FE2833(HttpStatus.BAD_REQUEST,"FE2833")
    , ERROR_FE2834(HttpStatus.BAD_REQUEST,"FE2834")
    , ERROR_FE2835(HttpStatus.BAD_REQUEST,"FE2835")
    , ERROR_FE2836(HttpStatus.BAD_REQUEST,"FE2836")
    , ERROR_FE2837(HttpStatus.BAD_REQUEST,"FE2837")
    , ERROR_FE2838(HttpStatus.BAD_REQUEST,"FE2838")
    , ERROR_FE2839(HttpStatus.BAD_REQUEST,"FE2839")
    , ERROR_FE2840(HttpStatus.BAD_REQUEST,"FE2840")






    /**
     * 3000 ~ 3999, Error code 는 클럽 관련 메세지만 정의한다.
     */
    , ERROR_FE3000(HttpStatus.NOT_ACCEPTABLE,"FE3000")
    , ERROR_FE3001(HttpStatus.NOT_ACCEPTABLE,"FE3001")
    , ERROR_FE3002(HttpStatus.NOT_ACCEPTABLE,"FE3002")
    , ERROR_FE3003(HttpStatus.NOT_ACCEPTABLE,"FE3003")
    , ERROR_FE3004(HttpStatus.NOT_ACCEPTABLE,"FE3004")
    , ERROR_FE3005(HttpStatus.NOT_ACCEPTABLE,"FE3005")
    , ERROR_FE3006(HttpStatus.NOT_ACCEPTABLE,"FE3006")
    , ERROR_FE3007(HttpStatus.NOT_ACCEPTABLE,"FE3007")
    , ERROR_FE3008(HttpStatus.NOT_ACCEPTABLE,"FE3008")
    , ERROR_FE3009(HttpStatus.NOT_ACCEPTABLE,"FE3009")
    , ERROR_FE3010(HttpStatus.NOT_ACCEPTABLE,"FE3010")
    , ERROR_FE3011(HttpStatus.NOT_ACCEPTABLE,"FE3011")
    , ERROR_FE3012(HttpStatus.NOT_ACCEPTABLE,"FE3012")
    , ERROR_FE3013(HttpStatus.NOT_ACCEPTABLE,"FE3013")
    , ERROR_FE3014(HttpStatus.NOT_ACCEPTABLE,"FE3014")
    , ERROR_FE3015(HttpStatus.NOT_ACCEPTABLE,"FE3015")
    , ERROR_FE3016(HttpStatus.BAD_REQUEST,"FE3016")
    , ERROR_FE3017(HttpStatus.NOT_ACCEPTABLE,"FE3017")
    , ERROR_FE3018(HttpStatus.NOT_ACCEPTABLE,"FE3018")
    , ERROR_FE3019(HttpStatus.NOT_ACCEPTABLE,"FE3019")
    , ERROR_FE3020(HttpStatus.BAD_REQUEST,"FE3020")
    , ERROR_FE3021(HttpStatus.BAD_REQUEST,"FE3021")
    , ERROR_FE3022(HttpStatus.BAD_REQUEST,"FE3022")
    , ERROR_FE3023(HttpStatus.BAD_REQUEST,"FE3023")
    , ERROR_FE3024(HttpStatus.BAD_REQUEST,"FE3024")
    , ERROR_FE3025(HttpStatus.BAD_REQUEST,"FE3025")
    , ERROR_FE3026(HttpStatus.BAD_REQUEST,"FE3026")
    , ERROR_FE3027(HttpStatus.BAD_REQUEST,"FE3027")
    , ERROR_FE3028(HttpStatus.BAD_REQUEST,"FE3028")
    , ERROR_FE3029(HttpStatus.BAD_REQUEST,"FE3029")
    , ERROR_FE3030(HttpStatus.BAD_REQUEST,"FE3030")
    , ERROR_FE3031(HttpStatus.BAD_REQUEST,"FE3031")
    , ERROR_FE3032(HttpStatus.BAD_REQUEST,"FE3032")
    , ERROR_FE3033(HttpStatus.BAD_REQUEST,"FE3033")
    , ERROR_FE3034(HttpStatus.BAD_REQUEST,"FE3034")
    , ERROR_FE3035(HttpStatus.BAD_REQUEST,"FE3035")
    , ERROR_FE3036(HttpStatus.BAD_REQUEST,"FE3036")
    , ERROR_FE3037(HttpStatus.BAD_REQUEST,"FE3037")
    , ERROR_FE3038(HttpStatus.BAD_REQUEST,"FE3038")
    , ERROR_FE3039(HttpStatus.BAD_REQUEST,"FE3039")
    , ERROR_FE3040(HttpStatus.BAD_REQUEST,"FE3040")
    , ERROR_FE3041(HttpStatus.BAD_REQUEST,"FE3041")
    , ERROR_FE3042(HttpStatus.BAD_REQUEST,"FE3042")
    , ERROR_FE3043(HttpStatus.BAD_REQUEST,"FE3043")
    , ERROR_FE3044(HttpStatus.BAD_REQUEST,"FE3044")
    , ERROR_FE3045(HttpStatus.BAD_REQUEST,"FE3045")
    , ERROR_FE3046(HttpStatus.BAD_REQUEST,"FE3046")
    , ERROR_FE3047(HttpStatus.BAD_REQUEST,"FE3047")
    , ERROR_FE3048(HttpStatus.BAD_REQUEST,"FE3048")
    , ERROR_FE3049(HttpStatus.BAD_REQUEST,"FE3049")


    , ERROR_FE3050(HttpStatus.NOT_ACCEPTABLE,"FE3050")
    , ERROR_FE3051(HttpStatus.NOT_ACCEPTABLE,"FE3051")
    , ERROR_FE3052(HttpStatus.NOT_ACCEPTABLE,"FE3052")
    , ERROR_FE3053(HttpStatus.NOT_ACCEPTABLE,"FE3053")
    , ERROR_FE3054(HttpStatus.NOT_ACCEPTABLE,"FE3054")
    , ERROR_FE3055(HttpStatus.BAD_REQUEST,"FE3055")

    , ERROR_FE3100(HttpStatus.NOT_ACCEPTABLE,"FE3100")
    , ERROR_FE3101(HttpStatus.NOT_ACCEPTABLE,"FE3101")
    , ERROR_FE3102(HttpStatus.NOT_ACCEPTABLE,"FE3102")
    , ERROR_FE3103(HttpStatus.NOT_ACCEPTABLE,"FE3103")

    , ERROR_FE3104(HttpStatus.NOT_ACCEPTABLE,"FE3104")
    , ERROR_FE3105(HttpStatus.NOT_ACCEPTABLE,"FE3105")
    , ERROR_FE3106(HttpStatus.NOT_ACCEPTABLE,"FE3106")
    , ERROR_FE3107(HttpStatus.NOT_ACCEPTABLE,"FE3107")
    , ERROR_FE3108(HttpStatus.NOT_ACCEPTABLE,"FE3108")
    , ERROR_FE3109(HttpStatus.NOT_ACCEPTABLE,"FE3109")
    , ERROR_FE3110(HttpStatus.NOT_ACCEPTABLE,"FE3110")
    , ERROR_FE3111(HttpStatus.NOT_ACCEPTABLE,"FE3111")
    , ERROR_FE3112(HttpStatus.NOT_ACCEPTABLE,"FE3112")

    , ERROR_FE3200(HttpStatus.NOT_ACCEPTABLE,"FE3200")
    , ERROR_FE3201(HttpStatus.NOT_ACCEPTABLE,"FE3201")
    , ERROR_FE3202(HttpStatus.NOT_ACCEPTABLE,"FE3202")


    , ERROR_FE3500(HttpStatus.BAD_REQUEST,"FE3500")
    , ERROR_FE3501(HttpStatus.BAD_REQUEST,"FE3501")

    , ERROR_FE3601(HttpStatus.BAD_REQUEST,"FE3601")
    , ERROR_FE3602(HttpStatus.BAD_REQUEST,"FE3602")
    , ERROR_FE3603(HttpStatus.BAD_REQUEST,"FE3603")
    , ERROR_FE3604(HttpStatus.BAD_REQUEST,"FE3604")
    , ERROR_FE3605(HttpStatus.BAD_REQUEST,"FE3605")
    , ERROR_FE3606(HttpStatus.BAD_REQUEST,"FE3606")
    , ERROR_FE3607(HttpStatus.BAD_REQUEST,"FE3607")
    , ERROR_FE3608(HttpStatus.BAD_REQUEST,"FE3608")
    , ERROR_FE3609(HttpStatus.BAD_REQUEST,"FE3609")
    , ERROR_FE3610(HttpStatus.BAD_REQUEST,"FE3610")

    , ERROR_FE3700(HttpStatus.BAD_REQUEST,"FE3700")
    , ERROR_FE3701(HttpStatus.BAD_REQUEST,"FE3701")
    , ERROR_FE3702(HttpStatus.BAD_REQUEST,"FE3702")
    , ERROR_FE3703(HttpStatus.BAD_REQUEST,"FE3703")
    , ERROR_FE3704(HttpStatus.BAD_REQUEST,"FE3704")
    , ERROR_FE3705(HttpStatus.BAD_REQUEST,"FE3705")
    //, ERROR_FE3706(HttpStatus.BAD_REQUEST,"FE3706")

//    , ERROR_5000(HttpStatus.INTERNAL_SERVER_ERROR,"5000")
//    , ERROR_5001(HttpStatus.INTERNAL_SERVER_ERROR,"5001")
//    , ERROR_5002(HttpStatus.INTERNAL_SERVER_ERROR,"5002");

    /**
     * 4000 ~ 4999, Error code 는 Minute 관련 메세지만 정의한다.
     */
    , ERROR_FE4006(HttpStatus.BAD_REQUEST, "FE4006")  // Like type error
    , ERROR_FE4007(HttpStatus.BAD_REQUEST, "FE4007")  // 이미 즐겨찾기 한 영상
    , ERROR_FE4008(HttpStatus.BAD_REQUEST, "FE4008")  // 삭제한 즐겨찾기 영상
    , ERROR_FE4011(HttpStatus.BAD_REQUEST,"FE4011")   // 하루 5회 쓰기 초과
    , ERROR_FE4012(HttpStatus.BAD_REQUEST,"FE4012")   // 중복 제목 사용 불가
    , ERROR_FE4013(HttpStatus.BAD_REQUEST,"FE4013")   // 차단중
    , ERROR_FE4014(HttpStatus.BAD_REQUEST,"FE4014")   // 해시태그 등록 에러
    , ERROR_FE4015(HttpStatus.BAD_REQUEST, "FE4015")  // 미닛 음원 맵핑 등록 에러
    , ERROR_FE4016(HttpStatus.BAD_REQUEST, "FE4016")  // 해시태그 개수 에러
    /**
     * 5000 ~ 5999, Error code 는 Fanit 관련 메세지만 정의한다.
     */

    , ERROR_FE5001(HttpStatus.BAD_REQUEST,"FE5001") // 진행중인 투표가 아닐때
    , ERROR_FE5002(HttpStatus.BAD_REQUEST,"FE5002") // 투표가 가능할때
    , ERROR_FE5003(HttpStatus.BAD_REQUEST,"FE5003") // 투표가 기간이 종료가 되었을때
    , ERROR_FE5004(HttpStatus.BAD_REQUEST,"FE5004") // 가지고 있는 포인트 이상으로 팬잇사용 시도시
    , ERROR_FE5005(HttpStatus.BAD_REQUEST,"FE5005") // 일일 최대 팬잇 사용수를 넘으려 할때
    , ERROR_FE5006(HttpStatus.BAD_REQUEST,"FE5006") // tocken과 integuid체크
    , ERROR_FE5007(HttpStatus.BAD_REQUEST,"FE5007") // 아모페 하루 투표 횟수 초과하려 할때
    , ERROR_FE5008(HttpStatus.BAD_REQUEST,"FE5008") // 로그인 리워드를 금일 받은 상황일때
    , ERROR_FE5009(HttpStatus.BAD_REQUEST,"FE5009") // 아모페 마지막 적립하고 2시간이 안지났을때
    , ERROR_FE5010(HttpStatus.BAD_REQUEST,"FE5010") // 아모페 일일 적립 횟수가 12번을 넘었을때
    , ERROR_FE5011(HttpStatus.BAD_REQUEST,"FE5011") // 유효한 회원이 아닐때
    , ERROR_FE5012(HttpStatus.BAD_REQUEST,"FE5012") // 유효한 투표대상이 아닐때
    , ERROR_FE5013(HttpStatus.BAD_REQUEST,"FE5013") // 투표에 포인트를 음수로 넘길경우에대한 처리
    , ERROR_FE5014(HttpStatus.BAD_REQUEST,"FE5014") // 실패 재시도 바랍니다.
    , ERROR_FE5015(HttpStatus.BAD_REQUEST,"FE5015") // 해당건에대한 적립횟수 초과입니다.
    
    ;
    /**
     * Custom Exception End
     * ********************************************************************************************************
     */


    private final HttpStatus status;
    private String code;

    ErrorCode(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ErrorCode(HttpStatus status) {
        this.status = status;
    }

    public static ErrorCode findException(String exceptionName) {
        return Arrays.stream(ErrorCode.values())
                .filter(s -> s.name().equals(exceptionName))
                .findAny()
                .orElse(ErrorCode.Exception);
    }



}
