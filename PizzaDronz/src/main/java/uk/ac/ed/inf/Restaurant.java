package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class representing the restaurant
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    private static final Restaurant[] INSTANCE = new Restaurant[];
    private String restaurantUrl = "https://ilp-rest.azurewebsites.net/restaurants";
    public static Restaurant[] getINSTANCE(){  return INSTANCE; }

    /**
     * The name and coordinate of the restaurant and an array of its pizzas available
     */
    private String name;

    private LngLat coordinate;

    private Menu[] menu;



    /**
     * Constructor of the drone,
     * values of its fields will be drawn from the REST server
     *
     * @param name the name of the restaurant
     * @param menu an array of available pizzas of the restaurant
     */
    public Restaurant(@JsonProperty("name")String name, @JsonProperty("longitude")double longitude, @JsonProperty("latitude")double latitude,@JsonProperty("menu")Menu[] menu) {
        this.name = name;
        this.coordinate = new LngLat(longitude, latitude);
        this.menu = menu;

    }


    /**
     * Read the JSON data from a URL and return an array of Restaurant objects including their corresponding menus
     *
     * @param serverBaseAddress the URL of the server to be extracted data with
     *
     * @return an array of Restaurant objects including their menus
     */

    public static Restaurant[] getRestaurantsFromRestServer(URL serverBaseAddress){
        Restaurant[] restaurants = null;

        // Parse the JSON data
        try {
            restaurants = new ObjectMapper().readValue(serverBaseAddress, Restaurant[].class);

        } catch (StreamReadException e){
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


        return restaurants;
    }

    // Getter and setter methods for private fields of restaurant class
    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(Menu[] menu) {
        this.menu = menu;
    }

    public String getName() {

        return this.name;
    }

    public Menu[] getMenu() {

        return this.menu;
    }

    public LngLat getCoordinate() {
        return coordinate;
    }

}