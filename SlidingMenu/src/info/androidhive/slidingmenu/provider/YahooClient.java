package info.androidhive.slidingmenu.provider;

import info.androidhive.slidingmenu.model.CityResult;
import info.androidhive.slidingmenu.model.Weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
public class YahooClient {
    public static String YAHOO_GEO_URL = "http://where.yahooapis.com/v1";
    public static String YAHOO_WEATHER_URL = "http://weather.yahooapis.com/forecastrss";

    private static String APPID = "ptuazgTV34GNyUzhFWdFZyxcCQuMaPafb5F8aPvgXcocjAqCG7imJ9JzI4bbTH8k6XiQ3jY";

    public static List<CityResult> getCityList(String cityName) {
        List<CityResult> result = new ArrayList<CityResult>();
        HttpURLConnection yahooHttpConn = null;
        try {
            String query =makeQueryCityURL(cityName);
            Log.d("Swa", "URL [" + query + "]");
            yahooHttpConn= (HttpURLConnection) (new URL(query)).openConnection();
            Log.d("Swa", "XML Parser ok 0");
            yahooHttpConn.connect();
            Log.d("Swa", "XML Parser ok 1");
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            Log.d("Swa", "XML Parser ok 2");
            parser.setInput(new InputStreamReader(yahooHttpConn.getInputStream()));
            Log.d("Swa", "XML Parser ok 3");
            int event = parser.getEventType();

            CityResult cty = null;
            String tagName = null;
            String currentTag = null;

            // We start parsing the XML
            while (event != XmlPullParser.END_DOCUMENT) {
                tagName = parser.getName();

                if (event == XmlPullParser.START_TAG) {
                   if (tagName.equals("place")) {
                      // place Tag Found so we create a new CityResult
                      cty = new CityResult();
                     //  Log.d("Swa", "New City found");
                   }
                    currentTag = tagName;
                   // Log.d("Swa", "Tag ["+tagName+"]");
                }
                else if (event == XmlPullParser.TEXT) {
                    // We found some text. let's see the tagName to know the tag related to the text
                    if ("woeid".equals(currentTag))
                        cty.setWoeid(parser.getText());
                    else if ("name".equals(currentTag))
                        cty.setCityName(parser.getText());
                    else if ("country".equals(currentTag))
                        cty.setCountry(parser.getText());

                    // We don't want to analyze other tag at the moment
                }
                else if (event == XmlPullParser.END_TAG) {
                    if ("place".equals(tagName))
                        result.add(cty);
                }

                event = parser.next();
            }
        }
        catch(Throwable t) {
            t.printStackTrace();
            Log.e("Swa", "Error in getCityList "+t.getMessage());
        }
        finally {
            try {
              yahooHttpConn.disconnect();
            }
            catch(Throwable ignore) {
            	Log.e("Swa","Error in getCityList in finally block  "+ ignore.getMessage());
            }

        }
        return result;
    }

    public static void getWeather(String woeid, String unit, RequestQueue rq, final WeatherClientListener listener) {
        String url2Call = makeWeatherURL(woeid, unit);
       // Log.d("SwA", "Weather URL ["+url2Call+"]");
        final Weather result = new Weather();
        StringRequest req = new StringRequest(Request.Method.GET, url2Call, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                parseResponse(s, result);
                listener.onWeatherResponse(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        rq.add(req);
    }

    private static Weather parseResponse (String resp, Weather result) {
       // Log.d("SwA", "Response ["+resp+"]");
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(new StringReader(resp));

            String tagName = null;
            String currentTag = null;

            int event = parser.getEventType();
            boolean isFirstDayForecast = true;
            while (event != XmlPullParser.END_DOCUMENT) {
                tagName = parser.getName();

                if (event == XmlPullParser.START_TAG) {
                    if (tagName.equals("yweather:wind")) {
                       // Log.d("SwA", "Tag [Wind]");
                        result.wind.chill = Integer.parseInt(parser.getAttributeValue(null, "chill"));
                        result.wind.direction = Integer.parseInt(parser.getAttributeValue(null, "direction"));
                        result.wind.speed  = (int) Float.parseFloat(parser.getAttributeValue(null, "speed"));
                    }
                    else if (tagName.equals("yweather:atmosphere")) {
                       // Log.d("SwA", "Tag [Atmos]");
                        result.atmosphere.humidity = Integer.parseInt(parser.getAttributeValue(null, "humidity"));
                        result.atmosphere.visibility = Float.parseFloat(parser.getAttributeValue(null, "visibility"));
                        result.atmosphere.pressure = Float.parseFloat(parser.getAttributeValue(null, "pressure"));
                        result.atmosphere.rising = Integer.parseInt(parser.getAttributeValue(null, "rising"));
                    }
                    else if (tagName.equals("yweather:forecast")) {
                      //  Log.d("SwA", "Tag [Fore]");
                        if (isFirstDayForecast) {
                            result.forecast.code = Integer.parseInt(parser.getAttributeValue(null, "code"));
                            result.forecast.tempMin = Integer.parseInt(parser.getAttributeValue(null, "low"));
                            result.forecast.tempMax = Integer.parseInt(parser.getAttributeValue(null, "high"));
                            isFirstDayForecast = false;
                        }
                    }
                    else if (tagName.equals("yweather:condition")) {
                      //  Log.d("SwA", "Tag [Condition]");
                        result.condition.code = Integer.parseInt(parser.getAttributeValue(null, "code"));
                        result.condition.description = parser.getAttributeValue(null, "text");
                        result.condition.temp = Integer.parseInt(parser.getAttributeValue(null, "temp"));
                        result.condition.date = parser.getAttributeValue(null, "date");
                    }
                    else if (tagName.equals("yweather:units")) {
                     //   Log.d("SwA", "Tag [units]");
                        result.units.temperature = "°" + parser.getAttributeValue(null, "temperature");
                        result.units.pressure = parser.getAttributeValue(null, "pressure");
                        result.units.distance = parser.getAttributeValue(null, "distance");
                        result.units.speed = parser.getAttributeValue(null, "speed");
                    }
                    else if (tagName.equals("yweather:location")) {
                        result.location.name = parser.getAttributeValue(null, "city");
                        result.location.region = parser.getAttributeValue(null, "region");
                        result.location.country = parser.getAttributeValue(null, "country");
                    }
                    else if (tagName.equals("image"))
                        currentTag = "image";
                    else if (tagName.equals("url")) {
                        if (currentTag == null) {
                            result.imageUrl = parser.getAttributeValue(null, "src");
                        }
                    }
                    else if (tagName.equals("lastBuildDate")) {
                       currentTag="update";
                    }
                    else if (tagName.equals("yweather:astronomy")) {
                        result.astronomy.sunRise = parser.getAttributeValue(null, "sunrise");
                        result.astronomy.sunSet = parser.getAttributeValue(null, "sunset");
                    }

                }
                else if (event == XmlPullParser.END_TAG) {
                    if ("image".equals(currentTag)) {
                       currentTag = null;
                    }
                }
                else if (event == XmlPullParser.TEXT) {
                    if ("update".equals(currentTag))
                        result.lastUpdate = parser.getText();
                }
                event = parser.next();
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return result;
    }
    
    /*public static List getYahooWeather(){
    	
    	InputStream inputStream = null;
        try {
            URL url = new URL("http://d28rh4a8wq0iu5.cloudfront.net/androidapps101/example-data/shopping.txt");
            inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            StringWriter stringWriter = new StringWriter();
            char[] buffer = new char[8192];
            // Yes this is an 'Unnecessary buffer' (=twice the memory movement) but Java Streams need an intermediate array
            // to bulk copy bytes& chars
            // Cant glue InputStreamReader directly to a StringWriter unless you want to move 1 char at a time :-(
            int count;
            while ((count = inputStreamReader.read(buffer, 0, buffer.length)) != -1) {
                stringWriter.write(buffer, 0, count);
            }
            JSONArray jsonArray = new JSONArray(stringWriter.toString());
            List<Item> result = new ArrayList<Item>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Item item = new Item(jsonArray.getJSONObject(i));
                result.add(item);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }*/

    private static String makeQueryCityURL(String cityName) {
        // We remove spaces in cityName
        cityName = cityName.replaceAll(" ", "%20");
        return YAHOO_GEO_URL + "/places.q(" + cityName + "%2A);count=" + Config.MAX_CITY_RESULT + "?appid=" + APPID;
    }

    private static String makeWeatherURL(String woeid, String unit) {
        return  YAHOO_WEATHER_URL + "?w=" + woeid + "&u=" + unit;
    }


    /* Pubblic listener interface */

    public static interface WeatherClientListener {
        public void onWeatherResponse(Weather weather);
    }
}
