package com.example.springtheory.temp;

public class Settings {
    //lazy
    private String theme;
    private static Settings instance = null;  // 유일한 객체를 static으로 보관
    private Settings() {}                                          // 생성자 private → 밖에서 new 금지
    static Settings getInstance() {
        if(instance == null) {
            instance = new Settings();
        }
        return instance;
    }             // 유일한 출입구

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
