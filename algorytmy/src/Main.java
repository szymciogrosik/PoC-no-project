import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        double[] t = {1.25,4.15,9.15,1.05,3.26,6.42};
        double[] t = {3493, 332, 811, 213, 2862, 2531, 35, 769, 18, 682, 150, 1315, 42, 1208, 1514, 616, 50, 140, 649, 1152, 1127, 812, 625, 815, 385, 855, 688, 270, 554, 338, 336, 1592, 900, 2970, 1730, 67, 246, 79, 1378, 42, 804, 523, 734, 318, 596, 1852, 142, 784, 166, 166, 2105, 2017, 222, 578, 689, 748, 2070, 76, 758, 564, 629, 960, 262, 515, 277, 1976, 592, 438, 1761, 934, 81, 697, 974, 120, 718, 1012, 627, 963, 367, 1016, 1597, 1511, 336, 55, 1236, 424, 515, 117, 1980, 69, 212, 3060, 180, 211, 3297, 124, 1537, 1188, 974, 373, 293, 1286, 2354, 46, 445, 1057, 2124, 1534, 2441, 1106, 496, 421, 659, 103, 1024, 1397, 137, 145, 278, 1869, 1997, 90, 5, 67, 1200, 1220, 1019, 2578, 698, 103, 874, 386, 1375, 1018, 1072, 588, 1621, 38, 1215, 3299, 618, 1573, 932, 497, 356, 387, 280, 1001, 284, 694, 383, 476, 402, 1377, 213, 130, 1330, 140, 861, 569, 2446, 159, 67, 880, 644, 1535, 789, 990, 824, 807, 1372, 107, 33, 576, 112, 201, 480, 20, 63, 359, 242, 362, 674, 87, 1001, 460, 1006, 3070, 229, 1847, 2026, 425, 378, 8257, 374, 1376, 193, 1658, 3487, 109, 1200, 129, 1645, 132, 1384, 404, 1124, 212, 1984, 2550, 511, 10, 2433, 593, 2750, 282, 3169, 1179, 1504, 431, 1307, 2626, 178, 2607, 3313};

        JelinskiMorandyModel jmm = new JelinskiMorandyModel(0.5, t, 0.02, 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
        jmm = new JelinskiMorandyModel(new Random().nextInt(), t, new Random().nextInt(), 0.0001);
        jmm.calculateModel();
    }
}
