package inter.bignumber;

public interface BigNumber extends Comparable<Object> {
    BigNumber add(BigNumber bigNumber);

    BigNumber sub(BigNumber bigNumber);

    public boolean isPositive();

    public BigNumber abs();

    public BigNumber makeNegative();
}
