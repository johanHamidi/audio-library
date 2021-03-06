package com.ipiecoles.java.audio.Repository;

import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    //Album findByName(String title);

    Album findByTitle(String title);

}
