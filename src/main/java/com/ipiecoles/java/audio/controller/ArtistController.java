package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.Repository.ArtistRepository;
import com.ipiecoles.java.audio.exception.EntityConflictException;
import com.ipiecoles.java.audio.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/* Gérer exceptions
throws EntityConflictException
throw new EntityConflictException("ghfgh");
*/
@RestControllerAdvice
@RequestMapping("/artists")
public class ArtistController {

        @Autowired
        private ArtistRepository artistRepository;

        //1ére question
        @RequestMapping("/{id}")
        public Artist getArtistById(@PathVariable("id") Long id){
                //Affiche les informations du commercial d'identifiant id
                Optional<Artist> a = artistRepository.findById(id);
                if(a.isPresent()){
                        return a.get();
                }
                throw new EntityNotFoundException("L'employé d'id "+ id +  " n'éxiste pas !");
        }

        //2éme question
        @RequestMapping(value = "", params = "name")
        public Page<Artist> findArtistWithName(@RequestParam("name") String name,
                                               @RequestParam("page") Integer page,
                                               @RequestParam("size") Integer size,
                                               @RequestParam("sortDirection") Sort.Direction sortDirection,
                                               @RequestParam("sortProperty") String sortProperty){
                return artistRepository.findByNameContaining(name,PageRequest.of(page,size, sortDirection,sortProperty));
        }

        //3éme question
        @RequestMapping(method = RequestMethod.GET)
        public Page<Artist> findAllArtist(@RequestParam("page") Integer page,
                                            @RequestParam("size") Integer size,
                                            @RequestParam("sortDirection") Sort.Direction sortDirection,
                                            @RequestParam("sortProperty") String sortProperty){

                return artistRepository.findAll(PageRequest.of(page,size, sortDirection,sortProperty));
        }

        //4éme question
        @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Artist createArtist(@RequestBody Artist artist) throws EntityConflictException {
                Artist artistSearch = artistRepository.findByName(artist.getName());
                if(artistSearch != null){
                        throw new EntityConflictException("L'artiste existe déjà!");
                }
                return this.artistRepository.save(artist);
        }

        //5éme question
        @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
        public Artist ModifyArtist(@PathVariable("id") Long idEmploye,@RequestBody Artist artist){
                return artistRepository.save(artist);
        }

        //6éme question
        @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteArtist(@PathVariable("id") Long id){
                artistRepository.deleteById(id);
        }
}
