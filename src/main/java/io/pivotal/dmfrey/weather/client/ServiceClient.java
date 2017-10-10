package io.pivotal.dmfrey.weather.client;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

public abstract class ServiceClient {

    private final Jaxb2Marshaller marshaller;
    private final HttpComponentsMessageSender httpComponentsMessageSender;
    private final WebServiceMessageFactory messageFactory;
    private final String baseUrl;
    private final WebServiceTemplate webServiceTemplate;

    protected ServiceClient( final Jaxb2Marshaller marshaller, final HttpComponentsMessageSender httpComponentsMessageSender, final WebServiceMessageFactory messageFactory, final String baseUrl ) {

        this.marshaller = marshaller;
        this.httpComponentsMessageSender = httpComponentsMessageSender;
        this.messageFactory = messageFactory;
        this.baseUrl = baseUrl;

        this.webServiceTemplate = webServiceTemplate();
    }

    public Response call( final Request<?> request ) {

        return new Response( webServiceTemplate.marshalSendAndReceive( request.getPayload() ) );
    }

    private WebServiceTemplate webServiceTemplate() {

        WebServiceTemplate wst = new WebServiceTemplate();
        wst.setMessageSender( httpComponentsMessageSender );
        wst.setMessageFactory( messageFactory );
        wst.setMarshaller( marshaller );
        wst.setUnmarshaller( marshaller );
        wst.setDefaultUri( baseUrl );
        wst.afterPropertiesSet();

        return wst;
    }

}
