package com.hello.cakeapp;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.widget.AdapterView;
import android.widget.BaseAdapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class CakeListAdapter  extends BaseAdapter{
    private Context context;
    private  int layout;
    private ArrayList<CakeTable> cakesList;

    public CakeListAdapter(Context context, int layout, ArrayList<CakeTable> cakesList) {
        this.context = context;
        this.layout = layout;
        this.cakesList = cakesList;
    }

    @Override
    public int getCount() {
        return cakesList.size();
    }

    @Override
    public Object getItem(int position) {
        return cakesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice ;
        TextView cakename_copy,flavour_copy,cost_copy,weight_copy,type_copy,detail_copy;
        Button order;
        ImageView iv_copy;
    }

    @Override
    public View getView(final int position, final View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            final CakeTable temp =cakesList.get(position);

                    holder.order=(Button) row.findViewById(R.id.order);

           holder.order.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Intent ne = new Intent(context,FinalizeOrder.class);
                   ne.putExtra("cakename", String.valueOf(temp.getCakename()));
                   ne.putExtra("flavor", String.valueOf(temp.getTitle()));
                   ne.putExtra("cost", String.valueOf(temp.getCost()));
                   ne.putExtra("weight", String.valueOf(temp.getWeight()));
                   ne.putExtra("type", String.valueOf(temp.getType()));
                   ne.putExtra("detail", String.valueOf(temp.getDetail()));
                   //ne.putExtra("image", Byte[].valueOf(temp.getIv()));
                   ne.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(ne);
               }
           });
                   /** ne.putExtra("cakename",temp.getCakename());
                    ne.putExtra("flavor",temp.getTitle());
                    ne.putExtra("cost",temp.getCost());
                    ne.putExtra("weight",temp.getWeight());
                    ne.putExtra("type",temp.getType());
                    ne.putExtra("detail",temp.getDetail());
                    ne.putExtra("image",temp.getIv());
                    ne.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);**/




            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        CakeTable food = cakesList.get(position);

        holder.txtName.setText(food.getCakename());
        holder.txtPrice.setText(food.getCost());

        byte[] foodImage = food.getIv();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }



}
