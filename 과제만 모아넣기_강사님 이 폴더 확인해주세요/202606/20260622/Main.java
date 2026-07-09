
public class Main {
    public static void main(String[] args) {
        System.out.println("===== SRP: 단일 책임 =====");
        Srp srp = new Srp();
        Srp.Journal journal = srp.new Journal();
        Srp.JournalSaver journalSaver = srp.new JournalSaver();
        journal.add("오늘은 자바를 배웠다");
        journal.add("SOLID는 어렵지만 재밌다");
        journalSaver.print(journal);
        System.out.println("===== OCP: 개방-폐쇄 =====");
        Ocp ocp = new Ocp();
        Ocp.DiscountPolicy[] dp = {ocp.new BasicDiscount(), ocp.new GoldDiscount(), ocp.new VipDiscount()};
        String[] name = {"일반", "골드", "Vip"};
        for (int i = 0; i < dp.length; i++) {
            System.out.println(name[i]+" 회원 -> "+dp[i].discount(10000)+"원");
        }
        System.out.println();
        System.out.println("===== LSP: 리스코프 치환 =====");
        Lsp lsp = new Lsp();
        Lsp.Bird[] birds = {lsp.new Penguin(), lsp.new Sparrow()};
        for (Lsp.Bird bird : birds) {
            bird.eat();
        }
        ((Lsp.Sparrow)birds[1]).fly();
        ((Lsp.Penguin)birds[0]).swim();
        System.out.println();
        System.out.println("===== ISP: 인터페이스 분리 =====");
        Isp isp = new Isp();
        Isp.SimplePrinter simplePrinter = isp.new SimplePrinter();
        Isp.SmartMachine smartMachine = isp.new SmartMachine();
        simplePrinter.print();
        smartMachine.print();
        smartMachine.scan();
        System.out.println();
        System.out.println("===== DIP: 의존관계 역전 =====");
        Dip dip = new Dip();
        Dip.MessageSender[] messageSenders = {dip.new EmailSender(), dip.new SmsSender()};
        Dip.NotificationService[] notificationService = {dip.new NotificationService(messageSenders[0]), dip.new NotificationService(messageSenders[1])};
        notificationService[0].notifyUser("주문이 완료되었습니다");
        notificationService[1].notifyUser("주문이 완료되었습니다");
    }
}
