import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by umesh_kumbhar on 4/11/17.
 */

public class WaterPlant extends plant {
    private BlockingQueue<Integer> hydrogenStorage;
    private BlockingQueue<Integer> oxygenStorage;
    private BlockingQueue<Integer> waterStorage;

    private plant h2Plant;
    private plant Oplant;

    private boolean shutdown;
    private int max_capacity;

    public WaterPlant(BlockingQueue<Integer> waterStorage, int max_capacity) {

        this.shutdown = true;
        this.max_capacity = max_capacity;

        this.hydrogenStorage = new ArrayBlockingQueue<Integer>(max_capacity * 2);
        this.oxygenStorage = new ArrayBlockingQueue<Integer>(max_capacity);

        this.waterStorage = waterStorage;
        this.h2Plant = new HydrogenPlant(hydrogenStorage, max_capacity  * 2);
        this.Oplant = new OxygenPlant(oxygenStorage, max_capacity);

    }

    @Override
    public void stopPlant() {
        this.h2Plant.stopPlant();
        this.Oplant.stopPlant();
        this.shutdown = true;
    }

    @Override
    public void produce() {

        Thread othread = new Thread(Oplant);
        othread.start();

        Thread hthread = new Thread(h2Plant);
        hthread.start();


        System.out.println("Water Plant Started");
        this.shutdown = false;
        while (!shutdown) {
            try {

                while (waterStorage.size() == max_capacity && !shutdown){
                    System.out.println("H2O Waiting");
                    Thread.sleep(10000);
                }

                if(!shutdown) {
                    int o = oxygenStorage.take();
                    int h1 = hydrogenStorage.take();
                    int h2 = hydrogenStorage.take();
                    System.out.println("H2O Produced");
                    waterStorage.put(o + h1 + h2);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Water Plant stopped");
    }
}
