package com.yangsong.bookmarkApp.constants;

public enum UserType{
    USER("user"),
    EDITOR("editor"),
    CHIEF_EDITOR("chief_editor");

    private UserType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
