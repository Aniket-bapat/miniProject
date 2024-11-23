package project.man.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;
    
    @Email(message="Invalid Email")
    @NotEmpty(message="Email mising")
    private String email;
    @NotEmpty(message="Password mising")
    private String password;
}
