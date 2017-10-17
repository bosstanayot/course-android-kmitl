package lazyinstragram.lab07.mylazyinstragram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import lazyinstragram.lab07.mylazyinstragram.MainActivity;
import lazyinstragram.lab07.mylazyinstragram.R;
import lazyinstragram.lab07.mylazyinstragram.api.UserProfile;

/**
 * Created by student on 10/6/2017 AD.
 */
class Holder extends RecyclerView.ViewHolder{
    public ImageView image;
    public TextView textLike;
    public TextView textComment;
    public Holder(View itemView){
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        textLike = (TextView) itemView.findViewById(R.id.textLike);
        textComment = (TextView) itemView.findViewById(R.id.textComment);
    }
}

public class PostAdapter extends

    RecyclerView.Adapter<Holder>{
    private Context context;
    ArrayList<String> data;
    ArrayList<String> like;
    ArrayList<String> comment;
    private String type;
    public PostAdapter(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public PostAdapter(Context context, UserProfile userProfile) {
        this.context = context;


        data = new ArrayList<>();
        like = new ArrayList<>();
        comment = new ArrayList<>();
        for(int i = 0;i<userProfile.getPosts().length;i++){
            data.add(userProfile.getPosts()[i].getUrl());
            like.add(userProfile.getPosts()[i].getLike());
            comment.add(userProfile.getPosts()[i].getComment());
        }

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(type.equals("grid")){
            view = inflater.inflate(R.layout.post_item, null , false);
            Holder holder = new Holder(view);
            return holder;
        }
        else{
            view = inflater.inflate(R.layout.list_item, null , false);
            Holder holder = new Holder(view);
            return holder;
        }



    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;

        Glide.with(context).load(data.get(position)).into(image);
        if(type.equals("list")){
            holder.textLike.setText(like.get(position));
            holder.textComment.setText(comment.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
