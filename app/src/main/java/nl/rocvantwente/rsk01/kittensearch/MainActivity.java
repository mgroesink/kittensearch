package nl.rocvantwente.rsk01.kittensearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likesCount";

    private RequestQueue mRequestQueue;
    private ArrayList<ExampleItem> mExampleItems;
    private ExampleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mSearch;
    private ImageButton mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearch = findViewById(R.id.editTextSearch);
        mSearchButton = findViewById(R.id.imageSearchButton);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseJson(mSearch.getText().toString());

            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the previous search
                ((EditText)view).setText("");
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleItems = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJson("kitten");
    }

    private void parseJson(String search) {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=" + search + "&image_type=photo&pretty=true";
//        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=dogs&image_type=photo&pretty=true";
        mExampleItems.clear();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        ExampleItem item = new ExampleItem(
                                hit.getString("webformatURL"), hit.getString("user"),
                                hit.getInt("likes"));

                        mExampleItems.add(item);

                    }
                    mAdapter = new ExampleAdapter(getApplicationContext(), mExampleItems);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        mRequestQueue.add((request));
    }

    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem item = mExampleItems.get(position);
        detailIntent.putExtra(EXTRA_URL, item.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, item.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES, item.getmLikes());
        startActivity(detailIntent);
    }
}
