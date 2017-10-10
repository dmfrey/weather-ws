package io.pivotal.dmfrey.weather.client;

import javax.xml.bind.JAXBElement;

public class Response<R> {

    private final R callResponse;

    public Response( final R response ) {

        if( response instanceof JAXBElement<?> ) {

            this.callResponse = ( (JAXBElement<R>) response ).getValue();

        } else {

            this.callResponse = response;

        }

    }

    public R getResponse() {

        return callResponse;
    }

}
