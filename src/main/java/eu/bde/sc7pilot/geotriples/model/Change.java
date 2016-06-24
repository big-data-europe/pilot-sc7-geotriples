package eu.bde.sc7pilot.geotriples.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import eu.bde.sc7pilot.geotriples.utils.Views;

public class Change {
	@JsonView(Views.Private.class)
	private Long id;
	@JsonView(Views.Public.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private DateTime sourceDate;
	@JsonView(Views.Public.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private DateTime targetDate;
	@JsonView(Views.Public.class)
	private Area area;

	public Change() {
		this(null, null, null, null);
	}

	public Change(DateTime date, Area area, Long id) {
		this(id, date, null, area);
	}
	public Change( DateTime sourceDate, DateTime targetDate, Area area) {
		this(null, sourceDate, targetDate, area);
	}
	public Change(Long id, DateTime sourceDate, DateTime targetDate, Area area) {
		this.area = area;
		this.id = id;
		this.sourceDate = sourceDate;
		this.targetDate = targetDate;
	}
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setChangeId(Long changeId) {
		this.id = changeId;
	}

	public DateTime getSourceDate() {
		return sourceDate;
	}

	public void setDate(DateTime date) {
		this.sourceDate = date;
	}

	public void setSourceDate(DateTime sourceDate) {
		this.sourceDate = sourceDate;
	}

	public DateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(DateTime targetDate) {
		this.targetDate = targetDate;
	}
}
