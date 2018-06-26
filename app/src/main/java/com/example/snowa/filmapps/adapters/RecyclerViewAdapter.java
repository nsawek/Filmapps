package com.example.snowa.filmapps.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.snowa.filmapps.activities.FilmActivity;
import com.example.snowa.filmapps.model.Film;
import com.example.snowa.filmapps.R ;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Film> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<Film> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.film_row_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, FilmActivity.class);
                i.putExtra("t_title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("s_summary",mData.get(viewHolder.getAdapterPosition()).getSummary());
                i.putExtra("l_len",mData.get(viewHolder.getAdapterPosition()).getLanguage());
                i.putExtra("t_time",mData.get(viewHolder.getAdapterPosition()).getTime());
                i.putExtra("y_year",mData.get(viewHolder.getAdapterPosition()).getYear());
                i.putExtra("r_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("film_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_rating.setText(mData.get(position).getRating());
        //Biblioteka glide do załadowania obrazka
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title ;
        TextView tv_rating ;
        ImageView img_thumbnail;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.t_title);
            tv_rating = itemView.findViewById(R.id.r_rating);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

}
