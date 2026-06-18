package temp3;

//	하나의 Chat을 두 스레드가 공유하게 실행
public class Main {
    static void main(String[] args) {
        /*Chat chat = new Chat();
        QuestionThread th1 = new QuestionThread(chat);
        AnswerThread th2 = new AnswerThread(chat);
        th1.start();
        th2.start();*/

        /*SharedResource sharedResource = new SharedResource();
        WorkerThread th1 = new WorkerThread(sharedResource, "worker1");
        WorkerThread th2 = new WorkerThread(sharedResource, "worker2");
        WorkerThread th3 = new WorkerThread(sharedResource, "worker3");
        th1.start();
        th2.start();
        th3.start();*/
        SharedResource sharedResource = new SharedResource();
        for (int i = 1; i <= 5; i++) {
            new WorkerThread(sharedResource, "worker" + i).start();
        }
        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(100);
                    sharedResource.makeResourceAvailable();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
