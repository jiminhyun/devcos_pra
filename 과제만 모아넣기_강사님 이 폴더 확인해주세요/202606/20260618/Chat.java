package temp3;

//	공유 객체. flag로 차례 관리 + question/answer(synchronized
public class Chat {
    private volatile boolean flag;

    public synchronized void question(String msg) throws InterruptedException {
        if(flag) {
            wait();
        }
        System.out.println("Question : " + msg);
        flag = true;
        notify();
    }

    public synchronized void answer(String msg) throws InterruptedException {
        if(!flag) {
            wait();
        }
        System.out.println("Answer : " + msg);
        flag = false;
        notify();
    }

}
