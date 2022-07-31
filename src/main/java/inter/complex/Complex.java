package inter.complex;

public interface Complex {
    Complex sum(Complex other);

    Complex sub(Complex other);

    Complex mul(Complex other);

    double getReal();

    double getImage();
}
