package info.androidhive.slidingmenu.model;

public class Place {

	private String country;
	private String name;
	private String admin1;
	private String admin2;
	private String admin3;
	private String locality1;
	private String locality2;
	private String postal;
	private String woeID;
	private String timeZone;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAdmin1() {
		return admin1;
	}
	public void setAdmin1(String admin1) {
		this.admin1 = admin1;
	}
	public String getAdmin2() {
		return admin2;
	}
	public void setAdmin2(String admin2) {
		this.admin2 = admin2;
	}
	public String getAdmin3() {
		return admin3;
	}
	public void setAdmin3(String admin3) {
		this.admin3 = admin3;
	}
	public String getLocality1() {
		return locality1;
	}
	public void setLocality1(String locality1) {
		this.locality1 = locality1;
	}
	public String getLocality2() {
		return locality2;
	}
	public void setLocality2(String locality2) {
		this.locality2 = locality2;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getWoeID() {
		return woeID;
	}
	public void setWoeID(String woeID) {
		this.woeID = woeID;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "WoeID:"+getWoeID()+" country:"+getCountry()+" name:"+getName()+" timezone: "+getTimeZone();
	}
}
