
package com.cygni.test.musicmashup.models.wikidata;

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
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "site",
    "title",
    "badges"
})

@NoArgsConstructor
@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Enwiki {

    @JsonProperty("site")
    private String site;
    @JsonProperty("title")
    private String title;
    @JsonProperty("badges")
    private List<String> badges;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("site")
    public String getSite() {
        return site;
    }

    @JsonProperty("site")
    public void setSite(String site) {
        this.site = site;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("badges")
    public List<String> getBadges() {
        return badges;
    }

    @JsonProperty("badges")
    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Enwiki{" +
                "site='" + site + '\'' +
                ", title='" + title + '\'' +
                ", badges=" + badges +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
