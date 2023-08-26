package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data // Annotation para criar os métodos getters e setters
@ToString // Annotation para criar o método toString
@AllArgsConstructor // Annotation para criar um construtor com todos os atributos da classe
public class Anime {
    private Long id;
    private String name;
}
