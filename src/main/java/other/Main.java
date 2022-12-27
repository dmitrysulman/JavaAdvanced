package other;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String str = "str";

        Object obj = str;

        System.out.println(obj instanceof CharSequence);

        String test = "test";
        MyGenericClass<String> myGenericClass = new MyGenericClass<>(test, test);

        String test1 = myGenericClass.getTObj();
        String test2 = myGenericClass.getObj();
        System.out.println(test1);
        System.out.println(test2.concat("+1"));

        String[] stringArray = new String[10];
        Object[] objects = stringArray;
        stringArray = (String[]) objects;
        stringArray[0] = "str";

        List<?> list = new ArrayList<CharSequence>(List.of("question"));
        System.out.println(list.get(0));
//        list.add(new Object()); //won't compile

        List<? super CharSequence> list1 = new ArrayList<Object>();
        list1.add("aString");
        Object s1 = list1.get(0);
        String s11 = (String) s1;
        System.out.println(s11);
        List<? extends String> list2 = new ArrayList<>(List.of("aTest"));
//        list2.add("aString"); //won't compile
        CharSequence s2 = list2.get(0);
        System.out.println(s2);

        List<String> strings = new ArrayList();
        strings.add("x");
        String fromList = strings.get(0);
        System.out.println(fromList);

        List<Integer> list3 = (List) strings;
        System.out.println(list3.get(0));
        list3.add(1);
        System.out.println(list3.get(1) + 1);

        List<CharSequence> charSequences = (List) strings;
        System.out.println(charSequences.get(0));

        Object o = strings;
// Warning, but will succeeed at execution time
        List<Integer> integers = (List<Integer>) o;
        System.out.println(integers.get(0));
        integers.add(1);
        Integer i = integers.get(1);
        System.out.println(i);
        i = integers.get(0); // Bang!
    }
}
