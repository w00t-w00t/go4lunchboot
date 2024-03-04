package com.openclassrooms.go4lunch.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.openclassrooms.go4lunch.model.bo.Lunch;
import com.openclassrooms.go4lunch.model.bo.Restaurant;
import com.openclassrooms.go4lunch.model.bo.Workmate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * LunchRepository is a Singleton class that provides methods to interact with the Lunch collection in Firestore
 */
public class LunchRepository {

    /**
     * The tag for the log messages
     */
    private static final String TAG = LunchRepository.class.getSimpleName();

    /**
     * The name of the collection in Firestore
     */
    private static final String COLLECTION_NAME = "lunch";

    /**
     * The path from the Lunch to the workmate id
     */
    private static final String LUNCH_WORKMATE_ID_FIELD = "workmates.idWorkmate";
    /**
     * The name of the field date lunch
     */
    private static final String LUNCH_DATE_LUNCH_FIELD = "dateLunch";
    /**
     * The name of the field in the document restaurantChosen
     */
    private static final String LUNCH_RESTAURANT_CHOSEN_NAME_FIELD = "restaurantChoosed.name";

    /**
     * Get the list of workmates who have already chosen a restaurant for today's lunch as LiveData
     */
    private final MutableLiveData<ArrayList<Workmate>> workmatesAlreadyChooseRestaurantForTodayLunch = new MutableLiveData<>();

    /**
     * Check if the current workmates has chosen a particular Restaurant for today
     */
    private final MutableLiveData<Boolean> thatRestaurantIsChosenForToday = new MutableLiveData<>();

    /**
     * Empty constructor
     */
    public LunchRepository() {
        super();
    }

    /**
     * Repository singleton
     */
    private static volatile LunchRepository instance;

    public static LunchRepository getInstance() {
        if (instance == null) {
            instance = new LunchRepository();
        }
        return instance;
    }

    /**
     * Get the Lunch collection
     */
    public static CollectionReference getLunchCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    /** Today */
    private static String toDay(){
        return Instant.now().truncatedTo(ChronoUnit.DAYS).toString();
    }

    /** Create a lunch */
    public void createLunch(Restaurant restaurantChoosed, Workmate workmate) {
        Lunch lunch = new Lunch(toDay(), restaurantChoosed, workmate);
        getLunchCollection().add(lunch);
    }

}
