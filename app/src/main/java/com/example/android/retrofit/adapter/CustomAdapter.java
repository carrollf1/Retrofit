package com.example.android.retrofit.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.retrofit.R;
import com.example.android.retrofit.model.RetroPost;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroPost> dataList;
    private Context context;

    public CustomAdapter(Context context,List<RetroPost> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView txtTitle;
        TextView txtBody;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            txtBody = mView.findViewById(R.id.body);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.txtBody.setText(dataList.get(position).getBody());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}