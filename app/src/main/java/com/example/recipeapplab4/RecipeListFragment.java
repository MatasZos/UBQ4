package com.example.recipeapplab4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;


public class RecipeListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        ListView list = view.findViewById(R.id.recipeListView);

        ArrayList<String> recipeNames = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(getActivity().getAssets().open("recipes.json"));

            String jsonText = "";

            while(scanner.hasNextLine()){
                jsonText = jsonText + scanner.nextLine();
            }

            scanner.close();

            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray recipes = jsonObject.getJSONArray("recipes");

            for(int i=0; i<recipes.length();i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                recipeNames.add(recipe.getString("name"));
            }
        }catch ( Exception e){
            Log.e("RecipeApp", "Error loading recipes", e);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,recipeNames);

        list.setAdapter(adapter);
        return view;

    }
}