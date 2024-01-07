
package com.cygni.test.musicmashup.models.coverart;

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
    "250",
    "500",
    "1200",
    "large",
    "small"
})

@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Thumbnails {

    @JsonProperty("250")
    private String _250;
    @JsonProperty("500")
    private String _500;
    @JsonProperty("1200")
    private String _1200;
    @JsonProperty("large")
    private String large;
    @JsonProperty("small")
    private String small;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("250")
    public String get250() {
        return _250;
    }

    @JsonProperty("250")
    public void set250(String _250) {
        this._250 = _250;
    }

    @JsonProperty("500")
    public String get500() {
        return _500;
    }

    @JsonProperty("500")
    public void set500(String _500) {
        this._500 = _500;
    }

    @JsonProperty("1200")
    public String get1200() {
        return _1200;
    }

    @JsonProperty("1200")
    public void set1200(String _1200) {
        this._1200 = _1200;
    }

    @JsonProperty("large")
    public String getLarge() {
        return large;
    }

    @JsonProperty("large")
    public void setLarge(String large) {
        this.large = large;
    }

    @JsonProperty("small")
    public String getSmall() {
        return small;
    }

    @JsonProperty("small")
    public void setSmall(String small) {
        this.small = small;
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
