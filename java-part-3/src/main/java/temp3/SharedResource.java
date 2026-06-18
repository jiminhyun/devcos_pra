package temp3;

//공유 자원. isAvailable + 대기(waitForResource) / 공급(makeResourceAvailable)
public class SharedResource {
    private volatile boolean isAvailable;

    public synchronized void waitForResource(String name) throws InterruptedException {
        while(!isAvailable) {
            System.out.println(name + " is waiting for resource...");
            wait(); //notify하면 다음 구문으로 가는것이다. 그래서 while문 다시 타고 오는것이다. all하면 모든 스레드가 깨어나기때문에 다시 밑에 줄로가고 아닌 경우에는 하나만 집어서 하는것인데 flag로 처리를 해놨기 때문에 안전하다.
        }
        System.out.println(name + " got the resource!");
        isAvailable = false;
    }

    public synchronized void makeResourceAvailable () throws InterruptedException {
        isAvailable = true;
        System.out.println("Resource is now available!");
        notifyAll();
    }

}
