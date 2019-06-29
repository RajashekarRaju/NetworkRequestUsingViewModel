package com.developersbreach.networkrequestusingviewmodel.data.network;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;


import com.developersbreach.networkrequestusingviewmodel.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    // Url to get posterPath to display into ImageView
    private static final String URL_POSTER_PATH = "http://image.tmdb.org/t/p/w500";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    public QueryUtils() {
    }

    /**
     * SCHEME_AUTHORITY = Encodes and sets the authority and scheme.
     * API_KEY = This is unique key given to the user for access from their server.
     * Obtain your API_KEY from TMDB site.
     */
    private static final String SCHEME_AUTHORITY = "https://api.themoviedb.org";
    private static final String APPEND_PATH_FIRST = "3";
    private static final String APPEND_PATH_SECOND = "movie";
    private static final String POPULAR_MOVIE_PATH = "popular";
    private static final String API_PARAM = "api_key";
    public static final String API_KEY = "YOUR_API_KEY";

    /**
     * Builds Uri used to fetch movie data from the server. This data is based on the query
     * capabilities of the movie database provider that we are using.
     * API_KEY is used to query specific data from the server
     * @return The String to use to query the movie database server.
     */
    public static String uriBuilder() {

        Uri baseUri = Uri.parse(SCHEME_AUTHORITY);
        // Constructs a new Builder.
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder
                .appendPath(APPEND_PATH_FIRST)
                .appendPath(APPEND_PATH_SECOND)
                .appendPath(POPULAR_MOVIE_PATH)
                .appendQueryParameter(API_PARAM, API_KEY);
        // Returns a string representation of the object.
        return uriBuilder.build().toString();
    }


    /**
     * Return a list of {@link Movie} objects that has been built up from parsing a JSON response.
     */
    public static List<Movie> extractMovieDataFromJson(String newJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding new to
        List<Movie> movieList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newJSON);
            // Extract the JSONArray associated with the key called "results",
            // which represents a list of features (or new).
            JSONArray newArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < newArray.length(); i++) {

                JSONObject jsonObject = newArray.getJSONObject(i);

                // Extract the value for the key called "original_title"
                String title = null;
                if (jsonObject.has("original_title")) {
                    title = jsonObject.getString("original_title");
                }

                // Extract the value for the key called "poster_path"
                String posterPath = null;
                if (jsonObject.has("poster_path")) {
                    posterPath = jsonObject.getString("poster_path");
                }

                // Appending posterPath value to URL_POSTER_PATH to fetch ImageView correctly
                String imagePosterPath = URL_POSTER_PATH + posterPath;

                // Create a new {@link Movie} object with the id, title, imagePosterPath from the
                // JSON response.
                Movie movie = new Movie(title, imagePosterPath);

                // Add the new {@link Movie} to the list of movies.
                movieList.add(movie);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the new JSON results", e);
        }
        // Return the list of Movies
        return movieList;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response, null if no response
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * Returns new URL object from the given string URL.
     */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
}
