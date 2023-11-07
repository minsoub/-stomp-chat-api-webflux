package kr.co.fns.chat.api.chat.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Chat Room 개설 요청 데이터")
public class ChatCreateRequest {

    private String roomId;

    private String name;

    private ChatType chatType;

    private String integUid;

    private UserType userType;
}
