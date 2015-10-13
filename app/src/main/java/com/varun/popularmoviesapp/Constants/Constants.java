package com.varun.popularmoviesapp.Constants;

import java.util.List;

/**
 * Created by VarunBarve on 10/8/2015.
 */
public class Constants {

    public static final String TAG = "POPULAR MOVIES APP";

    // API KEY
    public static final String API_KEY = "5647682c900f2d72be9d398c9c79dda7";

    // CONFIGURATION
    public static final String CONFIGURATION_URL = "http://api.themoviedb.org/3/configuration";

    // CONFIGURATION KEYS
    public static final String IMAGES_KEY = "images";
    public static final String CHANGE_KEYS_KEY = "change_keys";
    public static final String BASE_URL_KEY = "base_url";
    public static final String SECURE_BASE_URL_KEY = "secure_base_url";
    public static final String BACKDROP_SIZES_KEY = "backdrop_sizes";
    public static final String LOGO_SIZES_KEY = "logo_sizes";
    public static final String POSTER_SIZES_KEY = "poster_sizes";
    public static final String PROFILE_SIZES_KEY = "profile_sizes";
    public static final String STILL_SIZES_KEY = "still_sizes";

    // CONFIGURATION VARIABLE STORAGE
    public static String BASE_URL = "";
    public static String SECURE_BASE_URL = "";
    public static List<String> BACKDROP_SIZES;
    public static List<String> LOGO_SIZES;
    public static List<String> POSTER_SIZES;
    public static List<String> PROFILE_SIZES;
    public static List<String> STILL_SIZES;

    // POPULAR MOVIES
    public static final String POPULAR_MOVIES_URL = "http://api.themoviedb.org/3/movie/popular";
}
