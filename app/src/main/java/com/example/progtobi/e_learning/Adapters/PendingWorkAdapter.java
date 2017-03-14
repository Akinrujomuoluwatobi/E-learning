package com.example.progtobi.e_learning.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progtobi.e_learning.PendingWorkFrag;
import com.example.progtobi.e_learning.R;
import com.example.progtobi.e_learning.WorkReadingActivity;
import com.example.progtobi.e_learning.model.PWork;
import com.example.progtobi.e_learning.model._User;

import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 26-Oct-16.
 */

public class PendingWorkAdapter extends RecyclerView.Adapter<PendingWorkAdapter.MyViewHolder> {

    private Context activity;
    private LayoutInflater inflater;
    ArrayList<PWork> pendingwork = new ArrayList<>();

    /*public PendingWorkAdapter(Context context, ArrayList<PWork> pendingwork) {
        inflater = LayoutInflater.from(context);
        this.pendingwork = pendingwork;
    }*/

    public PendingWorkAdapter(Context activity, ArrayList<PWork> arrayList) {
        this.activity = activity;
        this.pendingwork = arrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pworkadaptlayout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PWork pending = pendingwork.get(position);
        holder.pworktitle.setText(pending.getWorktitle());
        holder.pworkteacher.setText(pending.getTeachername());

    }

    @Override
    public int getItemCount() {
        return pendingwork.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pworktitle, pworkteacher;

        public MyViewHolder(View itemView) {
            super(itemView);

            pworktitle = (TextView) itemView.findViewById(R.id.pworktitle);
            pworkteacher = (TextView) itemView.findViewById(R.id.pworkdesc);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        PWork clickedDataItem = pendingwork.get(pos);
                        int ID = clickedDataItem.getId();
                        String worktitle = clickedDataItem.getWorktitle();
                        String worknote = clickedDataItem.getWorktitle();
                        String workexample = clickedDataItem.getTopic_example();
                        String workhint = clickedDataItem.getTopic_hint();
                        String workquest = clickedDataItem.getTopic_questn();
                        String workquestex = clickedDataItem.getQst_example();
                        String id = Integer.toString(ID);
                        Intent intent = new Intent(activity, WorkReadingActivity.class);
                        intent.putExtra("worktitle", worktitle);
                        intent.putExtra("worknote", worknote);
                        intent.putExtra("workexample", workexample);
                        intent.putExtra("workhint", workhint);
                        intent.putExtra("workquest", workquest);
                        intent.putExtra("workquestex", workquestex);
                        activity.startActivity(intent);

                    }
                }
            });
        }
    }
}
