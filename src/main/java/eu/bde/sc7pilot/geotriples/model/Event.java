package eu.bde.sc7pilot.geotriples.model;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import eu.bde.sc7pilot.geotriples.utils.Views;

public class Event {
	@JsonView(Views.Public.class)
	private String id;

	@JsonView(Views.Public.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(Views.Public.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ")
	private DateTime eventDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(Views.Private.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ")
	private DateTime referenceDate;
	@JsonView(Views.Public.class)
	private List<Area> areas;

	@JsonView(Views.Public.class)
	private List<Entity> entities;

	@JsonView(Views.Public.class)
	private List<Image> images;

	public Event() {
	    this(null, null, null, null, null,null,null);
	}

	public Event(String id, String title, DateTime eventDate, List<Area> areas, List<Entity> entities) {
	    this(id, title, eventDate, null, areas, entities, null);
	}

        public Event(String id, String title, DateTime eventDate, List<Area> areas, List<Entity> entities,List<Image> images) {
	    this(id, title, eventDate, null, areas, entities, images);
	}

	public Event(String id, String title, DateTime eventDate, DateTime referenceDate, List<Area> areas,List<Entity> entities, List<Image> images) {
		this.id = id;
		this.title = title;
		this.eventDate = eventDate;
		this.referenceDate = referenceDate;
		this.areas = areas;
		this.entities = entities;
		this.images = images;
	}

    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(DateTime eventDate) {
		this.eventDate = eventDate;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public DateTime getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(DateTime referenceDate) {
		this.referenceDate = referenceDate;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof Event))
			return false;

		final Event event = (Event) other;

		if (!event.getId().equals(getId()))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getId().hashCode();
		return result;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}
