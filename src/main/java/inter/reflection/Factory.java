package inter.reflection;

import java.lang.reflect.ParameterizedType;

abstract class Factory<T> {
    public Factory() {

    }

    public T generateInstance() throws Exception {
        Class<T> aClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return aClass.getDeclaredConstructor().newInstance();
    }
}
