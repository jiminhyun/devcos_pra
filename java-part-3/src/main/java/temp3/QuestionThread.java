package temp3;

//각자 메시지를 돌며 chat 호출
public class QuestionThread extends Thread{
    private String[] msges = { "Hi", "How are you?", "What are you doing?" };
    private Chat chat;
    public QuestionThread(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        for(String msg : msges) {
            try {
                chat.question(msg);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
