package project.man.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project {
      @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;
 
    @Column(columnDefinition="text")
    @NotEmpty(message="Description mising")
    private String description;
 
    @Column(columnDefinition="text")
    @NotEmpty(message="Requirements mising")
    private String requirements;
}
