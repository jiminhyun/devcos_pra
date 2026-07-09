public class Dip {
    // ❌ 나쁜 예: 알림 서비스가 EmailSender를 '직접' new 함
    class NotificationService {
        private MessageSender sender;   // 구체 클래스에 못 박힘

        public NotificationService(MessageSender sender) {
            this.sender = sender;
        }

        void notifyUser(String msg) { sender.send(msg); }
    }

    interface MessageSender {
        void send(String msg);
    }

    class EmailSender implements MessageSender {
        public void send(String msg){
            System.out.println("[이메일] "+msg);
        }
    }

    class SmsSender implements MessageSender {
        public void send(String msg){
            System.out.println("[SMS] "+msg);
        }
    }
}
