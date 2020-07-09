package io.github.tiagoadmstz.algamoney.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "registry")
@SequenceGenerator(name = "registry_sequence", allocationSize = 1)
public class Registry implements Serializable {

    private static final long serialVersionUID = 9011357890249098191L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registry_sequence")
    private Long id;
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "description", length = 50)
    private String description;
    @JsonProperty("due-date")
    @Column(name = "due_date")
    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dueDate;
    @Column(name = "payday")
    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate payday;
    @NotNull
    @Column(name = "registry_value", length = 10, scale = 2)
    private BigDecimal value;
    @Size(max = 100)
    @Column(name = "note", length = 100)
    private String note;
    @JsonProperty("type")
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RegistryType registryType;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
