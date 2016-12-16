package com.example.android.bluetoothchat;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.HorizontalScrollView;

import android.widget.ImageButton;

import java.io.InputStream;

public class MainRun extends Activity  implements  View.OnClickListener{
    TextView inscriptionClick;
    Intent intentInscription ;
    Intent intentChat;
    Button connectButton ;
    EditText emailInput;
    EditText passwordInput;
    Users usrResalt ;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_run);
       inscriptionClick = (TextView) findViewById(R.id.inscription_page_link);
       inscriptionClick.setOnClickListener(this);
       connectButton = (Button) findViewById(R.id.buttonConnect);
       emailInput= (EditText) findViewById(R.id.emailTextInput);
       passwordInput= (EditText) findViewById(R.id.passwordTextInput);
       connectButton.setOnClickListener(this);
       intentInscription = new Intent(this,InscriptionPage.class);
       intentChat = new Intent(this, MainActivity.class);

   }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.inscription_page_link){
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("/resources/image.jpg");

           startActivity(intentInscription);
        }
        else if (v.getId()==R.id.buttonConnect){

           if(authentificationUser(emailInput.getText().toString().trim(),passwordInput.getText().toString().trim())==true)
           {
                intentChat.putExtra("objectUser",usrResalt.toString());
                startActivity(intentChat);
            };
        }

/*
        if (v.getId()==R.id.likeButton){
            horizontalScrollViewEmoticon.setVisibility(View.VISIBLE);
        }
*/
    }

    public  boolean authentificationUser(String email,String password){

        DBHandler db = new DBHandler(this);
        usrResalt= (Users)db.getUserauthentification(email, password);
        if (usrResalt !=null){

            return  true ;
        }
        emailInput.setText("");
                emailInput.setHintTextColor(Color.RED);
                emailInput.setHint("Invalid Mail or Password");

        return  false;
    }

}
