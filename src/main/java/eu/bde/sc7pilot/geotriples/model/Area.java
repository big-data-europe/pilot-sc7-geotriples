package eu.bde.sc7pilot.geotriples.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.vividsolutions.jts.geom.Geometry;

import eu.bde.sc7pilot.geotriples.utils.Views;

public class Area {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(Views.Private.class)
	private Long id;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(Views.Public.class)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(Views.Public.class)
	private Geometry geometry;

	public Area() {
		this (null, null, null);
	}

	public Area(String name, Geometry geometry, Long id) {
		this.id = id;
		this.name = name;
		this.geometry = geometry;
	}

	public Area(String name, Geometry geometry) {
		this(name, geometry, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
