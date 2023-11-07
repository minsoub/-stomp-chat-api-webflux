package kr.co.fns.chat.core.aop.exception.custom;


import kr.co.fns.chat.core.aop.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FantooException extends RuntimeException {

    private ErrorCode errorCode;

    private String addMessage;


    public FantooException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
