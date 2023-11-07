package kr.co.fns.chat.api.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import kr.co.fns.chat.api.chat.model.request.ChatCreateRequest;
import kr.co.fns.chat.api.chat.model.response.RoomListResponse;
import kr.co.fns.chat.api.chat.service.ChatRoomService;
import kr.co.fns.chat.base.dto.GeneralResponse;
import kr.co.fns.chat.core.config.resolver.Account;
import kr.co.fns.chat.core.config.resolver.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Tag(name = "Chat API", description = "Chat 관련 API 목록")
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Operation(summary = "chat, Chat List 데이터 출력", description="chat list 데이터 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = RoomListResponse.class)))
    })
    @GetMapping("/rooms")
    public GeneralResponse<RoomListResponse> roomList(
            Locale locale,
            @Parameter(hidden = true) @CurrentUser Account account,
            @Parameter(description = "채팅타입", example = "GENERAL") @RequestParam(name = "chatType") ChatType chatType,
            @Parameter(description = "통합정보 UID", example = "xx_u_2593789567d2f5d2adf5a11eda6f27538ce5e4dxx") @RequestParam(value = "integUid", required = false) String integUid
    ) {
        RoomListResponse roomListResponse = chatRoomService.getRooms(chatType, integUid);

        return new GeneralResponse<>(roomListResponse);
    }

    @Operation(summary = "chat, Chat방 개설", description="chat방 개설")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ChatRoom.class)))
    })
    @PostMapping("/room")
    public GeneralResponse<ChatRoom> roomCreate(
            Locale locale,
            @Parameter(hidden = true) @CurrentUser Account account,
            @Parameter(description = "채팅타입", example = "45") @RequestBody ChatCreateRequest chatCreateRequest
    ) {
        ChatRoom chatRoom = chatRoomService.createRoom(chatCreateRequest);
        return new GeneralResponse<>(chatRoom);
    }

    @Operation(summary = "chat, Chat방 정보 조회", description="chat방 정보조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ChatRoom.class)))
    })
    @GetMapping("/room/{chatType}/{roomId}")
    public GeneralResponse<ChatRoom> chatInfo(@PathVariable String chatType, @PathVariable String roomId) {
        ChatRoom chatRoom = chatRoomService.getRoom(Enum.valueOf(ChatType.class, chatType), roomId);

        return new GeneralResponse<>(chatRoom);
    }

}
