package kr.co.fns.chat.core.config.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  INVALID_DATA("F999", "data is null or empty"),
  INVALID_PASSWORD("F505","Invalid password!"),

  INVALID_FILE("F002","file is invalid"),
  FAIL_SAVE_FILE("F003","file save fail"),
  INVALID_TOKEN("F004","Invalid token"),
  NOT_EXIST_ACCOUNT("F404","NOT_EXIST_ACCOUNT"),
  EXIST_ACCOUNT("F405", "EXIST_ACCOUNT"),
  FAIL_ACCOUNT_REGISTER("F005","FAIL_ACCOUNT_REGISTER"),
  FAIL_PASSWORD_UPDATE("F006", "Current Password is not equals"),
  NOT_EXIST_ROLE("R404","NOT_EXIST_ROLE"),
  INVALID_ROLE("R500","INVALID_ROLE"),
  INVALID_CATEGORY("R501", "Invalid category!"),

  INVALID_MAX_ROLE("R501", "INVALID_MAX_ROLE"),

  NOT_EXIST_SITE("M404","NOT_EXIST_SITE"),
  NOT_EXIST_MENU("M401","NOT_EXIST_MENU"),
  NOT_EXIST_PROGRAM("P401","NOT_EXIST_PROGRAM"),
  FAIL_SAVE_MENU("M402","FAIL_SAVE_MENU"),
  FAIL_SEND_MAIL("M411","FAIL_SEND_MAIL"),

  NOT_EXIST_DATA("D001", "NOT_EXIST_DATA"),

  UNKNOWN_ERROR("999", "알 수 없는 에러가 발생하였습니다. 운영자에게 문의 주시기 바랍니다!!!"),
  INVALID_HEADER_USER_IP("901", "Header 정보가 유효하지 않습니다!!(Not found user_ip)"),
  INVALID_HEADER_SITE_ID("902","Header 정보가 유효하지 않습니다!!(Not found site_id)"),
  INVALID_HEADER_TOKEN("903", "Token 정보가 잘 못되었습니다!!!"),
  EXPIRED_TOKEN("909", "Token expired"),
  SERVER_RESPONSE_ERROR("904", "API 서버에서 에러가 발생하였습니다!!!"),
  GATEWAY_SERVER_ERROR("905", "Gateway Server Error"),
  USER_ALREADY_LOGIN("906", "User is already login"),
  AUTH_SERVER_RESPONSE_ERROR("905", "Auth server error."),
  AUTH_SERVER_AUTHORIZATION_FAIL("906", "Authorization fail."),
  AUTHORIZATION_FAIL("907","Fail Authorization"),
  NOT_ALLOWED_FILE_EXT("F015", "허용되지 않은 파일 확장자입니다. 파일을 확인해 주세요."),
  NOT_ALLOWED_FILE_SIZE("F016", "첨부가능한 파일 크기를 초과했습니다."),
  STO_MODIFY_ERROR("F017", "발행중인 토큰증권은 수정이 불가능합니다!!!"),
  STO_EXISTS_ERROR("F018", "이미 맵핑된 작품입니다!!!"),
  TOKEN_CREATE_ERROR("F018", "토큰 발행할 수 없는 상태입니다!!!"),
  TOKEN_NOT_EXIST("F019", "요청이 유효하지 않습니다!!!"),
  TOKEN_AUTH_FAIL("F020", "자격증명 정보가 유효하지 않습니다!!!"),
  TOKEN_CREATE_FAIL("F021", "요청이 서버의 상태와 충돌했습니다!!!")
  ;

  private final String code;
  private final String message;
}
