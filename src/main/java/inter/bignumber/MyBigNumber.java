package inter.bignumber;

public class MyBigNumber implements BigNumber {

    private final String number;

    public MyBigNumber(String number) {
        this.number = number;
    }

    public boolean isPositive() {
        return !number.startsWith("-");
    }

    public BigNumber abs() {
        if (isPositive()) {
            return this;
        } else {
            return new MyBigNumber(number.substring(1));
        }
    }

    private int compareAbsWithSameLength(BigNumber bigNumber) {
        String bigNumberString = bigNumber.toString();
        for (int i = 0; i < number.length(); i++) {
            if (Character.getNumericValue(number.charAt(i)) > Character.getNumericValue(bigNumberString.charAt(i))) {
                return 1;
            } else if (Character.getNumericValue(number.charAt(i)) < Character.getNumericValue(bigNumberString.charAt(i))) {
                return -1;
            }
        }
        return 0;
    }

    public BigNumber makeNegative() {
        if ("0".equals(number)) {
            return this;
        } else if (isPositive()) {
            return new MyBigNumber("-" + number);
        } else {
            return abs();
        }
    }

    private BigNumber addNumbers(BigNumber bigNumber) {
        StringBuilder result = new StringBuilder();
        boolean isNumber1Positive = isPositive();
        boolean isNumber2Positive = bigNumber.isPositive();

        int number1Start = isNumber1Positive ? 0 : 1;
        int number2Start = isNumber2Positive ? 0 : 1;
        int number1Sign = isNumber1Positive ? 1 : -1;
        int number2Sign = isNumber2Positive ? 1 : -1;
        int number1Position = number.length() - 1;
        int number2Position = bigNumber.toString().length() - 1;
        int add = 0;
        while (number1Position >= number1Start || number2Position >= number2Start) {
            int digit1 = 0;
            int digit2 = 0;

            if (number1Position >= number1Start) {
                digit1 = Character.getNumericValue(number.charAt(number1Position)) * number1Sign;
                number1Position--;
            }
            if (number2Position >= number2Start) {
                digit2 = Character.getNumericValue(bigNumber.toString().charAt(number2Position)) * number2Sign;
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
        boolean isThisNumberPositive = isPositive();
        boolean isOtherNumberPositive = bigNumber.isPositive();

        if (isThisNumberPositive && isOtherNumberPositive) {
            //оба положительные, складываем
            return addNumbers(bigNumber);
        } else if (!isThisNumberPositive && !isOtherNumberPositive) {
            //оба отрицательные, складываем модули, добавляем минус
            return ((MyBigNumber) abs()).addNumbers(bigNumber.abs()).makeNegative();
        } else {
            //одно положительное, другое отрицательное, берем большее со знаком плюс, из большего вычитаем модуль меньшего, добавляем знак большего
            MyBigNumber number1 = (MyBigNumber) abs();
            MyBigNumber number2 = (MyBigNumber) bigNumber.abs();
            if (number1.compareTo(number2) > 0) {
                BigNumber tmp = number1.addNumbers(number2.makeNegative());
                return isThisNumberPositive ? tmp : tmp.makeNegative();
            } else if (number1.compareTo(number2) < 0) {
                BigNumber tmp = number2.addNumbers(number1.makeNegative());
                return isOtherNumberPositive ? tmp : tmp.makeNegative();
            } else {
                return new MyBigNumber("0");
            }
        }

    }

    @Override
    public BigNumber sub(BigNumber bigNumber) {
        return add(bigNumber.makeNegative());
    }

    @Override
    public int compareTo(Object o) {
        MyBigNumber other = (MyBigNumber) o;
        boolean isPositiveThis = isPositive();
        boolean isPositiveOther = other.isPositive();
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
            return ((MyBigNumber) abs()).compareAbsWithSameLength(other.abs()) *
                    (isPositiveThis ? 1 : -1);
        }
    }

    @Override
    public String toString() {
        return number;
    }
}
