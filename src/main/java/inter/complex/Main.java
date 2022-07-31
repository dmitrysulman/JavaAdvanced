package inter.complex;

public class Main {
    public static void main(String[] args) {
        Creator myCreator = new MyCreator();
        Complex myComplex = myCreator.createComplex(1, 1);
    }
}
