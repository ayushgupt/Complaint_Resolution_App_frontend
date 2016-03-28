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
public class My_resolved_ArrayAdapter extends ArrayAdapter<String>{
    private final Context context;
    private final String[] head;
    private final String[] time;
    private final String[] upvote ;
    private final String[] type ;

    public My_resolved_ArrayAdapter(Context context, String[] head,String[] time,String[] upvote,String[] type) {
        super(context, -1, head);
        this.head=head ;
        this.time = time;
        this.upvote = upvote;
        this.context = context;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(type[position].equals("Individual"))
        {
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
        else if(type[position].equals("Hostel"))
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.hostel_row, parent, false);

            TextView heading = (TextView) rowView.findViewById(R.id.head);
            TextView own = (TextView) rowView.findViewById(R.id.owner);
            TextView up = (TextView) rowView.findViewById(R.id.upvote);
            TextView tim = (TextView) rowView.findViewById(R.id.time);

            heading.setText(head[position]);
            own.setText("You");
            up.setText(upvote[position]);
            tim.setText(time[position]);
            // change the icon for Windows and iPhone
            //number_View.setText(Integer.toString(position+1)+")");
            return rowView;

        }
        else
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.institute_row, parent, false);

            TextView heading = (TextView) rowView.findViewById(R.id.head);
            TextView own = (TextView) rowView.findViewById(R.id.owner);
            TextView up = (TextView) rowView.findViewById(R.id.upvote);
            TextView tim = (TextView) rowView.findViewById(R.id.time);

            heading.setText(head[position]);
            own.setText("You");
            up.setText(upvote[position]);
            tim.setText(time[position]);
            // change the icon for Windows and iPhone
            //number_View.setText(Integer.toString(position+1)+")");
            return rowView;

        }
    }
}
