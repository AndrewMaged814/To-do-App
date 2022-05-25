package com.example.myapplication.task.Adapters;
public class RowItem {

    private int ImageId;
    private String Title;

    public RowItem(String Title,int ImageId){

        this.Title = Title;
        this.ImageId = ImageId;
    }

    public String getTitle(){

        return Title;
    }

    public void setTitle(String Title){

        this.Title = Title;
    }

    public int getImageId(){

        return ImageId;
    }

    public void setImageId(int ImageId){

        this.ImageId = ImageId;
    }

    @Override
    public String toString() {
        return Title ;
    }
}
