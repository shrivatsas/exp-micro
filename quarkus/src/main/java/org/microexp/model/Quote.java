package org.microexp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Quote {
    @JsonProperty("c")
    Double current;
    @JsonProperty("h")
    Double high;
    @JsonProperty("l")
    Double low;
    @JsonProperty("o")
    Double open;
    @JsonProperty("pc")
    Double previousClose;
    @JsonProperty("t")
    Long timestamp;
}
