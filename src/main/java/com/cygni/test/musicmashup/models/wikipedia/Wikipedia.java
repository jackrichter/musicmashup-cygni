
package com.cygni.test.musicmashup.models.wikipedia;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "batchcomplete",
    "warnings",
    "query"
})

@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Wikipedia {

    @JsonProperty("batchcomplete")
    private String batchcomplete;
    @JsonProperty("warnings")
    private Warnings warnings;
    @JsonProperty("query")
    private Query query;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("batchcomplete")
    public String getBatchcomplete() {
        return batchcomplete;
    }

    @JsonProperty("batchcomplete")
    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    @JsonProperty("warnings")
    public Warnings getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(Warnings warnings) {
        this.warnings = warnings;
    }

    @JsonProperty("query")
    public Query getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(Query query) {
        this.query = query;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
