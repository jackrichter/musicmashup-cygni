
package com.cygni.test.musicmashup.models.musicbrainz;

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
    "iso-3166-1-codes",
    "type-id",
    "disambiguation",
    "sort-name",
    "id",
    "type",
    "name"
})

@NoArgsConstructor
@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Area {

    @JsonProperty("iso-3166-1-codes")
    private List<String> iso31661Codes;
    @JsonProperty("type-id")
    private Object typeId;
    @JsonProperty("disambiguation")
    private String disambiguation;
    @JsonProperty("sort-name")
    private String sortName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private Object type;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("iso-3166-1-codes")
    public List<String> getIso31661Codes() {
        return iso31661Codes;
    }

    @JsonProperty("iso-3166-1-codes")
    public void setIso31661Codes(List<String> iso31661Codes) {
        this.iso31661Codes = iso31661Codes;
    }

    @JsonProperty("type-id")
    public Object getTypeId() {
        return typeId;
    }

    @JsonProperty("type-id")
    public void setTypeId(Object typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("disambiguation")
    public String getDisambiguation() {
        return disambiguation;
    }

    @JsonProperty("disambiguation")
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    @JsonProperty("sort-name")
    public String getSortName() {
        return sortName;
    }

    @JsonProperty("sort-name")
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
