package org.example;

public interface AlphaVantage {

    // https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&apikey=demo
    @GET("query?function=GLOBAL_QUOTE&apikey=OHWOM3TXBGMY9WGW")
    Call<Quote> quote(@Query("symbol") String ticker);
}