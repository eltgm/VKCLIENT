package com.androidjun.eltgm.facebookclientjun;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKPhotoArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileActivity extends Activity{

    ArrayList<String> photos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        final RecyclerView ph = (RecyclerView) findViewById(R.id.photos);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        ph.setLayoutManager(llm);

        int userID = getIntent().getIntExtra("id", 0);

        final VKRequest request = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.OWNER_ID, userID), VKPhotoArray.class);
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    JSONArray photosJSON =  (JSONArray) ((JSONObject) response.json.get("response")).get("items");
                    int i = 0;
                    while (i < 20) {
                        String photo_604 = ((JSONObject)photosJSON.get(i)).get("photo_604").toString();
                        photos.add(photo_604);
                        i++;
                    }
                    final PhotosAdapter adapter = new PhotosAdapter(getApplicationContext(), photos);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ph.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
