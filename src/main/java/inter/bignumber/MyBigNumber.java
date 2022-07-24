package inter.bignumber;

public class MyBigNumber implements BigNumber {

    private final String number;

    public MyBigNumber(String number) {
        this.number = number;
    }

    public static boolean isPositive(BigNumber bigNumber) {
        return !bigNumber.toString().startsWith("-");
    }

    public static BigNumber abs(BigNumber bigNumber) {
        if (isPositive(bigNumber)) {
            return bigNumber;
        } else {
            return new MyBigNumber(bigNumber.toString().substring(1));
        }
    }

    private static int compareAbsWithSameLength(BigNumber bigNumber1, BigNumber bigNumber2) {
        String n1 = bigNumber1.toString();
        String n2 = bigNumber2.toString();
        for (int i = 0; i < n1.length(); i++) {
            if (Character.getNumericValue(n1.charAt(i)) > Character.getNumericValue(n2.charAt(i))) {
                return 1;
            } else if (Character.getNumericValue(n1.charAt(i)) < Character.getNumericValue(n2.charAt(i))) {
                return -1;
            }
        }
        return 0;
    }

    public static BigNumber makeNegative(BigNumber bigNumber) {
        if ("0".equals(bigNumber.toString())) {
            return bigNumber;
        } else if (isPositive(bigNumber)) {
            return new MyBigNumber("-" + bigNumber);
        } else {
            return abs(bigNumber);
        }
    }

    private static BigNumber addNumbers(BigNumber bigNumber1, BigNumber bigNumber2) {
        StringBuilder result = new StringBuilder();
        boolean isNumber1Positive = isPositive(bigNumber1);
        boolean isNumber2Positive = isPositive(bigNumber2);

        int number1Start = isNumber1Positive ? 0 : 1;
        int number2Start = isNumber2Positive ? 0 : 1;
        int number1Sign = isNumber1Positive ? 1 : -1;
        int number2Sign = isNumber2Positive ? 1 : -1;
        int number1Position = bigNumber1.toString().length() - 1;
        int number2Position = bigNumber2.toString().length() - 1;
        int add = 0;
        while (number1Position >= number1Start || number2Position >= number2Start) {
            int digit1 = 0;
            int digit2 = 0;

            if (number1Position >= number1Start) {
                digit1 = Character.getNumericValue(bigNumber1.toString().charAt(number1Position)) * number1Sign;
                number1Position--;
            }
            if (number2Position >= number2Start) {
                digit2 = Character.getNumericValue(bigNumber2.toString().charAt(number2Position)) * number2Sign;
                number2Position--;
            }
            int digit = digit1 + digit2 + add;
            add = 0;
            if (digit >= 10) {
                digit -= 10;
                add = 1;
            } else if (digit < 0) {
                digit += 10;
                add = -1;
            }
            result.insert(0, digit);
        }
        return new MyBigNumber(result.toString());
    }

    @Override
    public BigNumber add(BigNumber bigNumber) {
        boolean isThisNumberPositive = isPositive(this);
        boolean isOtherNumberPositive = isPositive(bigNumber);

        if (isThisNumberPositive && isOtherNumberPositive) {
            //оба положительные, складываем
            return addNumbers(this, bigNumber);
        } else if (!isThisNumberPositive && !isOtherNumberPositive) {
            //оба отрицательные, складываем модули, добавляем минус
            return makeNegative(addNumbers(abs(this), abs(bigNumber)));
        } else {
            //одно положительное, другое отрицательное, берем большее со знаком плюс, из большего вычитаем модуль меньшего, добавляем знак большего
            BigNumber number1 = abs(this);
            BigNumber number2 = abs(bigNumber);
            if (number1.compareTo(number2) > 0) {
                BigNumber tmp = addNumbers(number1, makeNegative(number2));
                return isThisNumberPositive ? tmp : makeNegative(tmp);
            } else if (number1.compareTo(number2) < 0) {
                BigNumber tmp = addNumbers(makeNegative(number1), number2);
                return isOtherNumberPositive ? tmp : makeNegative(tmp);
            } else {
                return new MyBigNumber("0");
            }
        }

    }

    @Override
    public BigNumber sub(BigNumber bigNumber) {
        return add(makeNegative(bigNumber));
    }

    @Override
    public int compareTo(Object o) {
        MyBigNumber other = (MyBigNumber) o;
        boolean isPositiveThis = isPositive(this);
        boolean isPositiveOther = isPositive(other);
        boolean isLongerThis = number.length() > other.toString().length();
        boolean isLongerOther = number.length() < other.toString().length();
        if (number.equals(other.toString())) {
            return 0;
        } else if (isPositiveThis && (!isPositiveOther || "0".equals(other.toString()))) {
            return 1;
        } else if (isPositiveOther && (!isPositiveThis || "0".equals(number))) {
            return -1;
        } else if (isPositiveThis && isLongerThis || !isPositiveThis && isLongerOther) {
            return 1;
        } else if (isPositiveThis && isLongerOther || !isPositiveThis && isLongerThis) {
            return -1;
        } else {
            return compareAbsWithSameLength(abs(this), abs(other)) *
                    (isPositiveThis ? 1 : -1);
        }
    }

    @Override
    public String toString() {
        return number;
    }
}
