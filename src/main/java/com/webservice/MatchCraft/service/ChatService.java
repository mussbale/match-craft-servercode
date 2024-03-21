package com.webservice.MatchCraft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservice.MatchCraft.model.Message;
import com.webservice.MatchCraft.repo.MessageRepository;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getChatHistory(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }

}
