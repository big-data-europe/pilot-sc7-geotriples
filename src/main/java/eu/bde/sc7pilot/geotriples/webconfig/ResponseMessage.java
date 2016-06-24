package eu.bde.sc7pilot.geotriples.webconfig;

public class ResponseMessage {
private int code;
private String message;
public ResponseMessage() {
}
public ResponseMessage(int code, String message) {
	super();
	this.code = code;
	this.message = message;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
