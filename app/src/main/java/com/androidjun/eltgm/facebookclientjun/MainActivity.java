package com.androidjun.eltgm.facebookclientjun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<JSONObject> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id,first_name,last_name,photo_200_orig,bdate"));

        RecyclerView rv = (RecyclerView) findViewById(R.id.user_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                JSONObject user;
                JSONArray userArr = null;
                try {
                    userArr = (JSONArray) ((JSONObject) (response.json).get("response")).get("items");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    int i = 0;
                    assert userArr != null;
                    while (userArr.get(i) != null) {
                        user = (JSONObject) userArr.get(i);
                        userList.add(user);
                        i++;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });

        UsersAdapter adapter = new UsersAdapter(userList, this);
        rv.setAdapter(adapter);
    }
}
