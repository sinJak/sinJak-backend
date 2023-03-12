package net.sinzak.server.chatroom.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sinzak.server.chatroom.domain.ChatMessage;
import net.sinzak.server.chatroom.domain.ChatRoom;
import net.sinzak.server.chatroom.dto.request.PostDto;
import net.sinzak.server.chatroom.dto.respond.GetChatMessageDto;
import net.sinzak.server.chatroom.repository.ChatRoomRepository;
import net.sinzak.server.chatroom.service.ChatRoomQueryService;
import net.sinzak.server.common.PropertyUtil;
import net.sinzak.server.common.error.ChatRoomNotFoundException;
import net.sinzak.server.common.error.PostNotFoundException;
import net.sinzak.server.user.domain.User;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


@Api(tags = "채팅-조회")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value ="/chat")
public class ChatRoomQueryController {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomQueryService chatRoomQueryService;
    private static final int MESSAGE_PAGE_SIZE =30;


    @PostMapping("/rooms")
    @ApiOperation(value ="채팅방 목록 조회")
    public JSONObject getChatRooms(@AuthenticationPrincipal User user){
        PropertyUtil.checkHeader(user);
        return chatRoomQueryService.getChatRooms(user);
    }

    @PostMapping(value = "/rooms/{uuid}")
    @ApiOperation(value ="채팅방 정보 조회 " ,notes = "postUserId = 게시글 올린 유저아이디, opponentUserId = 채팅 하는 상대방 아이디 ,삭제된 게시글일시 productId : null ->삭제된 게시글")
    public JSONObject getChatRoom(@PathVariable("uuid") String roomUuid, @AuthenticationPrincipal User user){
        PropertyUtil.checkHeader(user);
        return chatRoomQueryService.getChatRoom(roomUuid,user);
    }

    @PostMapping(value ="/rooms/post")
    @ApiOperation(value = "상품에 딸려있는 채팅방 불러오기")
    public JSONObject getChatRoomByProduct(@AuthenticationPrincipal User user, @RequestBody PostDto postDto){
        return chatRoomQueryService.getChatRoomsByPost(user,postDto);
    }

    @GetMapping(value = "/rooms/{uuid}/message")
    public Page<GetChatMessageDto> getChatRoomMessage(
            @PathVariable("uuid") String roomUuid, @RequestParam(value = "page",required = false,defaultValue = "0") int page){
        PageRequest pageRequest = PageRequest.of(page,MESSAGE_PAGE_SIZE,Sort.by("messageId").descending());
        return chatRoomQueryService.getChatRoomMessage(roomUuid,pageRequest);
    }

//    @ApiOperation(value ="채팅방 메시지 조회")
//    @GetMapping(value ="/rooms/{uuid}/message")
//    public Page<ChatMessage> getChatMessage(){
//
//    }


}
