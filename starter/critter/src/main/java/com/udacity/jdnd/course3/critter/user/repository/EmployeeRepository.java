package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e \n" +
            "from Employee e\n" +
            "join e.daysAvailable day\n" +
            "where\n" +
            "day.dayOfWeek = :dayOfWeek\n" +
            "and" +
            "(select count(distinct skill.employeeSkill) " +
            "   from e.skills skill where skill.employeeSkill in :skills) = :skillLength")
    List<Employee> findAllByDaysAvailableAndInSkills(@Param("dayOfWeek") DayOfWeek dayOfWeek,
                                                     @Param("skills") Collection<EmployeeSkill> skills,
                                                     @Param("skillLength") long skillLength);

}
