package com.example.phoebee.superiorproject.utilities;

import com.example.phoebee.superiorproject.model.Markets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONUtils {

    public static ArrayList<Markets> makeRepositoryList(String jsonResult) {
        ArrayList<Markets> repoList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonResult);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString("name");
                Long price = object.getLong("price");
                String category = object.getString("Category");
                String image = object.getString("image");
                String market = object.getString("market");
                repoList.add(new Markets(name, price, category, image, market));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repoList;
    }
}