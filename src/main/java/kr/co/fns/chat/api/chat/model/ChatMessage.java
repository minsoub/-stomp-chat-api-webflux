package kr.co.fns.chat.api.chat.model;

import kr.co.fns.chat.api.chat.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ChatMessage {
    private MessageType messageType;
    private String roomId;  // 방번호
    private String integUid;  // 메시지 보낸사람 (통합 UID)
    private String nickName;      // 메시지 보낸사람 NickName
    private String message;
    private Boolean isFile;
    private DataInfo dataInfo;
}
