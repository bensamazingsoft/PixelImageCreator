package com.ben.pixcreator.server.data.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ColorRGBDto.class)
public abstract class ColorRGBDto_ {

	public static volatile SingularAttribute<ColorRGBDto, CoordDto> coord;
	public static volatile SingularAttribute<ColorRGBDto, Integer> id;
	public static volatile MapAttribute<ColorRGBDto, String, Double> rgb;

	public static final String COORD = "coord";
	public static final String ID = "id";
	public static final String RGB = "rgb";

}

