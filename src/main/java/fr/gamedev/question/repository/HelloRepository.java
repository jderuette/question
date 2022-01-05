/**
 * 
 */
package fr.gamedev.question.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.gamedev.question.data.Salutation;

/**
 * @author djer1
 *
 */
@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface HelloRepository extends PagingAndSortingRepository<Salutation, Long> {

}
