package com.dsi31g4.activit42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText passlg,emaillg;
Button bttlog;
DBaider DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passlg = findViewById(R.id.passlog);
        emaillg = findViewById(R.id.email_log);
        bttlog = findViewById(R.id.btnlogin);
        DB = new DBaider(this);
        bttlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = emaillg.getText().toString();
                String pass = passlg.getText().toString();
                if (user.equals("")|| pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean chekchmail = DB.checkmdp(user,pass);
                    if(chekchmail == true){
                        Toast.makeText(LoginActivity.this, "login in successfull", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "login in invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}