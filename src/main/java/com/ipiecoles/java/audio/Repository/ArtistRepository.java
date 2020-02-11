package com.ipiecoles.java.audio.Repository;

import com.ipiecoles.java.audio.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository  extends JpaRepository<Artist, Long> {
    Page<Artist> findByNameContaining(String name, Pageable pageable);
    Page<Artist> findAll(Pageable pageable);
    Artist findByName(String name);

}
