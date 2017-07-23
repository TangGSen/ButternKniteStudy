package com.sen.butternkniteframwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.BindView;
import com.inject.InjectView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectView.bind(this);
    }
}
