package nl.rocvantwente.rsk01.kittensearch;

public class ExampleItem {


    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }

    public ExampleItem(String imageUrl , String creator , int likes){
        mCreator = creator;
        mImageUrl = imageUrl;
        mLikes = likes;
    }
}
