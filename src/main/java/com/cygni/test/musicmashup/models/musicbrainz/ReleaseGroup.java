
package com.cygni.test.musicmashup.models.musicbrainz;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "primary-type-id",
    "secondary-types",
    "title",
    "secondary-type-ids",
    "primary-type",
    "id",
    "first-release-date",
    "disambiguation"
})

@AllArgsConstructor
@Generated("jsonschema2pojo")
public class ReleaseGroup {

    @JsonProperty("primary-type-id")
    private String primaryTypeId;
    @JsonProperty("secondary-types")
    private List<String> secondaryTypes;
    @JsonProperty("title")
    private String title;
    @JsonProperty("secondary-type-ids")
    private List<String> secondaryTypeIds;
    @JsonProperty("primary-type")
    private String primaryType;
    @JsonProperty("id")
    private String id;
    @JsonProperty("first-release-date")
    private String firstReleaseDate;
    @JsonProperty("disambiguation")
    private String disambiguation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("primary-type-id")
    public String getPrimaryTypeId() {
        return primaryTypeId;
    }

    @JsonProperty("primary-type-id")
    public void setPrimaryTypeId(String primaryTypeId) {
        this.primaryTypeId = primaryTypeId;
    }

    @JsonProperty("secondary-types")
    public List<String> getSecondaryTypes() {
        return secondaryTypes;
    }

    @JsonProperty("secondary-types")
    public void setSecondaryTypes(List<String> secondaryTypes) {
        this.secondaryTypes = secondaryTypes;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("secondary-type-ids")
    public List<String> getSecondaryTypeIds() {
        return secondaryTypeIds;
    }

    @JsonProperty("secondary-type-ids")
    public void setSecondaryTypeIds(List<String> secondaryTypeIds) {
        this.secondaryTypeIds = secondaryTypeIds;
    }

    @JsonProperty("primary-type")
    public String getPrimaryType() {
        return primaryType;
    }

    @JsonProperty("primary-type")
    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("first-release-date")
    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    @JsonProperty("first-release-date")
    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    @JsonProperty("disambiguation")
    public String getDisambiguation() {
        return disambiguation;
    }

    @JsonProperty("disambiguation")
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
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
