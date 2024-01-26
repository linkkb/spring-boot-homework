package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        //FROM JPA entity name/field, not table name/column
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by firstname desc", Student.class);

        //return results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        //create query
        //FROM JPA entity name/field, not table name/column
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE firstname=:theData", Student.class);

        //set query parameters
        theQuery.setParameter("theData", firstName);

        //return results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer studentId) {
        // retrieve student data
        Student theStudent = entityManager.find(Student.class, studentId);
        // delete student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
