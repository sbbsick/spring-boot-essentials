package academy.devdojo.springboot2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data // Annotation para criar os métodos getters e setters
@ToString // Annotation para criar o método toString
@AllArgsConstructor // Annotation para criar um construtor com todos os atributos da classe
@Entity // Annotation para criar uma tabela no banco de dados
@Builder // Annotation para criar um construtor com os atributos final da classe
public class Anime {
    public Anime() {
    }

    @Id // Annotation para definir o atributo id como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation para definir o atributo id como auto incremento
    private Long id;
    private String name;
}
