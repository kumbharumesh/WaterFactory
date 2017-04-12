import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by umesh_kumbhar on 4/11/17.
 */
public class WaterFactory implements Factory {

    private BlockingQueue<Integer> waterStorage;
    private WaterPlant waterPlant;

    public WaterFactory(){

        this.waterStorage = new ArrayBlockingQueue<>(10);
        this.waterPlant = new WaterPlant(waterStorage, 10);
    }

    @Override
    public void start() {
        Thread plant = new Thread(waterPlant);
        plant.start();
    }

    @Override
    public void stop() {
        waterPlant.stopPlant();
    }

    @Override
    public void getProduct() {
        try {
            System.out.println(waterStorage.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
