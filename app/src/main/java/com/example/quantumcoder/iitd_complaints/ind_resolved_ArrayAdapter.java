package com.example.quantumcoder.iitd_complaints;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quantumcoder.iitd_complaints.R;

/**
 * Created by Admin on 28-Mar-16.
 */
public class ind_resolved_ArrayAdapter extends ArrayAdapter<String>{
    private final Context context;
    private final String[] head;
    private final String[] time;


    public ind_resolved_ArrayAdapter(Context context, String[] head,String[] time) {
        super(context, -1, head);
        this.head=head ;
        this.time = time;

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.ind_resolved_row, parent, false);

        TextView heading = (TextView) rowView.findViewById(R.id.head);


        TextView tim = (TextView) rowView.findViewById(R.id.time);

        heading.setText(head[position]);


        tim.setText(time[position]);
        // change the icon for Windows and iPhone
        //number_View.setText(Integer.toString(position+1)+")");
        return rowView;
    }
}
