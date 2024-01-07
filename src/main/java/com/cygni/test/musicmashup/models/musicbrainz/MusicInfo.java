
package com.cygni.test.musicmashup.models.musicbrainz;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.management.relation.Relation;

import com.cygni.test.musicmashup.models.musicbrainz.BeginArea;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "disambiguation",
    "gender",
    "begin-area",
    "type",
    "end-area",
    "relations",
    "id",
    "release-groups",
    "sort-name",
    "type-id",
    "isnis",
    "area",
    "country",
    "name",
    "life-span",
    "ipis",
    "gender-id"
})

@AllArgsConstructor
@Generated("jsonschema2pojo")
public class MusicInfo {

    @JsonProperty("disambiguation")
    private String disambiguation;
    @JsonProperty("gender")
    private Object gender;
    @JsonProperty("begin-area")
    private BeginArea beginArea;
    @JsonProperty("type")
    private String type;
    @JsonProperty("end-area")
    private Object endArea;
    @JsonProperty("relations")
    private List<Relation> relations;
    @JsonProperty("id")
    private String id;
    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups;
    @JsonProperty("sort-name")
    private String sortName;
    @JsonProperty("type-id")
    private String typeId;
    @JsonProperty("isnis")
    private List<String> isnis;
    @JsonProperty("area")
    private Area area;
    @JsonProperty("country")
    private String country;
    @JsonProperty("name")
    private String name;
    @JsonProperty("life-span")
    private LifeSpan lifeSpan;
    @JsonProperty("ipis")
    private List<Object> ipis;
    @JsonProperty("gender-id")
    private Object genderId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("disambiguation")
    public String getDisambiguation() {
        return disambiguation;
    }

    @JsonProperty("disambiguation")
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    @JsonProperty("gender")
    public Object getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Object gender) {
        this.gender = gender;
    }

    @JsonProperty("begin-area")
    public BeginArea getBeginArea() {
        return beginArea;
    }

    @JsonProperty("begin-area")
    public void setBeginArea(BeginArea beginArea) {
        this.beginArea = beginArea;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("end-area")
    public Object getEndArea() {
        return endArea;
    }

    @JsonProperty("end-area")
    public void setEndArea(Object endArea) {
        this.endArea = endArea;
    }

    @JsonProperty("relations")
    public List<Relation> getRelations() {
        return relations;
    }

    @JsonProperty("relations")
    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("release-groups")
    public List<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }

    @JsonProperty("release-groups")
    public void setReleaseGroups(List<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    @JsonProperty("sort-name")
    public String getSortName() {
        return sortName;
    }

    @JsonProperty("sort-name")
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @JsonProperty("type-id")
    public String getTypeId() {
        return typeId;
    }

    @JsonProperty("type-id")
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("isnis")
    public List<String> getIsnis() {
        return isnis;
    }

    @JsonProperty("isnis")
    public void setIsnis(List<String> isnis) {
        this.isnis = isnis;
    }

    @JsonProperty("area")
    public Area getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(Area area) {
        this.area = area;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("life-span")
    public LifeSpan getLifeSpan() {
        return lifeSpan;
    }

    @JsonProperty("life-span")
    public void setLifeSpan(LifeSpan lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    @JsonProperty("ipis")
    public List<Object> getIpis() {
        return ipis;
    }

    @JsonProperty("ipis")
    public void setIpis(List<Object> ipis) {
        this.ipis = ipis;
    }

    @JsonProperty("gender-id")
    public Object getGenderId() {
        return genderId;
    }

    @JsonProperty("gender-id")
    public void setGenderId(Object genderId) {
        this.genderId = genderId;
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
