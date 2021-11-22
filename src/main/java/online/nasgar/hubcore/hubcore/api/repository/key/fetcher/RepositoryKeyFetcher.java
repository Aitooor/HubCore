package online.nasgar.hubcore.hubcore.api.repository.key.fetcher;

import me.yushust.inject.key.TypeReference;
import online.nasgar.hubcore.hubcore.api.repository.key.annotation.RepositoryKey;
import online.nasgar.hubcore.hubcore.api.serializer.Serializable;

import java.util.Locale;

public interface RepositoryKeyFetcher {

    static String fetch(Class<? extends Serializable> clazz){
        if(clazz.isAnnotationPresent(RepositoryKey.class)){
            return clazz
                    .getAnnotation(RepositoryKey.class)
                    .value();
        }
        return getPluralized(clazz
                .getSimpleName()
                .toLowerCase(Locale.ROOT)
        );
    }

    static String fetch(TypeReference<? extends Serializable> typeReference){
        return fetch(typeReference.getRawType());
    }

    static String getPluralized(String origin) {
        return (origin.endsWith("s"))
                ? origin
                : origin + "s";
    }
}
