package info.androidhive.slidingmenu.util;

import info.androidhive.slidingmenu.model.Place;
import info.androidhive.slidingmenu.model.Weather;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class YahooParser {
	

	public static Weather parseWeather(Document srcDoc) {

		Weather myWeather = new Weather();

		// <description>Yahoo! Weather for New York, NY</description>
		myWeather.setDescription(srcDoc.getElementsByTagName("description")
				.item(0).getTextContent());

		// <yweather:location city="New York" region="NY"
		// country="United States"/>
		Node locationNode = srcDoc.getElementsByTagName("yweather:location")
				.item(0);
		myWeather.setCity(locationNode.getAttributes().getNamedItem("city")
				.getNodeValue().toString());
		myWeather.setRegion(locationNode.getAttributes().getNamedItem("region")
				.getNodeValue().toString());
		myWeather.setCountry(locationNode.getAttributes()
				.getNamedItem("country").getNodeValue().toString());

		// <yweather:wind chill="60" direction="0" speed="0"/>
		Node windNode = srcDoc.getElementsByTagName("yweather:wind").item(0);
		myWeather.setWindChill(windNode.getAttributes().getNamedItem("chill")
				.getNodeValue().toString());
		myWeather.setWindDirection(windNode.getAttributes()
				.getNamedItem("direction").getNodeValue().toString());
		myWeather.setWindSpeed(windNode.getAttributes().getNamedItem("speed")
				.getNodeValue().toString());

		// <yweather:astronomy sunrise="6:52 am" sunset="7:10 pm"/>
		Node astronomyNode = srcDoc.getElementsByTagName("yweather:astronomy")
				.item(0);
		myWeather.setSunrise(astronomyNode.getAttributes()
				.getNamedItem("sunrise").getNodeValue().toString());
		myWeather.setSunset(astronomyNode.getAttributes().getNamedItem("sunset")
				.getNodeValue().toString());

		// <yweather:condition text="Fair" code="33" temp="60"
		// date="Fri, 23 Mar 2012 8:49 pm EDT"/>
		Node conditionNode = srcDoc.getElementsByTagName("yweather:condition")
				.item(0);
		myWeather.setConditiontext(conditionNode.getAttributes()
				.getNamedItem("text").getNodeValue().toString());
		myWeather.setConditiondate(conditionNode.getAttributes()
				.getNamedItem("date").getNodeValue().toString());

		return myWeather;
	}
	
	public static Place parsePlaces(Document srcDoc){
		
		List<Place> placeList=new ArrayList<Place>();
		
		// <description>Yahoo! Weather for New York, NY</description>
		NodeList nodeListPlace=srcDoc.getElementsByTagName("place");
		for(int i=0;i<nodeListPlace.getLength();i++){
			Place place=new Place();
			Node placeNode=nodeListPlace.item(i);
			Log.d("Swa","placenode name:"+placeNode.getNodeName());
			NodeList nodeListPlaceElements=placeNode.getChildNodes();
			Log.d("Swa","nodelistplaceelement size:"+nodeListPlaceElements.getLength());
			for(int j=0;j<nodeListPlaceElements.getLength();j++){
				Log.d("Swa","node name:"+nodeListPlaceElements.item(j).getNodeName().toString());
			}
			place.setWoeID(nodeListPlaceElements.item(0).getTextContent());
			place.setName(nodeListPlaceElements.item(2).getTextContent());
			place.setCountry(nodeListPlaceElements.item(3).getTextContent());
		
			place.setAdmin1(nodeListPlaceElements.item(4).getTextContent());
			place.setTimeZone(nodeListPlaceElements.item(14).getTextContent());
			Log.d("Swa", "place is:"+place.toString());
			placeList.add(place);
		}
		return placeList.get(0);
	}
}
