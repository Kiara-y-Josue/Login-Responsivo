package com.example.loginresponsivo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity
                         implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void clickLogin(View view){

        Map<String, String> datos = new HashMap<String, String>();

        EditText txtCedula = findViewById(R.id.txtCedula);
        String usr= txtCedula.getText().toString();
        EditText txtPass = findViewById(R.id.txtClave);
        String pass = txtPass.getText().toString();
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?"+ "usr=" + usr + "&pass=" + pass,
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        Toast.makeText(this,result, Toast.LENGTH_LONG).show();
    }
}


