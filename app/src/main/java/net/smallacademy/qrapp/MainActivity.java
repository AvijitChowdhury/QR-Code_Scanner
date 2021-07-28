package net.smallacademy.qrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    EditText qrvalue;
    Button generateBtn,scanBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrvalue = findViewById(R.id.qrInput);
        generateBtn = findViewById(R.id.generateBtn);
        scanBtn = findViewById(R.id.scanBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = qrvalue.getText().toString();
                if(data.isEmpty()){
                    qrvalue.setError("Value Required.");
                }else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,500);
                    try {
                        Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(qrBits);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Scanner.class));
            }
        });


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case
                    R.id.about:
                Toast.makeText(MainActivity.this,"This is a QR and Bar Code Scanner App ",Toast.LENGTH_SHORT).show();
                break;
            case
                    R.id.developer:
                startActivity(new Intent(MainActivity.this,developer.class));

                break;

        }

        return true;
    }
}
