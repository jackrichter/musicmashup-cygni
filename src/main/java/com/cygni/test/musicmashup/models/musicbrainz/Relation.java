
package com.example;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.cygni.test.musicmashup.models.musicbrainz.AttributeIds;
import com.cygni.test.musicmashup.models.musicbrainz.AttributeValues;
import com.cygni.test.musicmashup.models.musicbrainz.Url;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "direction",
    "type-id",
    "ended",
    "source-credit",
    "end",
    "attributes",
    "attribute-ids",
    "target-credit",
    "url",
    "begin",
    "attribute-values",
    "target-type",
    "type"
})

@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Relation {

    @JsonProperty("direction")
    private String direction;
    @JsonProperty("type-id")
    private String typeId;
    @JsonProperty("ended")
    private Boolean ended;
    @JsonProperty("source-credit")
    private String sourceCredit;
    @JsonProperty("end")
    private Object end;
    @JsonProperty("attributes")
    private List<Object> attributes;
    @JsonProperty("attribute-ids")
    private AttributeIds attributeIds;
    @JsonProperty("target-credit")
    private String targetCredit;
    @JsonProperty("url")
    private Url url;
    @JsonProperty("begin")
    private Object begin;
    @JsonProperty("attribute-values")
    private AttributeValues attributeValues;
    @JsonProperty("target-type")
    private String targetType;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("type-id")
    public String getTypeId() {
        return typeId;
    }

    @JsonProperty("type-id")
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("ended")
    public Boolean getEnded() {
        return ended;
    }

    @JsonProperty("ended")
    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    @JsonProperty("source-credit")
    public String getSourceCredit() {
        return sourceCredit;
    }

    @JsonProperty("source-credit")
    public void setSourceCredit(String sourceCredit) {
        this.sourceCredit = sourceCredit;
    }

    @JsonProperty("end")
    public Object getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(Object end) {
        this.end = end;
    }

    @JsonProperty("attributes")
    public List<Object> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("attribute-ids")
    public AttributeIds getAttributeIds() {
        return attributeIds;
    }

    @JsonProperty("attribute-ids")
    public void setAttributeIds(AttributeIds attributeIds) {
        this.attributeIds = attributeIds;
    }

    @JsonProperty("target-credit")
    public String getTargetCredit() {
        return targetCredit;
    }

    @JsonProperty("target-credit")
    public void setTargetCredit(String targetCredit) {
        this.targetCredit = targetCredit;
    }

    @JsonProperty("url")
    public Url getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(Url url) {
        this.url = url;
    }

    @JsonProperty("begin")
    public Object getBegin() {
        return begin;
    }

    @JsonProperty("begin")
    public void setBegin(Object begin) {
        this.begin = begin;
    }

    @JsonProperty("attribute-values")
    public AttributeValues getAttributeValues() {
        return attributeValues;
    }

    @JsonProperty("attribute-values")
    public void setAttributeValues(AttributeValues attributeValues) {
        this.attributeValues = attributeValues;
    }

    @JsonProperty("target-type")
    public String getTargetType() {
        return targetType;
    }

    @JsonProperty("target-type")
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
