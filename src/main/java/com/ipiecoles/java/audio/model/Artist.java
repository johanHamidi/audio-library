package com.ipiecoles.java.audio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="artist")
public class Artist {

    public Artist(){}

    public Artist(Long id, String name, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @OneToMany(mappedBy = "artist")
    //Eviter boucle infini
    @JsonIgnoreProperties("artist")

    private List<Album> albums;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
