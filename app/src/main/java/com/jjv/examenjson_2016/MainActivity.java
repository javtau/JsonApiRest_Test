package com.jjv.examenjson_2016.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jjv.examenjson_2016.R;

public class MainActivity extends AppCompatActivity {
    final static  String CODIGO = "codigo";
    EditText txt_codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_codigo = (EditText) findViewById(R.id.txt_codigo);

    }

    public void consultar(View v){
        Intent i = new Intent(this,InfoActorActivity.class);
        i.putExtra(CODIGO,txt_codigo.getText().toString());
        startActivity(i);
    }
}
