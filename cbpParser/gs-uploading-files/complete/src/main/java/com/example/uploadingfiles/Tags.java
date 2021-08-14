package com.example.uploadingfiles;

import java.util.ArrayList;
import java.util.Arrays;

public class Tags {

    private String content;
    private ArrayList<String> tags;

    public Tags(String content){
        this.content=content;
        if(content!=null)
            tags=new ArrayList<String>(Arrays.asList(content.split(",")));
    }



    public ArrayList<String> getTags(){
        return tags;
    }

}
