
package com.cygni.test.musicmashup.models.wikipedia;

import java.util.LinkedHashMap;
import java.util.List;
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
    "normalized",
    "pages"
})

@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Query {

    @JsonProperty("normalized")
    private List<Normalized> normalized;
    @JsonProperty("pages")
    private Pages pages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("normalized")
    public List<Normalized> getNormalized() {
        return normalized;
    }

    @JsonProperty("normalized")
    public void setNormalized(List<Normalized> normalized) {
        this.normalized = normalized;
    }

    @JsonProperty("pages")
    public Pages getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(Pages pages) {
        this.pages = pages;
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
