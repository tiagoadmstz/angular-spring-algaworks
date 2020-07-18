package io.github.tiagoadmstz.algamoney.api.repositories.filters;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EntryFilter {

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateUntil;

}
