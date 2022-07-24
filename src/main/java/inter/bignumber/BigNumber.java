package inter.bignumber;

public interface BigNumber extends Comparable<Object> {
    BigNumber add(BigNumber bigNumber);

    BigNumber sub(BigNumber bigNumber);
}
