package com.cygni.test.musicmashup.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DetailedResponse {
    private String mbid;
    private String description;
    private List<Album> albums;

    public void addAlbum(Album album) {
        albums.add(album);
    }
}
