package temp3;

//	각자 메시지를 돌며 chat 호출
public class AnswerThread extends Thread{
    private String[] msges = { "Hello", "I'm fine, thank you!", "I'm coding in Java" };
    private Chat chat;

    public AnswerThread(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        for(String msg : msges) {
            try {
                chat.answer(msg);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
