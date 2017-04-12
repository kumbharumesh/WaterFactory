/**
 * Created by umesh_kumbhar on 4/11/17.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        WaterFactory factory = new WaterFactory();
        factory.start();
        int i = 10;
        while (i > 0){
            factory.getProduct();
            Thread.sleep(2000);
            i--;
        }

        factory.stop();
    }
}
