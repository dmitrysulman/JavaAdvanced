package inter.bignumber;

public class Main {
    public static void main(String[] args) {
        BigNumber n1 = new MyBigNumber("789678");
        BigNumber n2 = new MyBigNumber("789678");
        BigNumber n3 = new MyBigNumber("189678");
        BigNumber n4 = new MyBigNumber("189679");
        BigNumber n5 = new MyBigNumber("0");
        BigNumber n6 = new MyBigNumber("189678");
        BigNumber n7 = new MyBigNumber("-189678");
        BigNumber n8 = new MyBigNumber("-789678");
        BigNumber n9 = new MyBigNumber("189678");
        BigNumber n10 = new MyBigNumber("7789678");
        BigNumber n11 = new MyBigNumber("-189678");
        BigNumber n12 = new MyBigNumber("-7789678");

        System.out.println(n1.compareTo(n2)); //0
        System.out.println(n3.compareTo(n4)); //-1
        System.out.println(n4.compareTo(n3)); //1
        System.out.println(n5.compareTo(n6)); //-1
        System.out.println(n6.compareTo(n5)); //1
        System.out.println(n7.compareTo(n5)); //-1
        System.out.println(n5.compareTo(n7)); //1
        System.out.println(n1.compareTo(n7)); //1
        System.out.println(n7.compareTo(n1)); //-1
        System.out.println(n7.compareTo(n8)); //1
        System.out.println(n8.compareTo(n7)); //-1
        System.out.println(n9.compareTo(n10)); //-1
        System.out.println(n10.compareTo(n9)); //1
        System.out.println(n11.compareTo(n12)); //1
        System.out.println(n12.compareTo(n11)); //-1

        System.out.println(n9.add(n10));

        System.out.println(new MyBigNumber("-20504962648058829508634537240139148734").add(new MyBigNumber("469915985327887")));
        System.out.println(new MyBigNumber("-38027450742057608221309764383410169802626").add(new MyBigNumber("-38027450742057608221309764383410169802626")));
    }
}
