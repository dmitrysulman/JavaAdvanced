package inter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test1 {
    public static void main(String[] args) {
        int n = 0xfe01ccd1;
        System.out.println(Integer.reverseBytes(n));

        System.out.println(n << 24 | ((n & 0b00000000000000001111111100000000) << 8) | ((n & 0b00000000111111110000000000000000) >> 8) | (n >>> 24));

        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add("str");
        System.out.println(list.get(0) instanceof Integer);
        System.out.println(list.get(1) instanceof CharSequence);
    }
}
