
package com.example.recipeapplab4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.Scanner;

import android.widget.TextView;

import androidx.fragment.app.Fragment;
public class RecipeDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_recipe_details, container, false);

        Intent intent = getActivity().getIntent();
        int recipeIndex = intent.getIntExtra("recipeIndex", -1);

        Log.d("RecipeApp", "Recipe Index Selected: " + recipeIndex);

        try{
            Scanner scanner = new Scanner(getActivity().getAssets().open("recipes.json"));

            String jsonText = "";

            while(scanner.hasNextLine()){
                jsonText = jsonText+scanner.nextLine();
            }
            scanner.close();

            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray recipes = jsonObject.getJSONArray("recipes");

            JSONObject recipe = recipes.getJSONObject(recipeIndex);
            TextView recipeName = view.findViewById(R.id.recipeName);
            recipeName.setText(recipe.getString("name"));

            TextView recipeIngredients = view.findViewById(R.id.recipeIngredients);
            JSONArray ingredients = recipe.getJSONArray("ingredients");
            String ingredientsText ="";

            for(int i=0; i<ingredients.length();i++){
                ingredientsText = ingredientsText + ingredients.getString(i)+ "\n";
            }
            recipeIngredients.setText(ingredientsText);

            TextView recipeMethod = view.findViewById(R.id.recipeMethod);
            recipeMethod.setText(recipe.getString("method"));


            Log.d("RecipeApp","Recipe name: " + recipe.getString("name"));
        }catch(Exception e){
            Log.e("RecipeApp", "Error loading the recipe",e);
        }

        return view;

    }

}
