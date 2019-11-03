package com.jason.registertester;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private EditText edEmail;
    private EditText edPassword;
    private ImageView error1;
    private ImageView error2;
    private String upper;
    private String include;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edName = findViewById(R.id.enter_one);
        edEmail = findViewById(R.id.enter_two);
        edPassword = findViewById(R.id.enter_three);
        error1 = findViewById(R.id.error_one);
        error2 = findViewById(R.id.error_two);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                restart();
            }
        });
    }

    public void finished(View view) {
        upper = "Thank you!";
        include = "Welcome. You have registered successfully!";
        error1.setVisibility(View.GONE);
        error2.setVisibility(View.GONE);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restart();
            }
        };
        if(edName.length() < 4 && edPassword.length() >= 6) {
            error1.setVisibility(View.VISIBLE);
            upper = "Warning!";
            include = "UserName must be at least 3 characters.";
            listener = null;
        } else if(edPassword.length() < 7 && edName.length() >= 3) {
            error2.setVisibility(View.VISIBLE);
            upper = "Warning!";
            include = "Password must be at least 6 characters.";
            listener = null;
        } else if(edName.length() < 4 && edPassword.length() < 7) {
            error1.setVisibility(View.VISIBLE);
            error2.setVisibility(View.VISIBLE);
            upper = "Warning!";
            include = "UserName must be at least 3 characters." + "\n" + "Password must be at least 6 characters.";
            listener = null;
        }
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(upper)
                .setMessage(include)
                .setPositiveButton("OK", listener)
                .show();
    }
    public void restart() {
        error1.setVisibility(View.GONE);
        error2.setVisibility(View.GONE);
        edName.setText("");
        edEmail.setText("");
        edPassword.setText("");
        Toast.makeText(MainActivity.this, "Restart a register.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
