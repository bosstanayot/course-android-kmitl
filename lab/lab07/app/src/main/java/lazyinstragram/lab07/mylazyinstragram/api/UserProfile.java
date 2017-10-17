package lazyinstragram.lab07.mylazyinstragram.api;

/**
 * Created by student on 10/6/2017 AD.
 */

public class UserProfile {
    private String user;
    private String follower;
    private String post;
    private String bio;
    private String following;

    public Posts[] getPosts() {
        return posts;
    }

    public void setPosts(Posts[] posts) {
        this.posts = posts;
    }

    private Posts [] posts;



    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    private String urlProfile;
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }




    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }



    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }



}
