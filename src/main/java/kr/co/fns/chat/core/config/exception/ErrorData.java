package kr.co.fns.chat.core.config.exception;

import kr.co.fns.chat.core.config.model.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorData {
  private final String code;
  private final String message;

  public ErrorData(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}