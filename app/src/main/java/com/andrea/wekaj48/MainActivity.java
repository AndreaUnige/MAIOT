package com.andrea.wekaj48;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.andrea.wekaj48.J48.DecisionTree;
import com.andrea.wekaj48.interfaces.IMachineLearningAlgorithm;
import com.andrea.wekaj48.interfaces.IPrediction;

public class MainActivity extends AppCompatActivity implements IPrediction {

    private EditText etPetalWidth, etPetalLenght, etSepalWidth, etSepalLenght;
    private Button bttPrediction;
    private TextView tvPrediction;

    private float petalWidth, petalLenght, sepalWidth, sepalLenght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPetalWidth = findViewById(R.id.etPetalWidth);
        etPetalLenght = findViewById(R.id.etPetalWidth);
        etSepalWidth = findViewById(R.id.etSepalWidth);
        etSepalLenght = findViewById(R.id.etSepalLenght);

        bttPrediction = findViewById(R.id.bttPrediction);
        tvPrediction = findViewById(R.id.tvResult);

        IMachineLearningAlgorithm machineLearningAlgorithm = new DecisionTree(this);
        machineLearningAlgorithm.loadModel();


        bttPrediction.setOnClickListener( v -> {

            petalWidth = Float.parseFloat(etPetalWidth.getText().toString());
            petalLenght = Float.parseFloat(etPetalLenght.getText().toString());
            sepalWidth = Float.parseFloat(etSepalWidth.getText().toString());
            sepalLenght = Float.parseFloat(etSepalLenght.getText().toString());

            machineLearningAlgorithm.predict(
                    new double[] {petalWidth, petalLenght, sepalWidth, sepalLenght, 0}
            );

        });

    }

    @Override
    public void onPredictionAvailable(String prediction) {
        tvPrediction.setText("Flower: " + prediction);
    }
}