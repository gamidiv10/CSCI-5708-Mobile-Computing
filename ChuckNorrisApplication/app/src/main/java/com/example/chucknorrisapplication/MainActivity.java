package com.example.chucknorrisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> jokesToBeRetrieved = new ArrayList<>();

    public String joke;
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the recycler view from layout file
        recyclerView = findViewById( R.id.recyclerview_card_layout );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //setting the layout created to display jokes to the recycler view in activity_main
        recyclerView.setLayoutManager( linearLayoutManager );

        try {

            String jokesRetrived = new RetrieveJokes().execute().get();
            JSONObject jsonData = new JSONObject(jokesRetrived);
            JSONArray jsonArrayForJokes = jsonData.getJSONArray("value");

            //converting json array to array list
            for (int i = 0; i < jsonArrayForJokes.length(); i++)
            {
                //steps to convert json elements to string to store them in array list
                String jokeJsonArrayElement = jsonArrayForJokes.getString(i);
                JSONObject jsonObjectForJoke = new JSONObject(jokeJsonArrayElement);
                joke = jsonObjectForJoke.getString("joke");

                //adding individual converted json array element to array list
                jokesToBeRetrieved.add(joke);
            }
        }

        //to catch the exceptions that we come across while converting json to array list
         catch (JSONException | MalformedURLException | ExecutionException | InterruptedException e)
         {
             e.printStackTrace();
         }

        //passing the array list to the recycler view
        recyclerViewAdapter = new RecyclerViewAdapter(jokesToBeRetrieved);

        //setting the adapter for recycler view
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private static class RetrieveJokes extends AsyncTask<String, Void, String>
    {

        URL url = new URL( "http://api.icndb.com/jokes/random/10");
        StringBuilder sb = new StringBuilder();

        private RetrieveJokes() throws MalformedURLException
        {

        }

        @Override
        protected String doInBackground(String... strings) {
            try
            {
                //establishing oonnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try
                {
                    //getting input stream after establishing connection
                    InputStream inputStream = new BufferedInputStream( urlConnection.getInputStream() );

                    byte[] buffer = new byte[1024];
                    int bytesRead = 0;
                    while ((bytesRead = inputStream.read(buffer)) != -1)
                    {
                        //adding jokes to the string builder
                        sb.append(new String(buffer, 0, bytesRead));
                    }
                }
                finally
                {
                    //disconnecting the connection
                    urlConnection.disconnect();

                }

            }
            catch (IOException e)
            {
                //to catch exceptions while establishing connection and retrieving data
                e.printStackTrace();
            }


            //returning the retrived jokes in json
            return sb.toString();
        }
    }
}