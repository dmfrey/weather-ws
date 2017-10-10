package io.pivotal.dmfrey.weather.config;

import io.pivotal.dmfrey.weather.client.weather.WeatherServiceClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class ClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan(
                "net.webservicex"
        );

        return marshaller;
    }

    @Bean
    public SoapFaultMappingExceptionResolver soapFaultMappingExceptionResolver() {

        return new SoapFaultMappingExceptionResolver();
    }

    @Bean
    public WeatherServiceClient productionServiceClient( final Jaxb2Marshaller marshaller, final HttpComponentsMessageSender httpComponentsMessageSender, final WebServiceMessageFactory messageFactory, @Value( "${base.url}" ) final String baseUrl ) {

        return new WeatherServiceClient( marshaller, httpComponentsMessageSender, messageFactory, baseUrl );
    }

    @Bean
    public HttpComponentsMessageSender httpComponentsMessageSender( final HttpClient httpClient ) throws Exception {

        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setHttpClient( httpClient );
        httpComponentsMessageSender.afterPropertiesSet();

        return httpComponentsMessageSender;
    }

    @Bean
    public HttpClient httpClient() {

        return HttpClients.custom()
                .addInterceptorFirst( new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor() )
                .build();
    }

    @Bean
    public SaajSoapMessageFactory messageFactory() {

        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion( SoapVersion.SOAP_12 );

        return messageFactory;
    }

}
