package com.hello.cakeapp;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import javax.annotation.Nullable;
import com.hello.cakeapp.GalleryPickUp;
public class CakeList extends GalleryPickUp {

    GridView gridView;
    ArrayList<CakeTable> list;
    CakeListAdapter adapter = null;
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_list_activity);
        try {
            gridView = (GridView) findViewById(R.id.gridView);
            list = new ArrayList<>();
            adapter = new CakeListAdapter(this, R.layout.cake_items, list);
            gridView.setAdapter(adapter);
            Cursor cursor = GalleryPickUp.sqLiteHelper.getData("SELECT * FROM CAKETABLE");
            list.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String cakename = cursor.getString(1);
                String title = cursor.getString(2);
                String weight = cursor.getString(3);
                String type = cursor.getString(4);
                String cost = cursor.getString(5);
                String detail = cursor.getString(6);
                byte[] iv = cursor.getBlob(7);

                list.add(new CakeTable(cakename, title, weight, type, cost, detail, iv, id));
            }
            adapter.notifyDataSetChanged();
        }

        catch(NullPointerException e){

        }

    }



}