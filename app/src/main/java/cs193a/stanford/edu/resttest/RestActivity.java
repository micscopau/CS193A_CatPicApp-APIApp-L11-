/*
 * CS 193A, Marty Stepp
 * This program demonstrates REST APIs by fetching some data from two simple APIs,
 * the Internet Chuck Norris Database (ICNDb) and The Cat API.
 * We use the Ion library for downloading JSON/XML data from URLs, and
 * we use the Picasso library for fetching images from URLs.
 */

package cs193a.stanford.edu.resttest;

import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import stanford.androidlib.*;
import stanford.androidlib.xml.XML;

public class RestActivity extends SimpleActivity {
    // auto-generated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
    }

    public void chuckNorrisClick(View view){
        Ion.with(this)
                .load("http://api.icndb.com/jokes/random")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    //{ "type": "success", "value": { "id": 601, "joke": "Chuck Norris can remember the future.", "categories": [] } }
                    @Override
                    public void onCompleted(Exception e, String result) {
                        //data has arrived
                        try {
                            JSONObject json = new JSONObject(result);
                            JSONObject value = json.getJSONObject("value");
                            String joke = value.getString("joke");

                            $TV(R.id.output).setText(joke);

                        } catch(JSONException jsone){
                            Log.wtf("help",jsone);
                        }
                    }
                });
    }

    public void catClick(View view){
        GridLayout grid = $(R.id.grid);
        grid.removeAllViews();

        Ion.with(this)
                .load("http://thecatapi.com/api/images/get?format=xml&results_per_page=6")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        //data has arrived
                        try {
                            JSONObject json = XML.toJSONObject(result);
                            JSONArray image = json.getJSONObject("response")
                                    .getJSONObject("data")
                                    .getJSONObject("images")
                                    .getJSONArray("image");

                            for(int i = 0 ; i < image.length() ; i++){
                                JSONObject img = image.getJSONObject(i);
                                String url = img.getString("url");
                                //log(url);
                                loadImage(url);
                            }



                        } catch(JSONException jsone){
                            Log.wtf("help",jsone);
                        }
                    }
                });
    }

    public void loadImage(String url){
        ImageView imgView = new ImageView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        imgView.setLayoutParams(params);

        GridLayout grid = $(R.id.grid);
        grid.addView(imgView);

        Picasso.with(this)
                .load(url)
                .resize(400,400)
                .into(imgView);
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
