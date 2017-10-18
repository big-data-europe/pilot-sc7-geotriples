package eu.bde.sc7pilot.geotriples.model;

import com.fasterxml.jackson.annotation.JsonView;

import eu.bde.sc7pilot.geotriples.utils.Views;

public class Entity {
	
	public String getThesaurus_uuid() {
		return thesaurus_uuid;
	}

	public void setThesaurus_uuid(String thesaurus_uuid) {
		this.thesaurus_uuid = thesaurus_uuid;
	}

	public String getConcept_uri() {
		return concept_uri;
	}

	public void setConcept_uri(String concept_uri) {
		this.concept_uri = concept_uri;
	}

	public String getPairid() {
		return pairid;
	}

	public void setPairid(String pairid) {
		this.pairid = pairid;
	}
	
    public String getPrefLabel() {
		return prefLabel;
	}

	public void setPrefLabel(String prefLabel) {
		this.prefLabel = prefLabel;
	}
	
	@JsonView(Views.Public.class)
	String pairid;
	@JsonView(Views.Public.class)
	String thesaurus_uuid;
	@JsonView(Views.Public.class)
	String concept_uri;
	@JsonView(Views.Public.class)
	String prefLabel;

}
