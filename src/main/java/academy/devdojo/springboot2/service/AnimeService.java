package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.repository.AnimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService implements AnimeRepository {
    private final List<Anime> animes = List.of(new Anime(1L, "Vagabond"), new Anime(2L, "Berserk"));

    public List<Anime> listAll() {
        return animes;
    }

    public Anime getById(long id) {
        return animes.stream()// Cria um stream da lista de animes
                .filter(anime -> anime.getId().equals(id))// Filtra o stream para retornar o anime com o id passado como parâmetro
                .findFirst() // Retorna o primeiro elemento do stream
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found")); // Caso não encontre o anime, lança uma exceção -> 400 BAD REQUEST
    }
}
