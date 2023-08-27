package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.requests.AnimePostRequestDto;
import academy.devdojo.springboot2.requests.AnimePuttRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Annotation para criar um construtor com os atributos final da classe
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final AnimeMapper animeMapper;

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll(); // Caso não encontre o anime, lança uma exceção -> 400 BAD REQUEST
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name); // Caso não encontre o anime, lança uma exceção -> 400 BAD REQUEST
    }

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable); // Caso não encontre o anime, lança uma exceção -> 400 BAD REQUEST
    }

    public Anime getById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found")); // Caso não encontre o anime, lança uma exceção -> 400 BAD REQUEST
    }

    @Transactional() // Caso ocorra uma exceção, o rollback é feito
    public Anime save(AnimePostRequestDto animePostRequestDto) {
        return animeRepository.save(animeMapper.toAnime(animePostRequestDto));
    }

    public void delete(long id) {
        animeRepository.delete(getById(id)); // Remove o anime com o id passado como parâmetro
    }

    @Transactional() // Caso ocorra uma exceção, o rollback é feito
    public void update(AnimePuttRequestDto animePuttRequestDto) {
        Anime savedId = getById(animePuttRequestDto.getId()); // Busca o anime pelo id passado como parâmetro
        Anime anime = animeRepository.save(animeMapper.toAnime(animePuttRequestDto)); // Atualiza o anime, usando dto e mapper
        anime.setId(animePuttRequestDto.getId());

        animeRepository.save(anime); // Salva o anime atualizado
    }


}
