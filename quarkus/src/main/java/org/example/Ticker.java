package org.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/random")
public class Ticker {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "performedChecks", description = "How many stock ticks have been referred.")
    @Timed(name = "checksTimer", description = "A measure of how long it takes for a tick.", unit = MetricUnits.MILLISECONDS)
    public String stock() {
        return "hello";
    }
}