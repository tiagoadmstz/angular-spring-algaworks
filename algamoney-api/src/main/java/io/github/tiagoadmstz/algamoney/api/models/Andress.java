package io.github.tiagoadmstz.algamoney.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.tiagoadmstz.algamoney.api.serializers.ZipCodeSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Embeddable
public class Andress implements Serializable {

    private static final long serialVersionUID = -3102553444428056603L;
    @JsonProperty("street-andress")
    @NotNull
    @Size(min = 5, max = 255)
    @Column(name = "street_andress")
    private String streetAndress;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "number")
    private String number = "N/A";
    @Column(name = "adjunct")
    private String adjunct;
    @NotNull
    @Size(min = 5, max = 60)
    @Column(name = "district")
    private String district;
    @JsonProperty("zip-code")
    @JsonSerialize(using = ZipCodeSerializer.class)
    @NotNull
    @Size(min = 7, max = 7)
    @Pattern(regexp = "\\d{7}", message = "O CEP deve conter apenas números")
    @Column(name = "zip_code")
    private String zipCode;
    @NotNull
    @Size(min = 5, max = 60)
    @Column(name = "city")
    private String city;
    @NotNull
    @Size(min = 2, max = 60)
    @Column(name = "state")
    private String state;

}
