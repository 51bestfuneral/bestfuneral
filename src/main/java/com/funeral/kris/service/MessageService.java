package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Message;
import com.funeral.kris.model.MessageUser;

public interface MessageService {
	
	public void addResource(Message message);
	public void updateResource(Message message);
	public Message getResource(int id);
	public void deleteResource(int id);
	public List<MessageUser> getResources();
	public List<Message> getResources(HttpServletRequest request);

}
