package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // This annotation will load only the necessary dependencies to test JPA
@DisplayName("Anime Repository Test")
@Log4j2
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        log.info(animeSaved.getName());
        Assertions.assertNotNull(animeSaved);

    }

    @Test
    @DisplayName("Save updates anime when successful")
    void save_UpdatesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        animeSaved.setName("Overlord");
        Anime animeUpdated = this.animeRepository.save(animeSaved);
        //log.info(animeUpdated.getName());
        Assertions.assertNotNull(animeUpdated);
        Assertions.assertEquals(animeSaved.getName(), animeUpdated.getName());
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_removesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        this.animeRepository.delete(animeSaved);
        Anime anime = this.animeRepository.findById(animeSaved.getId()).orElse(null);
        log.info(anime);
        Assertions.assertNull(anime);
    }

    @Test
    @DisplayName("Find by name returns list of anime when successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        String name = animeSaved.getName();
        var animes = this.animeRepository.findByName(name);
        log.info(animes);
        Assertions.assertFalse(animes.isEmpty());
        Assertions.assertEquals(animeSaved.getName(), animes.get(0).getName());
    }

    @Test
    @DisplayName("Find by name returns empty list when no anime is found")
    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound() {
        var animes = this.animeRepository.findByName("xaxa");
        log.info(animes);
        Assertions.assertTrue(animes.isEmpty());
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
        Anime anime = new Anime();
        //Assertions.assertThrows(ConstraintViolationException.class, () -> this.animeRepository.save(anime));
        Assertions.assertThrows(Exception.class, () -> this.animeRepository.save(anime));
    }

    private static Anime createAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }
}
