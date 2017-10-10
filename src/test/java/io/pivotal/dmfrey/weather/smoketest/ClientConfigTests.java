package io.pivotal.dmfrey.weather.smoketest;

import io.pivotal.dmfrey.weather.client.Request;
import io.pivotal.dmfrey.weather.client.Response;
import io.pivotal.dmfrey.weather.client.weather.WeatherServiceClient;
import net.webservicex.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith( SpringRunner.class )
@SpringBootTest
public class ClientConfigTests {

    private static final Logger log = LoggerFactory.getLogger( ClientConfigTests.class );

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    @Test
    public void supportServiceCreated() throws Exception {

        assertThat( weatherServiceClient ).isNotNull();

    }

    @Test
    public void testWeatherServiceClientGetCitiesByCountry() throws Exception {

//        GetWeather payload = new ObjectFactory().createGetWeather();
//        payload.setCityName( "Philadelphia" );
//        payload.setCountryName( "USA" );

        GetCitiesByCountry payload = new ObjectFactory().createGetCitiesByCountry();
        payload.setCountryName( "united states" );

        Response response = weatherServiceClient.call( new Request<>( payload ) );
        assertThat( response ).isNotNull();
        assertThat( response.getResponse() ).isInstanceOf( GetCitiesByCountryResponse.class );

    }

    @Test
    public void testWeatherServiceClientGetWeather() throws Exception {

        GetWeather payload = new ObjectFactory().createGetWeather();
        payload.setCityName( "Philadelphia" );
        payload.setCountryName( "USA" );

        Response response = weatherServiceClient.call( new Request<>( payload ) );
        assertThat( response ).isNotNull();
        assertThat( response.getResponse() ).isInstanceOf( GetWeatherResponse.class );

    }

}
