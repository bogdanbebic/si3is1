/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Bogdan
 */
public class TerminCentar {
    public class Termin {
        private Date timeFrom;
        private Date timeTo;
        Termin(Date timeFrom, Date timeTo) {
            this.timeFrom = timeFrom;
            this.timeTo = timeTo;
        }

        public Date getTimeFrom() {
            return timeFrom;
        }

        public Date getTimeTo() {
            return timeTo;
        }

        @Override
        public String toString() {
            return "Termin{" + "timeFrom=" + timeFrom + ", timeTo=" + timeTo + '}';
        }
        
    }
    
    private static final String myRegionalniCentarParamName = "regionalniCentarId";
    private static final int myRegionalniCentar = 17011;
    
    private static final String baseUrlString = "http://collabnet.netset.rs:8081/is/terminCentar";
    
    private static final String availableTimeslotsUrl = "/getAvailableTimeslots";
    private static final String dateParamName = "dan";
    private static final String datePattern = "yyyy-MM-dd'T'HH:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    
    private static final String checkTimeslotUrl = "/checkTimeslotAvailability/";
    private static final String terminParamName = "termin";
    
    public List<Termin> getAvailableTimeslots(String day) {
        List<Termin> ret = new ArrayList<>();
        final String urlParams = "?" + myRegionalniCentarParamName 
                + "=" + myRegionalniCentar
                + "&" + dateParamName + "=" + day;
        
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(baseUrlString + availableTimeslotsUrl + urlParams).openConnection();
            if (httpConnection.getResponseCode() == 200) {
                JSONParser jsonParser = new JSONParser();
                JSONArray parsed = (JSONArray) jsonParser.parse(new InputStreamReader(httpConnection.getInputStream()));
                parsed.forEach(obj -> {
                    JSONObject jsonObj = (JSONObject)obj;
                    try {
                        ret.add(new Termin(dateFormat.parse(jsonObj.get("periodOd").toString()),
                                dateFormat.parse(jsonObj.get("periodDo").toString())));
                    } catch (ParseException ignored) {}
                });
            }
        } catch (Exception ignored) {}
        return ret;
    }
    
    public boolean isAvailableTimeslot(String timeslot) {
        final String urlParams = "?" + myRegionalniCentarParamName 
                + "=" + myRegionalniCentar
                + "&" + terminParamName + "=" + timeslot;
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(baseUrlString + checkTimeslotUrl + urlParams).openConnection();
            if (httpConnection.getResponseCode() == 200) {
                JSONParser jsonParser = new JSONParser();
                JSONObject parsed = (JSONObject) jsonParser.parse(new InputStreamReader(httpConnection.getInputStream()));
                return parsed.get("dostupnost").toString().equals("true");
            }
        } catch (Exception ignored) {}
        
        return false;
    }
}
