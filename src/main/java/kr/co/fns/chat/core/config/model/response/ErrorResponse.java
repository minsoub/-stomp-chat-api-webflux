package kr.co.fns.chat.core.config.model.response;

import kr.co.fns.chat.core.config.exception.ErrorData;
import kr.co.fns.chat.core.config.model.enums.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

  private final ReturnCode result;
  private final ErrorData error;

  public ErrorResponse(ErrorData data) {
    this.result = ReturnCode.FAIL;
    this.error = data;
  }
}