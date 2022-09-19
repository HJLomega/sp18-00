
public class HorribleSteve {
    public static void main(String [] args) {
        //System.out.println(Flik.isSameNumber(128, 128));
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            /* debug
            if ((i == j) != Flik.isSameNumber(i, j)){
                System.out.print(i);
                System.out.print(j);
                System.out.println("error");
            }
            */
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
