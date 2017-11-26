package com.example.k11.footballplus.Views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.k11.footballplus.Adapters.ListFieldSoccerAdapter;
import com.example.k11.footballplus.Helpers.SqliteHelper;
import com.example.k11.footballplus.LoginActivity;
import com.example.k11.footballplus.Models.CampFootball;
import com.example.k11.footballplus.R;

import java.util.ArrayList;
import java.util.List;

public class ListFieldSoccerActivity extends AppCompatActivity {
    private CheckBox checkboxFavoriteItemListFieldSoccer;
    private ImageView imagen;
    private Button BtnCommentItemListFieldSoccer,BtnReserveItemListFieldSoccer;




    RecyclerView recyclerViewContactsListFieldSoccer;
    ListFieldSoccerAdapter listFieldSoccerAdapter;
    List<CampFootball> campFootballList = new ArrayList<>();
    SqliteHelper sqliteHelper;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_field_soccer);


        recyclerViewContactsListFieldSoccer = (RecyclerView) findViewById(R.id.recyclerViewContactsListFieldSoccer);
        sqliteHelper = new SqliteHelper(this, "DB_CAMP_FOOTBALL", null, 1);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewContactsListFieldSoccer.setLayoutManager(linearLayoutManager);

        listContacts();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_field_soccer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuLookProfileListfieldSoccer) {

            Intent intent = new Intent(this, PerfilUserActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menuSignOffListfieldSoccer) {

//
// ############################### metodo para cerrar sesion  ###############################

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void listContacts(){
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CAMP  ORDER BY ID ASC", null);

        while (cursor.moveToNext()){
            CampFootball campFootball = new CampFootball();
            campFootball.setId(cursor.getInt(0));
            campFootball.setName(cursor.getString(1));
            campFootball.setDescription(cursor.getString(2));
            campFootball.setAddress(cursor.getString(3));
            campFootball.setPhone(cursor.getInt(4));
            campFootball.setImage(cursor.getString(5));
            campFootballList.add(campFootball);
        }

        cursor.close();

        if (campFootballList.size() != 0){
            processData();
        }else{
            Toast.makeText(this, "Lista vacia", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        listFieldSoccerAdapter = new ListFieldSoccerAdapter(campFootballList, getApplicationContext());
        recyclerViewContactsListFieldSoccer.setAdapter(listFieldSoccerAdapter);
    }



}
