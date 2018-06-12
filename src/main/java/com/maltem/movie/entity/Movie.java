package com.maltem.movie.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Movie {
	String title;
	
	String director;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	Date releaseDate;
	
	String type;
	
	public Movie() {}
	
	public Movie(String title, String director, Date releaseDate, String type) {
		super();
		this.title = title;
		this.director = director;
		this.releaseDate = releaseDate;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
