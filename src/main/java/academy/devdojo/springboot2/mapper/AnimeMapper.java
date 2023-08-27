package academy.devdojo.springboot2.mapper;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimePostRequestDto;
import academy.devdojo.springboot2.requests.AnimePuttRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Annotation para criar uma implementação da interface AnimeMapper
public abstract class AnimeMapper {
    public abstract Anime toAnime(AnimePostRequestDto animePostRequestDto);

    public abstract Anime toAnime(AnimePuttRequestDto animePuttRequestDto);
}
