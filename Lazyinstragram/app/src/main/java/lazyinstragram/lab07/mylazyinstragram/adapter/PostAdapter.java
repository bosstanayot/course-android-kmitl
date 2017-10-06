package lazyinstragram.lab07.mylazyinstragram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import lazyinstragram.lab07.mylazyinstragram.MainActivity;
import lazyinstragram.lab07.mylazyinstragram.R;

/**
 * Created by student on 10/6/2017 AD.
 */
class Holder extends RecyclerView.ViewHolder{
    public ImageView image;

    public Holder(View itemView){
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}

public class PostAdapter extends

    RecyclerView.Adapter<Holder>{
    private Context context;

        String[] data = {"https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/01.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/02.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/03.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/04.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/05.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/06.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/07.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/08.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/09.jpg",
                "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/10.png"

        };

    public PostAdapter(Context context) {
        this.context = context;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null , false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;

        Glide.with(context).load(data[position]).into(image);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
