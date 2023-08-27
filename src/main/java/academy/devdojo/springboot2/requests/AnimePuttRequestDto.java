package academy.devdojo.springboot2.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // Annotation para criar os métodos getters e setters
public class AnimePuttRequestDto {
    private Long id;

    @NotBlank(message = "The anime name cannot be empty")
    private String name;
}
