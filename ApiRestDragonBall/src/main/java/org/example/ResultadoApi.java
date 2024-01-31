package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResultadoApi implements Serializable {
    private List<Data> results;

    public List<Data> getResults() {
        return results;
    }

    public void setResults(List<Data> results) {
        this.results = results;
    }

    // Constructor/fábrica para manejar la deserialización del JSON
    @JsonCreator
    public ResultadoApi(@JsonProperty("results")List<Data> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultadoApi{" +
                "results=" + results +
                '}';
    }
}
