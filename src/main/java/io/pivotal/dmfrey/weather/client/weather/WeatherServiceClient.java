package io.pivotal.dmfrey.weather.client.weather;

import io.pivotal.dmfrey.weather.client.ServiceClient;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

public class WeatherServiceClient extends ServiceClient {

    public WeatherServiceClient( final Jaxb2Marshaller marshaller, final HttpComponentsMessageSender httpComponentsMessageSender, final WebServiceMessageFactory messageFactory, final String baseUrl ) {
        super( marshaller, httpComponentsMessageSender, messageFactory, baseUrl );
    }

}
