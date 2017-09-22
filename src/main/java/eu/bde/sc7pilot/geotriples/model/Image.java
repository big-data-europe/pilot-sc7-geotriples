package eu.bde.sc7pilot.geotriples.model;

import com.fasterxml.jackson.annotation.JsonView;
import eu.bde.sc7pilot.geotriples.utils.Views;
public class Image{
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
	    this.place=place;
	}

	public String getLink() {
	    return link;
	}

	public void setLink(String link) {
	    this.link=link;
	}

	public String getPairid() {
		return pairid;
	}

	public void setPairid(String pairid) {
		this.pairid = pairid;
	}



    public Image(String place, String link){
	this.place = place;
	this.link = link;
    }

    public Image(){
	this(null,null);
    }

	@JsonView(Views.Public.class)
	String pairid;

	@JsonView(Views.Public.class)
	String id;
	@JsonView(Views.Public.class)
	String place;
	@JsonView(Views.Public.class)
	String link;

}
