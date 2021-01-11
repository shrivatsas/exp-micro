package org.microexp.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/hello")
public class Greeting {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "performedChecks", description = "How many greetings have been performed.")
    @Timed(name = "checksTimer", description = "A measure of how long it takes to greet.", unit = MetricUnits.MILLISECONDS)
    public String hello() {
        return "hello";
    }
}