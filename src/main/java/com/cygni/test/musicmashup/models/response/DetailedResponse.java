package com.cygni.test.musicmashup.models.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"mbid", "description, albums"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DetailedResponse {
    @JsonProperty("mbid")
    private String mbid;

    @JsonProperty("description")
    private String description;
    @JsonProperty("albums")
    private List<Album> albums;

    @JsonIgnore
    public void addAlbum(Album album) {
        albums.add(album);
    }
}
