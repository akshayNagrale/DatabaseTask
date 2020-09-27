package com.andro.databasetask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends  RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private DataAdapterListener mListener;
    List<DataObject> arrayList_two = new ArrayList<DataObject>();
    Context context;

    public DataAdapter(Main2Activity main2Activity, List<DataObject> arrayList_three,DataAdapterListener mListener) {
        this.arrayList_two = arrayList_three;
        this.context = main2Activity;
        this.mListener = mListener;

    }

    public interface DataAdapterListener { // create an interface
        void onClickAtUpdateButton(DataObject data);
        void onClickAtDeleteButton(DataObject data); // create callback function


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listone, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final DataObject data = arrayList_two.get(i);
        viewHolder.name.setText(data.getName());
        viewHolder.city.setText(data.getCity());//object id...

        viewHolder.imgupdate.setOnClickListener
                    (new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onClickAtUpdateButton(data);
                }
            });
            viewHolder.imgdelete.setOnClickListener
                    (new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickAtDeleteButton(data);
                }
            });
        }



    @Override
    public int getItemCount() {
        return arrayList_two.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View update;
        TextView id,name, city;
        ImageView imgupdate,imgdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name =  itemView.findViewById(R.id.name);
            city =  itemView.findViewById(R.id.city);
            imgupdate = itemView.findViewById(R.id.update);
            imgdelete = itemView.findViewById(R.id.delet);
        }
    }
}
