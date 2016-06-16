package com.example.earlybirdcamp.EarlyBirdApp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.util.Log;

public class TaskActivity extends AppCompatActivity{
    private String user;
    private static final String TAG = "TaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        // this is how The Dialog is gonna look
                        .setView(R.layout.special_dialog)
                         // set the functionality of a button/ what the button is gonna be user for
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Dialog dialog = (Dialog) dialogInterface;
                                Spinner spin = (Spinner) dialog.findViewById(R.id.priority_spinner);

                                // we need these values for later use: Low, Medium, High
                                String[]  array_name = getResources().getStringArray(R.array.priority_array);

                                /*
                                ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(TaskActivity.this, android.R.layout.simple_spinner_item, array_name);
                                adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin.setAdapter(adapter_state);*/

                                spin.setOnItemSelectedListener(new OnItemSelectedListener() {

                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View selectedItemView,
                                                               int pos, long id) {
                                        Toast.makeText(parent.getContext(),
                                                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
                                                Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> arg0) {
                                        //  Auto-generated method stub

                                    }

                                });
                                int select = spin.getSelectedItemPosition();
                                Toast.makeText(TaskActivity.this, "this is my Toast message!!! =) " + select, Toast.LENGTH_LONG).show();

                                //String text = spin.getSelectedItem().toString();

                                //String task = String.valueOf(taskEditText.getText());
                                //Log.d(TAG, "Task to add: " + task);
                                //This is where we log the task stuff added
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
