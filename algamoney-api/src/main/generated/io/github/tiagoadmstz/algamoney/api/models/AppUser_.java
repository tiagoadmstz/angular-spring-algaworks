package io.github.tiagoadmstz.algamoney.api.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ {

	public static volatile SingularAttribute<AppUser, String> password;
	public static volatile SingularAttribute<AppUser, String> name;
	public static volatile ListAttribute<AppUser, Permition> permitions;
	public static volatile SingularAttribute<AppUser, Long> id;
	public static volatile SingularAttribute<AppUser, String> email;

}

