package com.andro.databasetask;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements DataAdapter.DataAdapterListener {

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    Dialog updateDataDialog;

    private List<DataObject> data_list = new ArrayList<DataObject>();
    Button btn_update;
    private Object DataObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView =  findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);

       data_list = databaseHelper.getAllUserData();//calling methode
        if(data_list != null && data_list.size()>0){

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            DataAdapter adapter = new DataAdapter(Main2Activity.this,data_list,this);
            recyclerView.setAdapter(adapter);

        }

    }

    @Override
    public void onClickAtUpdateButton(final DataObject data) {

        updateDataDialog = new Dialog(this);
        updateDataDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        updateDataDialog.setContentView(R.layout.dilog);
        updateDataDialog.setCanceledOnTouchOutside(false);//for avoid any cancle
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = updateDataDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        updateDataDialog.setCanceledOnTouchOutside(true);
        updateDataDialog.setCancelable(true);

        final EditText update_name = updateDataDialog.findViewById(R.id.update_name);
        final EditText update_city = updateDataDialog.findViewById(R.id.update_city);

        update_name.setText(data.getName());
        update_city.setText(data.getCity());
        updateDataDialog.show();



        btn_update = updateDataDialog.findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setName(update_name.getText().toString());
                data.setCity(update_city.getText().toString());
                databaseHelper.update(data);
                setAdapter();
                updateDataDialog.dismiss();                //for dialog dismiss
            }
        });
    }

    private void setAdapter() {
        data_list.clear();
        data_list = databaseHelper.getAllUserData();//calling methode

        DataAdapter adapter = new DataAdapter(Main2Activity.this,data_list,this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClickAtDeleteButton(DataObject data) {

        Log.d(String.valueOf(data),"show data");

        databaseHelper.delete(data);
        Toast.makeText(this, "Item is deleted", Toast.LENGTH_SHORT).show();
//        .......................for refreshing the list.......................
        data_list = databaseHelper.getAllUserData();//calling methode

        DataAdapter adapter = new DataAdapter(Main2Activity.this,data_list,this);
        recyclerView.setAdapter(adapter);

    }
}
