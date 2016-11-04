package fi.lahtinen.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findById(String id);
    
}
