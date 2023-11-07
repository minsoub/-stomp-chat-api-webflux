package kr.co.fns.chat.api.chat.listener;

import kr.co.fns.chat.api.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 메시지를 Redis에 저장한다.
     *
     * @param topic
     * @param chatMessage
     */
    public void publish(ChannelTopic topic, ChatMessage chatMessage) {
        redisTemplate.convertAndSend(topic.getTopic(), chatMessage);
    }
}
