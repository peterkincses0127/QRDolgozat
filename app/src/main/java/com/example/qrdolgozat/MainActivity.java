package com.example.qrdolgozat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
public class MainActivity extends AppCompatActivity {
    private TextView textResult;
    private Button btnScan, btnKiir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnScan.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            intentIntegrator.setPrompt("QR CODE SCAN");
            intentIntegrator.setCameraId(0);
            intentIntegrator.setBeepEnabled(false);
            intentIntegrator.setBarcodeImageEnabled(true);
            intentIntegrator.initiateScan();

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result =IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if (result != null){
            Toast.makeText(this, "Kilépés",Toast.LENGTH_SHORT).show();

        }else{
            textResult.setText("QR Code értéke:"+ result.getContents());

            Uri uri =Uri.parse(result.getContents());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        textResult = findViewById(R.id.textView);
        btnKiir = findViewById(R.id.kiir);
        btnScan = findViewById(R.id.scan);
    }
}