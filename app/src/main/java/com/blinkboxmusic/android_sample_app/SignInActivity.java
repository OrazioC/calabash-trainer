package com.blinkboxmusic.android_sample_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by orazio on 09/12/14.
 */
public class SignInActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
    }


    public void signIn(View view) {
        TextView emailAddressTextView = (TextView) findViewById(R.id.enter_username);
        TextView passwordTextView = (TextView) findViewById(R.id.enter_password);

        String username = emailAddressTextView.getText().toString();
        Log.d("Android Sample App", "MainActivity - signIn: username= " + username);
        String passwordText = passwordTextView.getText().toString();
        Log.d("Android Sample App", "MainActivity - signIn: password= " + passwordText);

        if (username.equalsIgnoreCase("bbm") && passwordText.equalsIgnoreCase("pass")) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, username);
            startActivity(intent);
        } else {

            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "";

            if (username.isEmpty() || passwordText.isEmpty()) {
                text = "Please type your username and password";
            } else {
                text = "Incorrect username or password";
            }

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }
    }
}
