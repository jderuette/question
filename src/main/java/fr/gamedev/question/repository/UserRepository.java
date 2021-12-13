/**
 *
 */
package fr.gamedev.question.repository;

import fr.gamedev.question.data.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author RayaneKettana
 *
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  /**
   * Find by last name.
   * @param name the name
   * @return (List)
   */
  List<User> findByLastName(@Param("name") String name);

}
