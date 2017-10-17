package lazyinstragram.lab07.mylazyinstragram;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import lazyinstragram.lab07.mylazyinstragram.adapter.PostAdapter;
import lazyinstragram.lab07.mylazyinstragram.api.LazyinstragramApi;
import lazyinstragram.lab07.mylazyinstragram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Spinner userSpinner;
    Context context = this;
    private String userselect;
    private String type = "grid";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSpinner = (Spinner)findViewById(R.id.spinner);

        final String[] user = getResources().getStringArray(R.array.user);
        ArrayAdapter<String> adapterUser = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item    , user);
        userSpinner.setAdapter(adapterUser);
        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userselect = user[position];
                Toast.makeText(MainActivity.this,userselect,Toast.LENGTH_SHORT).show();
                getUserProfile(userselect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void getUserProfile(String usrName){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyinstragramApi.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyinstragramApi lazyinstragramApi = retrofit.create(LazyinstragramApi.class);

        Call<UserProfile> call = lazyinstragramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();
                    TextView textUser = (TextView) findViewById(R.id.textUser);
                    textUser.setText("@"+userProfile.getUser());

                    TextView textFollower = (TextView) findViewById(R.id.textFollower);
                    textFollower.setText("Follower\n"+userProfile.getFollower());

                    TextView textFollowing = (TextView) findViewById(R.id.textFollowing);
                    textFollowing.setText("Following\n"+userProfile.getFollowing());

                    TextView textPost = (TextView) findViewById(R.id.textPost);
                    textPost.setText("Post\n"+userProfile.getPost());

                    TextView textBio = (TextView) findViewById(R.id.textBio);
                    textBio.setText(userProfile.getBio());

                    ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);

                    PostAdapter postAdapter = new PostAdapter(context,userProfile);
                    postAdapter.setType(type);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    if(type.equals("grid")){
                        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
                    }
                    else if(type.equals("list")){
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }

    public void onGrid(View view) {

       type = "grid";
        getUserProfile(userselect);

    }

    public void onList(View view) {

        type = "list";
        getUserProfile(userselect);
    }
}
