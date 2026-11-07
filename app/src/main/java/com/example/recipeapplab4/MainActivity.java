package com.example.recipeapplab4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, new RecipeListFragment());
        transaction.commit();

        if(findViewById(R.id.details_container) != null){
            FragmentTransaction detailsTransaction = fragmentManager.beginTransaction();

            detailsTransaction.replace(R.id.details_container,new RecipeDetailsFragment());
            detailsTransaction.commit();
        }
    }

    public void showRecipe(int index){
        RecipeDetailsFragment detailsFragment = new RecipeDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("recipeIndex", index);
        detailsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.details_container, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}