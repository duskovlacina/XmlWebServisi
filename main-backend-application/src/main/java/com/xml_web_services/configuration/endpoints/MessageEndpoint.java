package com.xml_web_services.configuration.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml_web_services.domain.GetAgentMessages;
import com.xml_web_services.domain.GetAgentMessagesResponse;
import com.xml_web_services.domain.Message;
import com.xml_web_services.repositories.AgentRepository;
import com.xml_web_services.repositories.MessageRepository;


@Endpoint
public class MessageEndpoint {

	private static final String NAMESPACE = "http://com/xml_web_services/domain";

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private AgentRepository agentRepository;
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAgentMessages")
	@ResponsePayload
	public GetAgentMessagesResponse getAccomodationService(@RequestPayload GetAgentMessages request) {
		GetAgentMessagesResponse response = new GetAgentMessagesResponse();
		List<Message> messages = this.messageRepository.findByAgentAndReaded(
				this.agentRepository.findByUsername(request.getAgentUsername()), false);
		
		for (Message m : messages) {
			m.setReaded(true);
			this.messageRepository.save(m);
		}
		response.getMessages().addAll(messages);
		return response;
	}
}
