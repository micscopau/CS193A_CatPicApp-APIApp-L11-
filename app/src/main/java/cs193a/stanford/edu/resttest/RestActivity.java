/*
 * CS 193A, Marty Stepp
 * This program demonstrates REST APIs by fetching some data from two simple APIs,
 * the Internet Chuck Norris Database (ICNDb) and The Cat API.
 * We use the Ion library for downloading JSON/XML data from URLs, and
 * we use the Picasso library for fetching images from URLs.
 */

package cs193a.stanford.edu.resttest;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import stanford.androidlib.*;

public class RestActivity extends SimpleActivity {
    // auto-generated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
    }


    /*
     * The JSON data will be in the following format:
     *
     * {
     *   "response": {
     *     "data": {
     *         "images": {
     *             "image": [
     *                {"url":"http:\/\/24.media.tumblr.com\/tumblr_luw2y2MCum1qbdrypo1_500.jpg","id":"d40","source_url":"http:\/\/thecatapi.com\/?id=d40"},
     *                {"url":"http:\/\/25.media.tumblr.com\/tumblr_m4rwp4gkQ11r6jd7fo1_400.jpg","id":"e85","source_url":"http:\/\/thecatapi.com\/?id=e85"},
     *  ...
     * }
     */

    /*
     *
     * The JSON data uses the following format:
     *
     *  {
     *   "type": "success",
     *   "value": {
     *              "id": 496,
     *              "joke": "Chuck Norris went out of an infinite loop.",
     *              "categories": ["nerdy"]
     *            }
     *  }
     */

}
