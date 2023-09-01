package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // This annotation will load only the necessary dependencies to test JPA
@DisplayName("Anime Repository Test")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;


    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertNotNull(animeSaved);

    }


    @Test
    @DisplayName("Save updates anime when successful")
    void save_UpdatesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        animeSaved.setName("Overlord");
        Anime animeUpdated = this.animeRepository.save(animeSaved);
        Assertions.assertNotNull(animeUpdated);
        Assertions.assertEquals(animeSaved.getName(), animeUpdated.getName());
    }

    private static Anime createAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }
}
