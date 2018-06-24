package com.xml_web_services.configuration.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml_web_services.domain.GetAgentMessages;
import com.xml_web_services.domain.GetAgentMessagesResponse;

public class MessageSoapClient extends WebServiceGatewaySupport {

	public GetAgentMessagesResponse getMessages(String username) {
		GetAgentMessages request = new GetAgentMessages();
		request.setAgentUsername(username);
		GetAgentMessagesResponse response = (GetAgentMessagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/GetAgentMessagesResponse"));
		
		return response;
	}
}
