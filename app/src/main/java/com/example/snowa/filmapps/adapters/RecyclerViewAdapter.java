package com.example.snowa.filmapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.example.snowa.filmapps.model.ListaFilmowfull;
import com.example.snowa.filmapps.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<ListaFilmowfull> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<ListaFilmowfull> mData){
        this.mContext=mContext;
        this.mData=mData;

        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view =inflater.inflate(R.layout.listafilmowfull_row_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

holder.tv_title.setText(mData.get(position).getTitle());
holder.tv_language.setText(mData.get(position).getLanguage());
holder.tv_category.setText(mData.get(position).getCategory());
holder.tv_rating.setText(mData.get(position).getRating());

//Ładowanie obrazka z internetu przy pomocy Glide

        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_rating;
        TextView tv_language;
        TextView tv_category;
        ImageView img_thumbnail;



        public MyViewHolder(View itemView){
            super(itemView);
            tv_title=itemView.findViewById(R.id.title);
            tv_category=itemView.findViewById(R.id.category);
            tv_rating=itemView.findViewById(R.id.rating);
            tv_language=itemView.findViewById(R.id.language);
            img_thumbnail=itemView.findViewById(R.id.thumbnail);
        }
    }

}
