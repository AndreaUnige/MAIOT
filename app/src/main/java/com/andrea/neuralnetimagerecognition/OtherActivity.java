package com.andrea.neuralnetimagerecognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);

        String custom = getIntent().getStringExtra("test");
        Toast.makeText(getApplicationContext(), "Text: " + custom, Toast.LENGTH_LONG).show();


        Intent i = new Intent(getString(R.string.OTHER_ACTIVITY));
        i.putExtra("test", "back data!");
        setResult(Activity.RESULT_OK, i);
        finish();

    }
}
