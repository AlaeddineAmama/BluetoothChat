package com.example.android.bluetoothchat;
import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionPage extends Activity implements View.OnClickListener{
    TextView connexionText ;
    TextView minimumtext;
    Button buttonInscreption ;
    DBHandler db ;
    Spinner spinnerDays,spinnerMonths,spinnerYears ;
    ArrayAdapter<CharSequence> adapterSpeenerDays,adapterSpeenerMonths,adapterSpeenerYears;
    EditText nameEditText,surnameEditText,emailEditText,passwordEditText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_page);
        connexionText = (TextView) findViewById(R.id.identifiez_vousText);
        minimumtext = (TextView) findViewById(R.id.textView5);
        connexionText.setOnClickListener(this);
        buttonInscreption = (Button) findViewById(R.id.inscriptionButton);
        buttonInscreption.setOnClickListener(this);


        spinnerDays=(Spinner) findViewById(R.id.spinnerDays);
        adapterSpeenerDays=ArrayAdapter.createFromResource(this,R.array.days,android.R.layout.simple_spinner_item) ;
        adapterSpeenerDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(adapterSpeenerDays);

        spinnerMonths=(Spinner) findViewById(R.id.spinnerMonths);
        adapterSpeenerMonths=ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item) ;
        adapterSpeenerMonths.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonths.setAdapter(adapterSpeenerMonths);

        spinnerYears=(Spinner) findViewById(R.id.spinnerYears);
        adapterSpeenerYears=ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item) ;
        adapterSpeenerYears.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYears.setAdapter(adapterSpeenerYears);

        db = new DBHandler(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.identifiez_vousText){
            onBackPressed();

        }
        else if(v.getId()==R.id.inscriptionButton){
            nameEditText=(EditText) findViewById(R.id.nameInput);
            surnameEditText=(EditText) findViewById(R.id.surnameInput);
            emailEditText=(EditText) findViewById(R.id.emailInput);
            passwordEditText=(EditText) findViewById(R.id.passwordInput);
            String name=nameEditText.getText().toString().trim();
            String surname=surnameEditText.getText().toString().trim();
            String email=emailEditText.getText().toString().trim();
            String password=passwordEditText.getText().toString().trim();


            if(checkDataInput(name,surname,email,password)){
                addDataInputToDB(name,surname,email,password);
            }
        }
    }
    public boolean  checkDataInput(String name,String surname,String email,String password)
    {
        if (surname.length()<3 || surname.length()>10 ||  isAlpha(surname)==false ){
            surnameEditText.setText("");
            surnameEditText.setHint("invalid FirstName");

            return false ;
        }
        else if (name.length()<3 || name.length()>10 ||isAlpha(name)==false )
        {   nameEditText.setText("");
            nameEditText.setHint("invalid name");
            return false ;
        }
        else if ( isEmail(email) == false )
        {   emailEditText.setText("");
            emailEditText.setHint("invalid e-mail");
            return false ;
        }
        else if(checkEmailIsExist(email)==true){
            emailEditText.setText("");
            emailEditText.setHint("e-mail already exists ");
            return false ;
        }
        else if (password.length()<6 || password.length()>15)
        {   passwordEditText.setText("");
            passwordEditText.setHint("invalid password");
            return false ;
        }
        else
        {
            return true ;
        }
    }



    public boolean isAlpha(String name)
    {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }


    public boolean isEmail(String email)
    {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if(mat.matches()){

            return true;
        }else{

            return  false;
        }
    }

    public void  addDataInputToDB(String name,String surname,String email,String password)
    {


         // Inserting Users/Rows
        Log.d("Insert: ", "Inserting ..");
        //  db.addUser(new Users(1, "Dockers", "Dockers", "Dockers", "Dockers"));
        db.addUser(new Users(name,surname,email,password));

/*
        Dialog dialog = new Dialog( this);
        dialog.setContentView(R.layout.activity_inscription_page);
        dialog.setTitle("Inserting Users/Rows");
        dialog.show();
*/


    }
    public boolean checkEmailIsExist(String email){
    Users usr=(Users)    db.getUserByEmail(email) ;
        if(usr !=null){
            return true ;
        }
        return false;
    }
}
