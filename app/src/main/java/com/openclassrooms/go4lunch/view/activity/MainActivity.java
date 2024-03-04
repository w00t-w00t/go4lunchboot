package com.openclassrooms.go4lunch.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.go4lunch.R;
import com.openclassrooms.go4lunch.databinding.ActivityMainBinding;
import com.openclassrooms.go4lunch.model.bo.Restaurant;
import com.openclassrooms.go4lunch.model.bo.Workmate;
import com.openclassrooms.go4lunch.model.repository.LunchRepository;

/** MainActivity that includes core "tests" **/
public class MainActivity extends AppCompatActivity {

    /** View Binding **/
    private ActivityMainBinding binding;

    /**
     * LunchRepository
     */
    private final LunchRepository lunchRepository = LunchRepository.getInstance();

    /** Test Firebase Insert **/
    private void testFireStoreInsert(){

        Restaurant restaurant = new Restaurant("id", "monResto", "monPhone",  14.4f, "type", "url", "monWebsite", "monAddress","10:30");
        Workmate workmate = new Workmate("monNom", "monEmail", "monPhoto", "monId");

        lunchRepository.createLunch(restaurant, workmate);

    }

    /** Lifecycle **/
    @Override
    public void onResume() {
        super.onResume();
    }

    /** OnCreate **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Test FireStore
                testFireStoreInsert();

                // Display a message
                Snackbar.make(view, "Clicked!", Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab)
                    .setAction("Action", null).show();
            }
        });
    }

}