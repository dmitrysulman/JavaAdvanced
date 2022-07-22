package inter;

public class Test1 {
    public static void main(String[] args) {
        int n = 0xfe01ccd1;
        System.out.println(Integer.reverseBytes(n));

        System.out.println(n << 24 | ((n & 0b00000000000000001111111100000000) << 8) | ((n & 0b00000000111111110000000000000000) >> 8) | (n >>> 24));
    }
}
