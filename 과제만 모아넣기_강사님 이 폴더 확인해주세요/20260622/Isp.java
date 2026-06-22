public class Isp {
    // ❌ 나쁜 예: 인쇄만 하면 되는데 scan/fax까지 강제로 구현
    interface Machine {
        void print();
        void scan();
        void fax();
    }

    interface Printer {
        void print();
    }

    interface Scanner {
        void scan();
    }

    interface Faxer  {
        void fax();
    }

    class SimplePrinter implements Printer {
        public void print() { System.out.println("구형 프린터: 인쇄만 합니다"); }
    }

    class SmartMachine implements Printer, Scanner {
        public void print() { System.out.println("복합기: 인쇄"); }
        public void scan()  {
            System.out.println("복합기: 스캔"); }
    }
}
