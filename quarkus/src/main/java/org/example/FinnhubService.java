package org.example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinnhubService {
    // https://finnhub.io/api/v1/quote?symbol=GOOG&token=bvs3jlf48v6oefuv7b7g
    @GET("/api/v1/quote?token=bvs3jlf48v6oefuv7b7g")
    Call<Quote> quote(@Query("symbol") String ticker);
}