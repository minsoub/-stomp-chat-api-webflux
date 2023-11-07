package kr.co.fns.chat.api.chat.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Chat Room List 데이터 응답")
public class RoomListResponse {

    @Schema(description = "Chat Room List", example = "ChatRoom 객체 (Schema 참조)", implementation = ChatRoom.class)
    private List<ChatRoom> chatRoomList;
}
