package com.example.android.movie.Networking;

/**
 * Created by Shimaa on 8/29/2016.
 */
public class Urls {
    public static final String API_KEY = "9d439968a128f2c3596337f5aaa636cc";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String TOP_RATED = BASE_URL + "movie/top_rated?api_key=" + API_KEY ;
    public static final String POPULAR = BASE_URL + "movie/popular?api_key=" + API_KEY ;
    public static final String NOW_PLAYING = BASE_URL + "movie/now_playing?api_key=" + API_KEY ;
    public static final String UPCOMING = BASE_URL + "movie/upcoming?api_key=" + API_KEY ;

    public static final String POPULAR_PEOPLE = BASE_URL + "person/popular?api_key=" + API_KEY;
    public static final String BASE_SEARCH_URL = BASE_URL + "search/movie?api_key=" + API_KEY + "&query=";

    public static final String SIMILAR_MOVIE_BASE_URL = BASE_URL + "movie/";
    public static  final  String SIMILAR_MOVIE_REM_URL = "/similar?api_key=" + API_KEY + "&id=";

    public static final String CAST_BASE_URL = BASE_URL + "movie/";
    public static  final  String CAST_REM_URL = "/credits?api_key=" + API_KEY + "&id=";


}
