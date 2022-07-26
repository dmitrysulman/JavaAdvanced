package annotation_reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@MyAnnotation(3)
public class Main {

    @MyAnnotation(value = 3)
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Main> c1 = Main.class;
        Main m = new Main();
        Class<? extends Main> c2 = m.getClass();
        Class<?> c3 = Class.forName("annotation_reflection.Main");

        for (Method method : c1.getMethods()) {
            System.out.print(method.getModifiers() + " ");
            System.out.print(method.getReturnType() + " ");
            System.out.print(method.getName() + " ");
            System.out.println(Arrays.toString(method.getParameterTypes()));
        }

        for (Annotation annotation : c1.getAnnotations()) {
            System.out.println(annotation instanceof MyAnnotation);
            System.out.println(Annotation.class);
        }
    }

    @MyAnnotation(value = 3, test = "qwea")
    public void test(Integer a, String q) {

    }
}
