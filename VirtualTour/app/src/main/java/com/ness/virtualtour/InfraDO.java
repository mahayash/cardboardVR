package com.ness.virtualtour;

import java.io.Serializable;

public class InfraDO implements Serializable {

    private String text;
    private String image;
    private String description;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
