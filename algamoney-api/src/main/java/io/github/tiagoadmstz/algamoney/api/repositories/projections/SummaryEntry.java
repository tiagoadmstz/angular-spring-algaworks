package io.github.tiagoadmstz.algamoney.api.repositories.projections;

import io.github.tiagoadmstz.algamoney.api.models.EntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryEntry implements Serializable {

    private static final long serialVersionUID = 6050551827825587201L;
    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate payday;
    private BigDecimal value;
    private EntryType entryType;
    private String category;
    private String person;

}
