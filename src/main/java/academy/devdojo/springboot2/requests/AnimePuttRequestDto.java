package academy.devdojo.springboot2.requests;

import lombok.Data;

@Data // Annotation para criar os métodos getters e setters
public class AnimePuttRequestDto {
    private Long id;
    private String name;
}
