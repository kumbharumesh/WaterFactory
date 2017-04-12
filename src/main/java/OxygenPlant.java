import java.util.concurrent.BlockingQueue;

/**
 * Created by umesh_kumbhar on 4/11/17.
 */

public class OxygenPlant extends plant {
    private BlockingQueue<Integer> storage;
    private boolean shutdown;
    private int max_capacity;

    public OxygenPlant(BlockingQueue<Integer> storage, int max_capacity) {
        this.storage = storage;
        this.shutdown = true;
        this.max_capacity = max_capacity;
    }

    @Override
    public void stopPlant() {
        this.shutdown = true;
    }

    @Override
    public void produce() {

        System.out.println("O Plant Started");
        this.shutdown = false;
        while (!shutdown) {
            try {

                while (storage.size() == max_capacity && !shutdown) {
                    System.out.println("O Waiting");
                    Thread.sleep(1000);
                }

                if (!shutdown) {
                    storage.add(1);
                    System.out.println("O Produced");
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Oxygen Plant stopped");
    }
}
