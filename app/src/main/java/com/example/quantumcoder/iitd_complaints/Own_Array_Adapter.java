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
public class Own_Array_Adapter extends ArrayAdapter<String>{
    private final Context context;
    private final String[] head;
    private final String[] time;
    private final String[] upvote ;

    public Own_Array_Adapter(Context context, String[] head,String[] time,String[] upvote) {
        super(context, -1, head);
        this.head=head ;
        this.time = time;
        this.upvote = upvote;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.individualcomplaints, parent, false);

        TextView heading = (TextView) rowView.findViewById(R.id.head);

        TextView up = (TextView) rowView.findViewById(R.id.upvote);
        TextView tim = (TextView) rowView.findViewById(R.id.time);

        heading.setText(head[position]);

        up.setText(upvote[position]);
        tim.setText(time[position]);
        // change the icon for Windows and iPhone
        //number_View.setText(Integer.toString(position+1)+")");
        return rowView;
    }
}
