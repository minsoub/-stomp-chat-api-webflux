package kr.co.fns.chat.api.chat.service;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import kr.co.fns.chat.api.chat.model.request.ChatCreateRequest;
import kr.co.fns.chat.api.chat.model.response.RoomListResponse;
import kr.co.fns.chat.api.chat.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    /**
     * ChatType에 따른 채팅방 리스트 리턴
     *
     * @param chatType
     * @param integUid
     * @return
     */
    public RoomListResponse getRooms(ChatType chatType, String integUid) {

        List<ChatRoom> chatRoomList = chatRoomRepository.findAllRoom(chatType);

        return RoomListResponse.builder()
                .chatRoomList(chatRoomList)
                .build();
    }

    /**
     * 채팅방 개설
     *
     * @param chatCreateRequest
     * @return
     */
    public ChatRoom createRoom(ChatCreateRequest chatCreateRequest) {
        ChatRoom chatRoom = ChatRoom.builder()
                        .chatType(chatCreateRequest.getChatType())
                        .name(chatCreateRequest.getName())
                        .roomId(chatCreateRequest.getRoomId())
                        .integUid(chatCreateRequest.getIntegUid())
                        .userType(chatCreateRequest.getUserType())
                        .build();
        chatRoomRepository.createChatRoom(chatRoom);
        return chatRoom;
    }

    /**
     * 채팅방 정보 조회
     *
     * @param chatType
     * @param roomId
     * @return
     */
    public ChatRoom getRoom(ChatType chatType, String roomId) {

        return chatRoomRepository.findRoomById(chatType, roomId);
    }


}
