package com.example.progtobi.e_learning.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progtobi.e_learning.R;
import com.example.progtobi.e_learning.model._User;

import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 13-Oct-16.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private Context activity;
    private LayoutInflater inflater;
    ArrayList<_User> studdata = new ArrayList<>();

    /*public StudentAdapter(Context context, ArrayList<_User> studdata) {
        inflater = LayoutInflater.from(context);
        this.studdata = studdata;
    }*/

    public StudentAdapter(Context activity, ArrayList<_User> arrayList) {
        this.activity = activity;
        this.studdata = arrayList;

    }

    public StudentAdapter(ArrayList<_User> users) {
        this.studdata = users;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentsview, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        _User student = studdata.get(position);

        //holder.studimg.setImageResource(R.drawable.image_view);
        holder.studname.setText(student.getName());
        holder.studphone.setText(student.getPhone());
        //holder.callstud.setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public int getItemCount() {

        return studdata.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView studname, studphone;
        ImageView studimg, callstud;

        public MyViewHolder(View itemView) {
            super(itemView);
            studname = (TextView) itemView.findViewById(R.id.name);
            studphone = (TextView) itemView.findViewById(R.id.phone);
            studimg = (ImageView) itemView.findViewById(R.id.studimage);
            callstud = (ImageView) itemView.findViewById(R.id.call);

            callstud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        _User clickedDataItem = studdata.get(pos);
                        final String number = clickedDataItem.getPhone();
                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "
                                + number));
                        activity.startActivity(i);
                    }
                }
            });
        }
    }
}
