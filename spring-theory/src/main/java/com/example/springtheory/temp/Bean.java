package com.example.springtheory.temp;

public interface Bean {// 자식들이라서 직접 바꿔줘야함
    String name();
}

class ColombiaBean implements Bean{
    @Override
    public String name() {
        return "콜롬비아 원두";
    }
}

class EthiopiaBean implements Bean{
    @Override
    public String name() {
        return "에티오피아 원두";
    }
}