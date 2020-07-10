package io.github.tiagoadmstz.algamoney.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Registry.class)
public abstract class Registry_ {

	public static volatile SingularAttribute<Registry, RegistryType> registryType;
	public static volatile SingularAttribute<Registry, String> note;
	public static volatile SingularAttribute<Registry, Person> person;
	public static volatile SingularAttribute<Registry, LocalDate> dueDate;
	public static volatile SingularAttribute<Registry, String> description;
	public static volatile SingularAttribute<Registry, LocalDate> payday;
	public static volatile SingularAttribute<Registry, Long> id;
	public static volatile SingularAttribute<Registry, Category> category;
	public static volatile SingularAttribute<Registry, BigDecimal> value;

}

