package hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class BookstoreApplication {

  @RequestMapping(value = "/recommended")
  public String readingList(){
    return "Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt), The Phoenix Project";
  }
  
  @RequestMapping(value = "/genres")
  public String genreList() {
	  return "Fiction, Thriller, Romance, Science, Kids, Computer, English, Spanish, German, Novel, SciFi";
  }
  
  @RequestMapping(value = "/all") 
  public String allBooks() {
	 return "Book 1, Book 2, Book 3, Book 4, Book 5, Book 6, Book 7, Book 8, Book 9, Book 10";
  }
  
  @RequestMapping(value = "/all/{genre}")
  public String allBooksByGenre(@PathVariable String genre) {
	  if(genre.equalsIgnoreCase("Fiction")) {
		  return "Fiction 1, Fiction 2, Fiction 3";
	  }
	  
	  if(genre.equalsIgnoreCase("Thriller")) {
		  return "Thriller 1, Thriller 2";
	  }

	  if(genre.equalsIgnoreCase("Computer")) {
		  return "Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt), The Phoenix Project";
	  }
	  
	  // "simulate a search on amazon"
	  executeURL("https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dstripbooks&field-keywords=AnyBook");
	 	  
	  	  
	  return "Random Book 1, Random Book 2, Random Book 3";
  }

  private String executeURL(String uri) {
	  try {
		  URL url = new URL(uri);
		  HttpURLConnection con = (HttpURLConnection) url.openConnection();
		  con.setRequestMethod("GET");
		  con.setRequestProperty("User-Agent", "MySpringBootApp");
		  con.getResponseCode();
		  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		  String inputLine;
		  StringBuffer response = new StringBuffer();
	
		  while ((inputLine = in.readLine()) != null) {
			  response.append(inputLine);
		  }
		  in.close();
		  
		  return response.toString();
	  }catch(Exception e) {
		  return e.getMessage();
	  }
  }
  
  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }
}