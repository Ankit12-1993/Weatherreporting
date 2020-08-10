package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GetJKeyValueJSON 
{
	ArrayList<String> list = new ArrayList<String>();
	
 	public ArrayList<String> GetJKeyValueJSON2(String json, String keyTo) 
    {
        JsonParser p = new JsonParser();
        return check(keyTo, p.parse(json));
    }



    private ArrayList<String> check(String key, JsonElement jsonElement) 
    {

        if (jsonElement.isJsonArray()) 
        {
            for (JsonElement jsonElement1 : jsonElement.getAsJsonArray()) 
            {
                check(key, jsonElement1);
            }
        } 
        else 
        {
            if (jsonElement.isJsonObject()) 
            {
                Set<Map.Entry<String, JsonElement>> entrySet = jsonElement.getAsJsonObject().entrySet();
                
                for (Map.Entry<String, JsonElement> entry : entrySet) 
                {
                    String key1 = entry.getKey();
                    if (key1.equals(key)) 
                    {
                        list.add(entry.getValue().toString());
                    }
                    check(key, entry.getValue());
                }
            } 
            else 
            {
                if (jsonElement.toString().equals(key)) 
                {
                    list.add(jsonElement.toString());
                }
            }
        }return list;
    }
	
}
