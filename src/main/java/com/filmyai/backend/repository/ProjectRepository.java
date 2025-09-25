package com.filmyai.backend.repository;

import com.filmyai.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//	@Query("SELECT p FROM Project p LEFT JOIN FETCH p.jobs j LEFT JOIN FETCH j.role LEFT JOIN FETCH j.jobActor LEFT JOIN FETCH p.genre")
//	List<Project> findAllWithJobs();
//
//	@Query("SELECT p FROM Project p " + "LEFT JOIN FETCH p.jobs j " + "LEFT JOIN FETCH j.role "
//			+ "LEFT JOIN FETCH j.jobActor " + "LEFT JOIN FETCH p.genre " + "WHERE p.projectId = :id")
//	Optional<Project> findByIdWithJobs(@Param("id") Long id);
//
//	@Query("SELECT DISTINCT p FROM Project p " + "LEFT JOIN FETCH p.jobs j " + "LEFT JOIN FETCH j.role "
//			+ "LEFT JOIN FETCH j.jobActor " + "LEFT JOIN FETCH p.genre g " + "WHERE g.genreId = :genreId")
//	List<Project> findByGenreIdWithJobs(@Param("genreId") Long genreId);

	// Query to find projects by production company
//    @Query("SELECT p FROM Project p WHERE :company = ANY(p.productionCompanies)")
//    List<Project> findByProductionCompany(@Param("company") String company);

	// Query to find projects by genre
//    @Query("SELECT p FROM Project p WHERE :genre = ANY(p.genre)")
//    List<Project> findByGenre(@Param("genre") String genre);

	@Query(value = "SELECT * FROM projects p WHERE :genre = ANY (p.genre)", nativeQuery = true)
	List<Project> findByGenre(String genre);

	/**
	 * Find projects where a given production company exists in the
	 * production_companies array column.
	 */
	@Query(value = "SELECT * FROM projects p WHERE :company = ANY (p.production_companies)", nativeQuery = true)
	List<Project> findByProductionCompany(String company);

}
