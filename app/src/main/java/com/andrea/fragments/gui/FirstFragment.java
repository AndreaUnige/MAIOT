package com.andrea.fragments.gui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrea.fragments.R;


public class FirstFragment extends Fragment {

    private final String TAG = "FirstFragment";
    private EditText etText;
    private Button btt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreateView");
        return inflater.inflate(R.layout.first_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, TAG + " onViewCreated");

        etText = view.findViewById(R.id.etFr1);
        btt = view.findViewById(R.id.bttFr1);

        btt.setOnClickListener( v -> {
            String t = etText.getText().toString();

            Bundle result = new Bundle();
            result.putString("dataKey", t);

            getParentFragmentManager().setFragmentResult("requestKey", result);

            // TO REMOVE THE FRAGMENT
            /*
            getParentFragmentManager().beginTransaction()
                .remove(this)
                .commit();
             */

            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SecondFragment())
                .addToBackStack(null)
                .commit();

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, TAG + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, TAG + " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, TAG + " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, TAG + " onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + " onDestroy");
    }

}
