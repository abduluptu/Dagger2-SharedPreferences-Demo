package com.sohainfotech.dagger2sharedpreferencesdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sohainfotech.dagger2sharedpreferencesdemo.R;
import com.sohainfotech.dagger2sharedpreferencesdemo.component.DaggerMyComponent;
import com.sohainfotech.dagger2sharedpreferencesdemo.component.MyComponent;
import com.sohainfotech.dagger2sharedpreferencesdemo.module.SharedPrefModule;

import javax.inject.Inject;

//Step: 4

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername, etNumber;
    private Button btnSave, btnGet;

    //get dependency injection
    private MyComponent myComponent;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //use dependency injection
        /**
         * Dagger keyword is prepended on the Component class name.
         * If the component class name was AppComponent, the result would have been DaggerAppComponent.
         * Note: At this stage Dagger can generate files,
         * so you need to Rebuild your project to allow it to do its job (Build > Rebuild Project).
         */
        myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        /**
         * The SharedPreferences dependency object is available to use after this:
         */
        myComponent.inject(this);
    }

    private void initViews() {
        btnGet = findViewById(R.id.btnGet);
        btnSave = findViewById(R.id.btnSave);
        etUsername = findViewById(R.id.etUsername);
        etNumber = findViewById(R.id.etNumber);
        btnSave.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSave:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", etUsername.getText().toString().trim());
                editor.putString("number", etNumber.getText().toString().trim());
                editor.apply();

                clearText();
                break;
            case R.id.btnGet:
                etUsername.setText(sharedPreferences.getString("userName", "default"));
                etNumber.setText(sharedPreferences.getString("number", "9876543210"));
                break;
        }

    }

    private void clearText(){
        etUsername.setText("");
        etNumber.setText("");
    }
}