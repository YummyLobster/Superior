package com.example.phoebee.superiorproject.utilities;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String NEWS_BASE_URL =
            "http://hansfurnitures.com/market.php";

    final static String PARAM_QUERY = "q";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";
    final static String sortBy = "latest";
    final static String PARAM_99ranch = "99ranch";
    final static String PARAM_HMart = "HMart";


    public static URL buildUrl(String searchQuery, String ranch99, String hmart) {
        Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, searchQuery)
                .appendQueryParameter(PARAM_99ranch, ranch99)
                .appendQueryParameter(PARAM_HMart, hmart)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
