package com.ben.pixcreator.server.data.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PixelGridDto.class)
public abstract class PixelGridDto_ {

	public static volatile SingularAttribute<PixelGridDto, String> owner;
	public static volatile SingularAttribute<PixelGridDto, byte[]> miniatureBytes;
	public static volatile MapAttribute<PixelGridDto, CoordDto, ColorRGBDto> grid;
	public static volatile SingularAttribute<PixelGridDto, String> name;
	public static volatile SingularAttribute<PixelGridDto, String> description;
	public static volatile SingularAttribute<PixelGridDto, Integer> id;
	public static volatile SetAttribute<PixelGridDto, String> filters;

	public static final String OWNER = "owner";
	public static final String MINIATURE_BYTES = "miniatureBytes";
	public static final String GRID = "grid";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String FILTERS = "filters";

}

