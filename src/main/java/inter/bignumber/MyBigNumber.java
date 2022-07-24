package inter.bignumber;

public class MyBigNumber implements BigNumber {

    private final String number;

    public MyBigNumber(String number) {
        this.number = number;
    }

    @Override
    public BigNumber add(BigNumber bigNumber) {
        StringBuilder result = new StringBuilder();
        boolean isThisNumberPositive = isPositive(this);
        boolean isOtherNumberPositive = isPositive(bigNumber);

        if (isThisNumberPositive && isOtherNumberPositive) {
            //оба положительные, складываем
        } else if (!isThisNumberPositive && !isOtherNumberPositive) {
            //оба отрицательные, складываем модули, добавляем минус
        } else {
            //одно положительное, другое отрицательное, берем большее со знаком плюс, из большего вычитаем модуль меньшего, добавляем знак большего
        }

        int thisNumberStart = isThisNumberPositive ? 0 : 1;
        int otherNumberStart = isOtherNumberPositive ? 0 : 1;
        int thisNumberSign = isThisNumberPositive ? -1 : 1;
        int otherNumberSign = isOtherNumberPositive ? -1 : 1;
        int thisNumberPosition = number.length() - 1;
        int otherNumberPosition = bigNumber.toString().length() - 1;
        int add = 0;
        while (thisNumberPosition >= thisNumberStart || otherNumberPosition >= otherNumberStart) {
            if (thisNumberPosition >= thisNumberStart && otherNumberPosition >= otherNumberStart) {
                int thisDigit = Character.getNumericValue(number.charAt(thisNumberPosition)) * thisNumberSign;
                int otherDigit = Character.getNumericValue(bigNumber.toString().charAt(thisNumberPosition)) * otherNumberSign;
                int digit = thisDigit + otherDigit + add;
                if (digit >= 10) {
                    digit -= 10;
                    add = 1;
                } else if (digit < 0 && digit > -10) {
                    digit += 10;
                    add = -1;
                }
            }
        }
        return this;
    }

    public static boolean isPositive(BigNumber bigNumber) {
        return !bigNumber.toString().startsWith("-");
    }

    private static String abs(BigNumber bigNumber) {
        if (isPositive(bigNumber)) {
            return bigNumber.toString();
        } else {
            return bigNumber.toString().substring(1);
        }
    }

    private static int compareAbsWithSameLength(String n1, String n2) {
        for (int i = 0; i < n1.length(); i++) {
            if (Character.getNumericValue(n1.charAt(i)) > Character.getNumericValue(n2.charAt(i))) {
                return 1;
            } else if (Character.getNumericValue(n1.charAt(i)) < Character.getNumericValue(n2.charAt(i))) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public BigNumber sub(BigNumber bigNumber) {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        MyBigNumber other = (MyBigNumber) o;
        if (number.equals(other.toString())) {
            return 0;
        } else if (isPositive(this) && (!isPositive(other) || "0".equals(other.toString()))) {
            return 1;
        } else if (isPositive(other) && (!isPositive(this) || "0".equals(number))) {
            return -1;
        } else if (isPositive(this) && number.length() > other.toString().length() || !isPositive(this) && number.length() < other.toString().length()) {
            return 1;
        } else if (isPositive(this) && number.length() < other.toString().length() || !isPositive(this) && number.length() > other.toString().length()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return number;
    }
}
