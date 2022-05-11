package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Map {

    public Map(){

    }

    public void loadMap(){
    //JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/Map/map.json")) {
        //Read JSON file
        Object obj = jsonParser.parse(reader);

        JSONObject map = (JSONObject) obj;
        System.out.println(map);

        //Iterate over employee array
        parseMap(map);

    } catch (
    FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
}



    private static void parseMap(JSONObject map) {
        //Get employee object within list
        JSONArray mapData = (JSONArray) map.get("layers");
        Iterator<JSONArray> layers = mapData.iterator();

    }
}
