package other;

import java.io.Serializable;

public class MyGenericClass<T extends String&Serializable&CharSequence&Comparable<String>> {
    private Object obj;
    private T tObj;

    public MyGenericClass(Object obj, T tObj) {
        this.obj = obj;
        this.tObj = tObj;
    }

    public T getObj() {
        return (T) obj;
    }

    public T getTObj() {
        return tObj;
    }

    public static <T extends CharSequence> T getObj(T t) {
        return t;
    }
}
