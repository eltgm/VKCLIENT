package com.androidjun.eltgm.facebookclientjun;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class UsersAdapter extends
        RecyclerView.Adapter<UsersAdapter.PersonViewHolder>{
    static class PersonViewHolder extends
            RecyclerView.ViewHolder{
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;


        PersonViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }

    private ArrayList<JSONObject> userList;
    private Context context;

    UsersAdapter(ArrayList<JSONObject> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        try {
            holder.personName.setText(userList.get(position).get("first_name") + " " + userList.get(position).get("last_name"));
            Picasso.with(context)
                    .load(userList.get(position).get("photo_100").toString())
                    .into(holder.personPhoto);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
