package kr.co.fns.chat.api.chat.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.fns.chat.api.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisReceiveListener implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * Redis에서 메시지가 발생(publish)되면 대기하고 있던 onMessage가 해당 메시지를 받아 처리한다.
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            log.debug("message => {}", message);
            // redis에서 발행된 데이터를 받아 deserialize
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

            // ChatMessage 맵핑
            ChatMessage chatMessage = objectMapper.readValue(publishMessage, ChatMessage.class);

            // Websocket 구독자에게 채팅 메시지 전송
            simpMessageSendingOperations.convertAndSend("/topic/chat/room/"+chatMessage.getRoomId(), chatMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
    }
}
