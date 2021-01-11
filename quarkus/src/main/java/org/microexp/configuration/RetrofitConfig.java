package org.microexp.configuration;

import org.microexp.FinnhubService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RetrofitConfig {

    @Produces
    FinnhubService getFinnhub() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finnhub.io")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        FinnhubService service = retrofit.create(FinnhubService.class);
        return service;
    }
}
