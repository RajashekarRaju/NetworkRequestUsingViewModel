# NetworkRequestUsingViewModel
This repository shows an example on how to fetch json data and show in list using android architecture components. Components such as LiveData, ViewModel and app using repository class to query and fetch data. Room database is not used in this example as we don't want to make viewing offline, the main goal is to show how these classes communicate each other and activity doesn't lose it's list-state with proper ViewModel implementations.

Check out my other repository for app using Room database implementation.
https://github.com/RajashekarRaju/AndroidArchitectureComponents

Get your own api key from TMDB site. Add the missing key in QueryUtils.java class under utils package.

private static final String API_KEY = "YOUR_API_KEY";
