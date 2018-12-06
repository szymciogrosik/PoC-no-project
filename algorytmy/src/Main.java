import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        double[] t = {1.25,4.15,9.15,1.05,3.26,6.42};
        double[] t = {7,11,8,10,15,22,20,25,28,35};

        JelinskiMorandyModel jmm = new JelinskiMorandyModel(0.5, t, 0.02, 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextDouble(), 0.0001);
        jmm.calculateModel();

        System.out.println("Hello World!");
    }
}
