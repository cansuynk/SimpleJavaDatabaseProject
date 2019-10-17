package deneme;

public class Movie {

	private int year, length, popularity;
	private String title, subject, award;
	private String actor, actress, director;

	
	public Movie(int year, int length, int popularity, String title, String subject, String award, String actor, String actress, String director){
		this.year = year;
		this.length = length;
		this.title = title;
		this.subject = subject;
		this.actor = actor;
		this.actress = actress;
		this.director = director;
		this.popularity = popularity;
		this.award = award;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getPopularity() {
		return popularity;
	}


	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getAward() {
		return award;
	}


	public void setAward(String award) {
		this.award = award;
	}


	public String getActor() {
		return actor;
	}


	public void setActor(String actor) {
		this.actor = actor;
	}


	public String getActress() {
		return actress;
	}


	public void setActress(String actress) {
		this.actress = actress;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}
	
}
