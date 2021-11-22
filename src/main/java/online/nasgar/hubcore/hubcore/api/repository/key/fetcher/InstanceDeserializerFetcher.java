package online.nasgar.hubcore.hubcore.api.repository.key.fetcher;

import com.google.gson.reflect.TypeToken;
import me.yushust.inject.key.TypeReference;
import online.nasgar.hubcore.hubcore.api.repository.key.annotation.InstanceDeserializer;
import online.nasgar.hubcore.hubcore.api.serializer.Serializable;

import java.lang.reflect.Type;

public interface InstanceDeserializerFetcher {

    static <O extends Serializable> Type fetch(Class<O> clazz) {
        if (clazz.isAnnotationPresent(InstanceDeserializer.class)) {
            return TypeToken.get(clazz
                    .getAnnotation(InstanceDeserializer.class)
                    .value())
                    .getType();
        }
        return clazz;
    }

    static <O extends Serializable> Type fetch(TypeReference<O> typeReference) {
        return fetch(typeReference.getRawType());
    }
}
