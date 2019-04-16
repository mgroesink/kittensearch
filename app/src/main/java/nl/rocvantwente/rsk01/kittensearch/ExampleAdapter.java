package nl.rocvantwente.rsk01.kittensearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleItems) {
        this.mContext = context;
        this.mExampleList = exampleItems;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView)itemView.findViewById(R.id.image_view);
            mTextViewCreator = (TextView)itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = (TextView)itemView.findViewById(R.id.text_view_likes);

        }
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent , false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem item = mExampleList.get(position);
        holder.mTextViewLikes.setText("Likes: " + item.getmLikes());
        holder.mTextViewCreator.setText(item.getmCreator());
        Picasso.with(mContext).load(item.getmImageUrl()).fit()
                .centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
