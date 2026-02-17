package com.andrea.fragments.gui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrea.fragments.R;

public class SecondFragment extends Fragment {


    private final String TAG = "SecondFragment";
    private TextView tvFr2;
    private Button bttFr2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreateView");
        return inflater.inflate(R.layout.second_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, TAG + " onViewCreated");

        tvFr2 = view.findViewById(R.id.tvFr2);
        bttFr2 = view.findViewById(R.id.bttFr2);

        getParentFragmentManager().
                setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {

                    String result = bundle.getString("dataKey");
                    tvFr2.setText(result);
        });

        bttFr2.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FirstFragment())
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, TAG + " onSaveInstanceState");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + " onDestroy");
    }

}
