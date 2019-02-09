package com.example.lecture_sign_in_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private Button btnViewData;
    private EditText editText;
    private Spinner spiDur;
    private Spinner spiDate;
    private Spinner spiTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        spiDate = (Spinner) findViewById(R.id.spiDate);
        spiDur = (Spinner) findViewById(R.id.spiDur);
        spiTime = (Spinner) findViewById(R.id.spiTime);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);

        //taking in values
        btnAdd.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                String newEntry1 = editText.getText().toString();
                String newEntry2 = spiTime.getSelectedItem().toString();
                String newEntry3 = spiDate.getSelectedItem().toString();
                String newEntry4 = spiDur.getSelectedItem().toString();

                if (editText.length() !=0){
                    AddData(newEntry1, newEntry2, newEntry3, newEntry4);
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }


            }
        });

        btnViewData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);

            }

        });


        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                goToSecondActivity();

            }

        });
    }

    public void AddData(String newEntry1, String newEntry2, String newEntry3, String newEntry4){
        boolean insertData = mDatabaseHelper.addData(newEntry1, newEntry2, newEntry3, newEntry4);

        if(insertData){
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    private void goToSecondActivity() {

        Intent intent = new Intent(this, page2.class);

        startActivity(intent);

    }
}
