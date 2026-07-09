package com.example.springtheory.temp;

public class Hollywood {
}

interface ClickListener {
    void onClick();
}

class Button { //매개 변수 업캐스팅, 조립
    ClickListener cl;
    void setListener(ClickListener cl) {
        this.cl = cl;
    }
    void press(){
        System.out.println("[시스템] 버튼이 눌렸습니다");
        cl.onClick();
    }
}

class LikeAction implements ClickListener {
    @Override
    public void onClick() {
        System.out.println("내 코드 실행: 좋아요!");
    }
}