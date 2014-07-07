package info.androidhive.slidingmenu.model;
/*
 * Copyright (C) 2014 Francesco Azzola - Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Weather {

    public String imageUrl;

    public Condition condition = new Condition();
    public Wind wind = new Wind();
    public Atmosphere atmosphere = new Atmosphere();
    public Forecast forecast = new Forecast();
    public Location location = new Location();
    public Astronomy astronomy = new Astronomy();
    public Units units = new Units();

    public String lastUpdate;

    public class Condition {
        public  String description;
        public  int code;
        public  String date;
        public  int temp;
    }

    public  class Forecast {
        public  int tempMin;
        public  int tempMax;
        public  String description;
        public  int code;
    }

    public static class Atmosphere {
        public  int humidity;
        public  float visibility;
        public  float pressure;
        public  int rising;
    }

    public class Wind {
        public  int chill;
        public  int direction;
        public  int speed;
    }

    public class Units {
        public String speed;
        public String distance;
        public String pressure;
        public String temperature;
    }

    public class Location {
        public String name;
        public String region;
        public String country;
    }

    public class Astronomy {
        public String sunRise;
        public String sunSet;
    }
    
    
    String description;
    String city;
    String region;
    String country;

    String windChill;
    String windDirection;
    String windSpeed;

    String sunrise;
    String sunset;

    String conditiontext;
    String conditiondate;

    public String toString(){

     return "\n- " + description + " -\n\n"
      + "city: " + city + "\n"
      + "region: " + region + "\n"
      + "country: " + country + "\n\n"

      + "Wind\n"
      + "chill: " + windChill + "\n"
      + "direction: " + windDirection + "\n"
      + "speed: " + windSpeed + "\n\n"

      + "Sunrise: " + sunrise + "\n"
      + "Sunset: " + sunset + "\n\n"

      + "Condition: " + conditiontext + "\n"
      + conditiondate +"\n";
     
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWindChill() {
		return windChill;
	}

	public void setWindChill(String windChill) {
		this.windChill = windChill;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public String getConditiontext() {
		return conditiontext;
	}

	public void setConditiontext(String conditiontext) {
		this.conditiontext = conditiontext;
	}

	public String getConditiondate() {
		return conditiondate;
	}

	public void setConditiondate(String conditiondate) {
		this.conditiondate = conditiondate;
	}

}
