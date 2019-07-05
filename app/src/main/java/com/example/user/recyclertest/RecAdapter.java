package com.example.user.recyclertest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.recyclertest.recyclerView;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

    private List<items> itemsList;
    private AppCompatActivity contextnow;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body, tinput, cinput;
        public ImageView del, add;
        public Button addb;

        public MyViewHolder(View view) {
            super(view);


            del = view.findViewById(R.id.imageView2);
            add = view.findViewById(R.id.imageView);
            title = (TextView) view.findViewById(R.id.title);
            body = (TextView) view.findViewById(R.id.body);

        }
    }


    public RecAdapter(List<items> itemsLists, AppCompatActivity cont) {
        this.itemsList = itemsLists;
        this.contextnow = cont;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_element, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        items currentItem = itemsList.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.body.setText(currentItem.getBody());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsList.remove(position);
                notifyDataSetChanged();


            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = contextnow.getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.activity_alert, null);
                final TextView ttitleTextbox = alertLayout.findViewById(R.id.dtitle);
                final TextView bodyTextbox = alertLayout.findViewById(R.id.dbody);
                ttitleTextbox.setText(itemsList.get(position).getTitle());
                bodyTextbox.setText(itemsList.get(position).getBody());
                AlertDialog.Builder alert = new AlertDialog.Builder(contextnow);
                alert.setTitle("Edit");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                alert.setNegativeButton("Cancel", null);

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String titl = ttitleTextbox.getText().toString();
                        String body = bodyTextbox.getText().toString();
                        itemsList.get(position).setTitle(titl);
                        itemsList.get(position).setBody(body);
                        notifyDataSetChanged();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}