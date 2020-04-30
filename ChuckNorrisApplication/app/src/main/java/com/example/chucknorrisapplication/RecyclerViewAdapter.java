package com.example.chucknorrisapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Vamsi Gamidi on 2019-11-29.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private ArrayList<String> jokesToBeDisplayed;


    //Constructor to initialize jokesToBeDisplayed list
    public RecyclerViewAdapter(ArrayList<String> jokesRetrieved) {
        this.jokesToBeDisplayed = jokesRetrieved;

    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Inflating the cardview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position)
    {
        //Inserting jokes into the view one by one
        holder.txtViewJoke.setText(jokesToBeDisplayed.get(position));
    }

    @Override
    public int getItemCount()
    {
        //using the size of the list to iterate the jokes in the recycler view
        return jokesToBeDisplayed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //Declaring variables for items in the card view
        TextView txtViewJoke;
        RelativeLayout cardViewLayout;

        //Constructor for ViewHolder class
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Initializing varibles with items in the card view
            txtViewJoke = itemView.findViewById(R.id.text_view_joke);
            cardViewLayout = itemView.findViewById(R.id.card_view_layout);
        }
    }
}
