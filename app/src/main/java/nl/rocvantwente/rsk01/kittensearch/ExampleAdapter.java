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

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
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

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
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
