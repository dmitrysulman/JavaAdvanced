package inter.complex;

public class Main {
    public static void main(String[] args) {
        Creator myCreator = new MyCreator();
        Complex myComplex1 = myCreator.createComplex(2.12, 3.46);
        Complex myComplex2 = myCreator.createComplex(-5.64, 7.12);
        System.out.println(myComplex1);
        System.out.println(myComplex1.mul(myComplex2));

        Complex myComplex3 = myCreator.createComplex(-5.643, 7.124);
        System.out.println(myComplex2.equals(myComplex3));
    }
}
