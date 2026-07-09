package temp3;

//자원을 계속 요청하는 일꾼
public class WorkerThread extends Thread{
    private int count = 0;
    private SharedResource sharedResource;
    private String threadName;

    public WorkerThread(SharedResource sharedResource, String threadName) {
        this.sharedResource = sharedResource;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        while (true) {
            try {
                sharedResource.waitForResource(threadName);
                /*count++;
                System.out.println(threadName + " count = " + count);*/
                Thread.sleep(100);
                /*if (count > 100) break;*/
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        /*long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("걸린 시간: "+durationMs);*/
    }
}
