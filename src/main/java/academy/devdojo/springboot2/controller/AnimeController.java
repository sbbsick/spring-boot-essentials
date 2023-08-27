package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.requests.AnimePostRequestDto;
import academy.devdojo.springboot2.requests.AnimePuttRequestDto;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/animes")
@Log4j2 // Annotation para criar um logger da classe
//@AllArgsConstructor // Annotation para criar um construtor com todos os atributos da classe
@RequiredArgsConstructor // Annotation para criar um construtor com os atributos final da classe
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        // Cria um objeto ResponseEntity com o status 200 e o body com a lista de animes
        //ResponseEntity<List<Anime>> body =
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok().body(animeService.listAll());
    }

    @GetMapping(path = "/{id}") // Path passando um id como parâmetro na url
    public ResponseEntity<Anime> getById(@PathVariable long id) { // @PathVariable -> Pega o id passado na url e atribui ao parâmetro id
        return ResponseEntity.ok().body(animeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestDto anime) { // @RequestBody -> Pega o body da requisição e atribui ao parâmetro anime
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED); // Cria um objeto ResponseEntity com o status 201 e o body com o anime salvo
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Cria um objeto ResponseEntity com o status 204
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePuttRequestDto animePuttRequestDto) {
        animeService.update(animePuttRequestDto); // Atualiza o anime
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Cria um objeto ResponseEntity com o status 204
    }
}

