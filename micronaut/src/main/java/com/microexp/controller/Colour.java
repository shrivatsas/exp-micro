package com.microexp.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.tracing.annotation.ContinueSpan;

import java.util.Random;
import java.util.stream.Collectors;

@Controller("/random")
public class Colour {

    @ContinueSpan
    @Get(produces = MediaType.TEXT_PLAIN)
    public String generate() {
        Random r = new Random();
        return r.ints(3, 0, 256)
                .mapToObj(Integer::toHexString)
                .collect(Collectors.joining());
    }

}
