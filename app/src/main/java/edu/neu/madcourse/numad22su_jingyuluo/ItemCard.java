package edu.neu.madcourse.numad22su_jingyuluo;

public class ItemCard implements ItemClickListener {

    private final String linkName;
    private final String linkUrl;

    public ItemCard(String linkName, String linkUrl) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getLinkName() {
        return linkName;
    }


    @Override
    public void onItemClick(int position) {

    }

}