package kr.co.fns.chat.base.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommonResponse<T> {
    private T result;

    public CommonResponse(T data) {
        this.result = data;
    }
}
