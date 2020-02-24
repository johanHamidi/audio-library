package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.Repository.AlbumRepository;
import com.ipiecoles.java.audio.Repository.ArtistRepository;
import com.ipiecoles.java.audio.exception.EntityConflictException;
import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;

    //Question 7

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Album createAlbum(@RequestBody Album album) throws EntityConflictException {
        Album albumSearch = albumRepository.findByTitle(album.getTitle());
        if(albumSearch != null){
            throw new EntityConflictException("L'album existe déjà!");
        }
        return this.albumRepository.save(album);
    }

    //Question 8
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") Integer idAlbum){
        albumRepository.deleteById(idAlbum);
    }

}
