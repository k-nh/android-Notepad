package com.example.notepad;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private String text ;

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    private boolean pressed ;

    public void setText(String text) {
        this.text = text ;
    }

    public String getText() {
        return this.text ;
    }
}
