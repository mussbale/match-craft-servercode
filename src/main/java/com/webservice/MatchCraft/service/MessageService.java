package com.webservice.MatchCraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservice.MatchCraft.model.Message;
import com.webservice.MatchCraft.repo.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepo;
	
	
	public Message saveMessage(Message message) {
        return messageRepo.save(message);
    }
	
}
