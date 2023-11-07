package kr.co.fns.chat.api.chat.controller;

import kr.co.fns.chat.api.chat.enums.MessageType;
import kr.co.fns.chat.api.chat.listener.RedisPublisher;
import kr.co.fns.chat.api.chat.model.ChatMessage;
import kr.co.fns.chat.api.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * /topic/room/{roomId}/message
     * @param chatMessage
     */
    @MessageMapping("/room/{roomId}/message")
    public void message(ChatMessage chatMessage) {
        log.debug("rcv message => {}", chatMessage);
        if (chatMessage.getMessageType() == MessageType.ENTER) {
            chatRoomRepository.enterChatRoom(chatMessage.getRoomId());
            chatMessage.setMessage(chatMessage.getNickName()+"님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다. (Publish)
        redisPublisher.publish(chatRoomRepository.getTopic(chatMessage.getRoomId()), chatMessage);
    }
}
