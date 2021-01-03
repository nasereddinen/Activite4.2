package com.dsi31g4.activit42;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nom, password, repassword;
    Button register, login;
    // instance de class de j'ai creer
    DBaider DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom = findViewById(R.id.NewName);
        password = findViewById(R.id.NewPassword);
        repassword = findViewById(R.id.REpass);
        register = findViewById(R.id.btnNewRegister);
        login = (Button) findViewById(R.id.btn_log);
        // on creer une instance de la clas DBhelper et pour moi(BD helper) s"appelle DB
        DB = new DBaider(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // je creer une intent appéler intent
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = nom.getText().toString();
                String s2 = password.getText().toString();
                String s3 = repassword.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals(s3)) {
                        Boolean checkmail = DB.checknom(s1);
                        //dans le video dans le DBhelper checkmail method il return false lorsque user.count<0
                        if (checkmail == false) {
                            Boolean insert = DB.insertData(s1, s2);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Email exist", Toast.LENGTH_SHORT).show();
                            }
                            // la derniere else n'exist pas dans le tutorial cet pour le password
                        } else {
                            Toast.makeText(MainActivity.this, "pass faild", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
            });
    }
}