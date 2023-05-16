package com.example.moa.controller;

import com.example.moa.dto.chat.ChatMessageResponseDto;
import com.example.moa.service.ChatService.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomListController {

    @Autowired
    private final ChatService chatService;

    @PostMapping("/chat/create")
    public ResponseEntity<String> createChatRoom() {
//        String email = (String) httpServletRequest.getAttribute("email");

        String roomId = chatService.createChatRoom();
        return ResponseEntity.ok().body(roomId);
    }
}