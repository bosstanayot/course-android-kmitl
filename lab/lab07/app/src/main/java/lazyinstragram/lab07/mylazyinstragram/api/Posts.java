package lazyinstragram.lab07.mylazyinstragram.api;

/**
 * Created by barjord on 10/16/2017 AD.
 */


public class Posts {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String like;
    private String url;

}

