package com.webservice.MatchCraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.webservice.MatchCraft.model.Message;
import com.webservice.MatchCraft.service.MessageService;
import com.webservice.MatchCraft.service.UserService;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    // Endpoint for sending chat messages
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message chatMessage) {
        Message savedMessage = messageService.saveMessage(chatMessage); // Save the message to the database
        messagingTemplate.convertAndSend("/topic/publicChat", savedMessage); // Broadcast the message to the public chat topic
    }

    // Endpoint for handling new user connection and broadcasting their online status
    @MessageMapping("/chat.newUser")
    public void newUser(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Assuming the sender ID is correctly set in the chatMessage entity
        Long userId = chatMessage.getSender().getId(); // Correctly use Long for userId
        userService.setUserOnlineStatus(userId, true); // Update user status to online
        // Assuming OnlineStatusMessage constructor or method allows Long for userId
        messagingTemplate.convertAndSend("/topic/onlineUsers", new OnlineStatusMessage(userId, true));
    }

    // Handle user disconnect
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        Long userId = (Long) headerAccessor.getSessionAttributes().get("userId"); // Ensure casting to Long
        if (userId != null) {
            userService.setUserOnlineStatus(userId, false); // Update user status to offline
            // Assuming OnlineStatusMessage constructor or method allows Long for userId
            messagingTemplate.convertAndSend("/topic/onlineUsers", new OnlineStatusMessage(userId, false));
        }
    }
}
