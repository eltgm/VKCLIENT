package com.androidjun.eltgm.facebookclientjun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class PhotosAdapter extends
        RecyclerView.Adapter<PhotosAdapter.PhotoHolder>{
    static class PhotoHolder extends
                            RecyclerView.ViewHolder{
        ImageView photo;
        PhotoHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

    private ArrayList<String> photos;
    PhotosAdapter(ArrayList<String> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_photo, parent, false);
        return new PhotoHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        URL newurl = null;
        try {
            newurl = new URL(photos.get(position));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert newurl != null;
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            holder.photo.setImageBitmap(mIcon_val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
//TODO сделать сохранение на устройство