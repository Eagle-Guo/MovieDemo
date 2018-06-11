package com.maltem.movie.controller;

import com.maltem.movie.entity.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
	 /**
	  * list all the movie data
	  * @return
	  */
	 @RequestMapping(value="list", method=RequestMethod.GET, consumes="application/json") 
	 public List<Movie> listAll (){
		 List<Movie> movies = new ArrayList<Movie>();
		  return movies;
	 }
	 
	 /**
	  * get the movie base on name
	  * @param name
	  * @return
	  */
	 @RequestMapping(value="get/{name}", method=RequestMethod.GET, consumes="application/json") 
	 public Movie getMovieByName (@PathVariable String name){
		 Movie movie = new Movie();
		 movie.setTitle("Ocean's 8");
		 movie.setDirector("Gary Ross");
		 Calendar cal = Calendar.getInstance();
		 cal.set(2018, 06, 13);
		 movie.setReleaseDate(cal.getTime());
		 movie.setType("action");
		 return movie;
	 }
	 
	 
}
