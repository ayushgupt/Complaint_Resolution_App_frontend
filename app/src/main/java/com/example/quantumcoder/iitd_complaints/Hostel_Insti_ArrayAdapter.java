package com.example.quantumcoder.iitd_complaints;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by Admin on 28-Mar-16.
 */
public class Hostel_Insti_ArrayAdapter extends ArrayAdapter<String>{
    private final Context context;
    private final String[] head;
    private final String[] owner ;
    private final String[] time;
    private final String[] upvote ;


    public Hostel_Insti_ArrayAdapter(Context context, String[] head, String[] owner,String[] time,String[] upvote) {
        super(context, -1, head);
        this.head=head ;
        this.owner = owner;
        this.time = time;
        this.upvote = upvote;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_complaint_1, parent, false);

        TextView heading = (TextView) rowView.findViewById(R.id.head);
        TextView own = (TextView) rowView.findViewById(R.id.owner);
        TextView up = (TextView) rowView.findViewById(R.id.upvote);
        TextView tim = (TextView) rowView.findViewById(R.id.time);

        heading.setText(head[position]);
        own.setText(owner[position]);
        up.setText(upvote[position]);
        tim.setText(time[position]);
        // change the icon for Windows and iPhone
        //number_View.setText(Integer.toString(position+1)+")");
        return rowView;
    }



}
