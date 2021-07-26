package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
	/**
	 * This function restart the auto increment of id to 1 only used for testing
	 */
	@Modifying
	@Query(value = "ALTER TABLE EMPLOYEE  ALTER COLUMN ID RESTART WITH 1", nativeQuery = true)
	public void resetId();
}
