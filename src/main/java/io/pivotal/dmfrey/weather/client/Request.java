package io.pivotal.dmfrey.weather.client;

public class Request<P> {

    private final P payload;

    public Request( P payload ) {

        this.payload = payload;

    }

    public P getPayload() {

        return payload;
    }

}
