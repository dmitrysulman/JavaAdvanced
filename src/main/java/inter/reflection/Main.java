package inter.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    void dependencyInjection(List<Object> objects) throws Exception {
        Map<Class<?>, Object> beans = new HashMap<>();
        objects.forEach(object -> beans.put(object.getClass(), object));
        objects.forEach(object -> {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = beans.get(field.getType());
                    if (bean != null) {
                        try {
                            field.set(object, bean);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        throw new CandidateNotFindException();
                    }
                }
            }
        });
    }

    Map<String, Operation> createPoolOperation(List<Object> objects) throws Exception {
        Map<String, Operation> poolOperation = new HashMap<>();
        objects.forEach(object -> {
            Method[] methods = object.getClass().getDeclaredMethods();
            poolOperation.putAll(Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(BotRequestMapping.class))
                    .collect(Collectors.toMap(method -> method.getAnnotation(BotRequestMapping.class).value(),
                                    method -> () -> method.invoke(object)
                            )
                    )
            );
//                    .forEach(method ->
//                            poolOperation.put(method.getAnnotation(BotRequestMapping.class).value(),
//                                    () -> method.invoke(object)
//                            )
//                    );
        });

        return poolOperation;
    }
}
