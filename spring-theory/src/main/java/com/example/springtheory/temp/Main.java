package com.example.springtheory.temp;

public class Main {
    static void main(String[] args) {
        System.out.println("===== 2. DI: 제어를 바깥(main)으로 =====");
        new CoffeeMaker(new ColombiaBean()).brew();
        new CoffeeMaker(new EthiopiaBean()).brew();
        System.out.println();
        System.out.println("===== 3. IoC 컨테이너: 조립까지 위임 =====");
        new CoffeeContainer().getCoffeeMaker().brew();
        System.out.println();
        System.out.println("===== 4. 헐리우드 원칙: 흐름의 역전 =====");
        Button button = new Button();
        button.setListener(new LikeAction());
        button.press();
        //@Autowired 만든 빈을 등록해서 쓸 수 있게 하기 때문에 굳이 new 안써도 쓸 수 있다.
    }
}
