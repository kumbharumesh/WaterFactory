/**
 * Created by umesh_kumbhar on 4/11/17.
 */
public abstract class plant implements  Runnable {
    public abstract void stopPlant();
    public abstract void produce();

    @Override
    public void run(){
        produce();
    }
}
