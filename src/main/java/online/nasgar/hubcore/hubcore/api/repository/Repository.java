package online.nasgar.hubcore.hubcore.api.repository;


import online.nasgar.hubcore.hubcore.api.serializer.Serializable;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Represents the repository object
 * such a database or file.
 *
 * @param <O> The serializable model to be saved.
 */
public interface Repository<O extends Serializable> {

    /**
     * @see #findSync(String)
     *
     * @param id The {@link O}'s id.
     * @return A {@link CompletableFuture} of the
     * method {@link #findSync(String)}.
     */
    default CompletableFuture<Optional<O>> find(String id){
        return CompletableFuture.supplyAsync(() -> {
            try {
                return findSync(id);
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
            return Optional.empty();
        });
    }

    /**
     * Searches for an {@link O} stored
     * in the {@link Repository}'s implementation.
     *
     * @param id The {@link O}'s id.
     * @return An optional of {@link O}
     * result of the search.
     * @throws IOException
     */
    Optional<O> findSync(String id) throws IOException;

    /**
     * @see #findAllSync()
     *
     * @return A {@link CompletableFuture} of a
     * {@link Set} with all {@link O} saved
     * in the {@link Repository}'s implementation.
     */
    default CompletableFuture<Set<O>> findAll(){
        return CompletableFuture.supplyAsync(this::findAllSync);
    }

    /**
     * Searches all {@link O} saved
     * in the {@link Repository}'s implementation.
     *
     * @return A {@link Set} of {@link O}
     * saved in the {@link Repository}'s implementation.
     */
    Set<O> findAllSync();

    /**
     * @see #removeSync(String).
     *
     * @param id The {@link O}'s id.
     * @return A {@link CompletableFuture} of {@link Boolean} result
     * if the {@link O} was be saved.
     */
    default CompletableFuture<Boolean> remove(String id){
       return CompletableFuture.supplyAsync(() -> removeSync(id));
    }

    /**
     * Remove the {@link O} corresponding
     * with the id.
     *
     * @param id The {@link O}'s id.
     * @return A {@link Boolean} result
     * if the {@link O} was be saved.
     */
    boolean removeSync(String id);


    /**
     * @see #replaceSync(String, Serializable).
     *
     * @param id The {@link O}'s id .
     * @param o The {@link O} to be replaced.
     * @return A {@link CompletableFuture} of a
     * {@link Boolean} result if the {@link O}
     * was be saved.
     */
    default CompletableFuture<Boolean> replace(String id, O o){
        return CompletableFuture.supplyAsync(() -> {
            try {
                replaceSync(id, o);
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
            return null;
        });
    }

    /**
     * Replace a {@link O} with another
     * using
     *
     * @param id The {@link O}'s id .
     * @param o The {@link O} to be replaced.
     * @return A {@link Boolean} result if the
     * {@link O} was be replaced and saved
     * @throws IOException
     */
    boolean replaceSync(String id, O o) throws IOException;

    default CompletableFuture<Boolean> save(O o){
        return CompletableFuture.supplyAsync(() -> {
            try {
                saveSync(o);
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
            return null;
        });
    }

    boolean saveSync(O o) throws IOException;
}
