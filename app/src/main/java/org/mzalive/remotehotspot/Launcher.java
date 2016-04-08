package org.mzalive.remotehotspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Intent mainServiceIntent = new Intent(getApplicationContext(), MainSrv.class);
        getApplicationContext().startService(mainServiceIntent);
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        finish();
    }
}
