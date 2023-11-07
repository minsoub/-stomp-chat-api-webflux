package kr.co.fns.chat.api.chat.repository;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.listener.RedisReceiveListener;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisReceiveListener redisReceiveListener;   // 구독 처리 Listener

    private static final String CHAT_ROOMS = "CHAT_ROOM::";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    private Map<String, ChannelTopic> topics;

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    /**
     * 채팅방 리스트 리턴
     *
     * @param chatType
     * @return
     */
    public List<ChatRoom> findAllRoom(ChatType chatType) {
        return opsHashChatRoom.values(CHAT_ROOMS+chatType.toString());
    }

    /**
     * 채팅방 조회
     *
     * @param chatType
     * @param id
     * @return
     */
    public ChatRoom findRoomById(ChatType chatType, String id) {
        return opsHashChatRoom.get(CHAT_ROOMS+chatType.toString(), id);
    }

    /**
     * 채팅방 생성
     * @param chatRoom
     * @return
     */
    public void createChatRoom(ChatRoom chatRoom) {
        opsHashChatRoom.put(CHAT_ROOMS+chatRoom.getChatType().toString(), chatRoom.getRoomId(), chatRoom);
    }

    /**
     * 채팅방 입장
     *
     * @param roomId
     */
    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
        }
        redisMessageListenerContainer.addMessageListener(redisReceiveListener, topic);
        topics.put(roomId, topic);
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }
}
