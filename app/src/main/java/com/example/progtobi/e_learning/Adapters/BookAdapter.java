package com.example.progtobi.e_learning.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progtobi.e_learning.R;
import com.example.progtobi.e_learning.model._Books;

import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 13-Oct-16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context activity;
    private LayoutInflater inflater;
    ArrayList<_Books> books = new ArrayList<>();

    public BookAdapter(Context context, ArrayList<_Books> books) {
        inflater = LayoutInflater.from(context);
        this.books = books;
    }

    public BookAdapter(ArrayList<_Books> books) {
        this.books = books;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books,parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        _Books book = books.get(position);

        holder.bookname.setText(book.getName());
        holder.bookauthor.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {

        return books.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookname, bookauthor;
        ImageView bookimg;

        public MyViewHolder(View itemView) {
            super(itemView);
            bookname = (TextView) itemView.findViewById(R.id.bname);
            bookauthor = (TextView) itemView.findViewById(R.id.bauthor);
            bookimg = (ImageView) itemView.findViewById(R.id.bimage);

        }
    }
}
