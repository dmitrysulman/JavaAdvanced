package inter.complex;

public class MyCreator extends Creator {
    @Override
    public Complex createComplex(double real, double image) {
        return new MyComplex(real, image);
    }
}
