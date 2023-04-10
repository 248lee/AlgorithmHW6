public class App {
    public static void main(String[] args) throws Exception {
        int[][] a = {{10, 20, 30, 17, 44}, {40, 70, 50, 100, 98}};
        HW06_4110064229_1 hw6_1 = new HW06_4110064229_1();
        HW06_4110064229_2 hw6_2 = new HW06_4110064229_2();
        System.out.println(hw6_1.find_median(a));
        System.out.println(hw6_2.find_median(a));
    }
}
