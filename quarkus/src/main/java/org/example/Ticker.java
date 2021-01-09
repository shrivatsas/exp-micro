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
import java.io.InputStream;
import java.util.*;

@Path("/random")
public class Ticker {

    private static final Logger LOG = Logger.getLogger(Ticker.class);
    private FinnhubService finnhubService;
    private List<String> stocks;

    Ticker(FinnhubService in) {
        this.finnhubService = in;
        this.stocks = new ArrayList<>();
        InputStream ins = getClass().getResourceAsStream("/stocks.json");
        Scanner st = new Scanner(ins).useDelimiter(",");

        while(st.hasNext()) {
            stocks.add(st.next().replace("\"",""));
        }
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