package temp;

public class Lsp {
    class Bird {
        void eat() {
            System.out.println("냠냠 먹습니다");
        }
    }
    class Penguin extends Bird {
        void swim() {
            System.out.println("첨벙 헤엄칩니다");
        }
    }

    class Sparrow extends Bird implements FlyingBird {
        public void fly() { System.out.println("훨훨 납니다"); }
    }

    interface FlyingBird {
        void fly();
    }
}
