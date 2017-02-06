package com.myapp.android.localfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kavya on 12/13/2016.
 */
public class ResultsAdapter  extends ArrayAdapter{
    ArrayList<Results> results;
    Context context;
    public ResultsAdapter(Context context, ArrayList<Results> results) {
        super(context, R.layout.single_row, results);
        this.results = results;
        this.context = context;
    };
    class resultsviewholder{
        TextView mytitle;
        TextView myaddress;
        TextView myphone;
        TextView mydistance;
        resultsviewholder(View v){
            mytitle = (TextView) v.findViewById(R.id.titlefield);
            myaddress = (TextView) v.findViewById(R.id.addressfield);
            myphone = (TextView) v.findViewById(R.id.phonefield);
            mydistance = (TextView) v.findViewById(R.id.distancefield);

        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row  = convertView;
        resultsviewholder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row,parent,false);
            holder = new resultsviewholder(row);
            row.setTag(holder);
        }else {
            holder = (resultsviewholder) row.getTag();
        }
        Results result = results.get(position);
        holder.mytitle.setText(result.title);
        holder.myaddress.setText(result.address+", "+result.city+", "+result.state);
        holder.myphone.setText("Phone: "+result.phone);
        holder.mydistance.setText("Distance: " +result.distance+" miles");

        return row;

    }
}
