package org.example;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Path("/random")
public class Ticker {

    private static final Logger LOG = Logger.getLogger(Ticker.class);
    private FinnhubService finnhubService;
    private List<String> stocks;

    Ticker(FinnhubService in) {
        this.finnhubService = in;
        this.stocks = Arrays.asList("Y|FB","Y|AAPL","Y|AMZN","Y|NFLX","Y|GOOG","Y|MSFT");
//        try {
//            this.stocks = Files.readAllLines(Paths.get("stock.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "performedChecks", description = "How many stock ticks have been referred.")
    @Timed(name = "checksTimer", description = "A measure of how long it takes for a tick.", unit = MetricUnits.MILLISECONDS)
    public String stock() {
        String currentQuote = "?";
        int idx = (new Random()).nextInt(stocks.size());
        String stock = stocks.get(idx).split("\\|")[1];
        try {
            Quote q = finnhubService.quote(stock).execute().body();
            currentQuote = String.valueOf(q.current);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stock + " " + currentQuote;
    }
}