package com.example.seenkart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ErrorActivity extends AppCompatActivity {
TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        if(getIntent().getStringExtra("MSG")!=null)
        {
            tv=findViewById(R.id.tverror);
            tv.setText(getIntent().getStringExtra("MSG"));
        }
    }
    public void btnRetry_click(View v)
    {
        Redirect();

    }
    public void btnExit_click(View v)
    {
    finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Redirect();
    }

    private void Redirect()
    {
        if(Common.connectivityAvailable(ErrorActivity.this))
        {
            Intent home= new Intent(ErrorActivity.this,MainActivity.class);
            startActivity(home);
            finish();
        }
        else
            Toast.makeText(this, R.string.checkinternet, Toast.LENGTH_SHORT).show();
    }
}
