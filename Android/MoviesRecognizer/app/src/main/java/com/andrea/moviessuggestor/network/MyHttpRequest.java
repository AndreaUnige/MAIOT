package com.andrea.moviessuggestor.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.andrea.moviessuggestor.interfaces.IRequestStatus;
import com.andrea.moviessuggestor.misc.Constants;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


import java.nio.charset.StandardCharsets;

public class MyHttpRequest {

    private final String TAG = "MyHttpRequest";

    private Context context;

    private RequestQueue requestQueue;
    private StringRequest request;
    private IRequestStatus iRequestStatus;

    private int networkResponseCode;

    private final int HTTP__OK = 200;


    public MyHttpRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(this.context);
        this.iRequestStatus = (IRequestStatus) context;
    }

    public void doRequest(JSONObject body) {
        new Thread(() -> {
            createRequest(body);
            requestQueue.add(request);
        }).start();
    }

    private void createRequest(JSONObject body) {
        request = new StringRequest(Request.Method.POST, Constants.getServerFullUrl(),
                response -> {
                     if (networkResponseCode != HTTP__OK)
                         return;

                    iRequestStatus.onResultAvailable(response);
                },

                error -> {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onErrorResponse: " + error.toString());
                })

        // Anonymous class
        {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                networkResponseCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() { return body.toString().getBytes(StandardCharsets.UTF_8); }
        };
    }


}
