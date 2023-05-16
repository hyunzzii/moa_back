package com.example.moa.service.ChatService;

import com.example.moa.domain.ChatMessage;
import com.example.moa.dto.chat.ChatMessageRequestDto;
import com.example.moa.dto.chat.ChatMessageResponseDto;

import java.util.List;

public interface ChatService {
    ChatMessage saveChatMessage(ChatMessageRequestDto chatMessageDto);
    List<ChatMessageResponseDto> getChatMessagesByRoomId(String roomId);
    String createChatRoom();
}
