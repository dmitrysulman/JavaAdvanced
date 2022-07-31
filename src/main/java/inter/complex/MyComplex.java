package inter.complex;

import java.util.Objects;

public class MyComplex implements Complex {
    private final double real;

    private final double image;

    public MyComplex(double real, double image) {
        this.real = real;
        this.image = image;
    }

    @Override
    public Complex sum(Complex other) {
        return null;
    }

    @Override
    public Complex sub(Complex other) {
        return null;
    }

    @Override
    public Complex mul(Complex other) {
        return null;
    }

    @Override
    public double getReal() {
        return real;
    }

    @Override
    public double getImage() {
        return image;
    }

    @Override
    public String toString() {
        return String.format("%.2f+%.2fi", real, image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyComplex myComplex = (MyComplex) o;
        return myComplex.toString().equals(toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }
}
