package inter;

import java.nio.charset.Charset;

public class Test {
    public static void main(String[] args) {
        System.out.println(Long.valueOf(42L).equals(new Long(42L)));
        System.out.println(Integer.valueOf(42).equals(42L));
        System.out.println(Long.valueOf(42L).equals(42));
        System.out.println(Integer.valueOf(42) == Integer.valueOf(42));
        System.out.println(new Integer(42) == new Integer(42));
        System.out.println(Integer.valueOf(42) == 42);
        System.out.println(Long.valueOf(42L).equals(42L));

        Object object = new Object();
        System.out.println("native.encoding : " + System.getProperty("native.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println(System.out.charset().displayName());

        //        Integer.
    }
}
