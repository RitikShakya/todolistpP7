package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText itemdata1;
    Button addbtn1;
    ListView listView;

    ArrayList<String> arrayList = new ArrayList<>();

    ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemdata1= findViewById(R.id.itemdata);
        addbtn1 = findViewById(R.id.addbtn);

        listView = findViewById(R.id.listitem);



        arrayList = FIleHelper.readData(this);


        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,arrayList);
        listView.setAdapter(adapter);

        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item = itemdata1.getText().toString();

                arrayList.add(item);
                itemdata1.setText(" ");
                FIleHelper.writeData(arrayList,getApplicationContext());
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int p, long l) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete");
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrayList.remove(p);
                        adapter.notifyDataSetChanged();
                        FIleHelper.writeData(arrayList,getApplicationContext());

                        Toast.makeText(getApplicationContext(), "Item Deleted" , Toast.LENGTH_SHORT).show();

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }
}