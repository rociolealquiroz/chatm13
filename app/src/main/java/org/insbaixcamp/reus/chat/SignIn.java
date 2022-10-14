package org.insbaixcamp.reus.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        EditText etUser = findViewById(R.id.etUsername);
        Button update = findViewById(R.id.bUpdateUsername);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        etUser.setText(sharedPref.getString(getResources().getString(R.string.preference_user_name),"anonymous"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.preference_user_name), etUser.getText().toString());
                editor.apply();
            }
        });

    }



}