package io.github.tiagoadmstz.algamoney.api.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "person")
@SequenceGenerator(name = "person_sequence", allocationSize = 1)
public class Person implements Serializable {

    private static final long serialVersionUID = -6968197561694183052L;
    @Id
    @Column(name = "id", length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    private Long id;
    @NotNull
    @Size(min = 10, max = 255)
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "active", columnDefinition = "boolean")
    private Boolean active = true;
    @Embedded
    private Andress andress;

}
