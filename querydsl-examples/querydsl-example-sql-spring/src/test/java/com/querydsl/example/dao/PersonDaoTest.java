package com.querydsl.example.dao;

import static org.junit.Assert.*;

import com.querydsl.example.dto.Person;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;

public class PersonDaoTest extends AbstractDaoTest {

  @Resource PersonDao personDao;

  @Test
  public void findAll() {
    List<Person> persons = personDao.findAll();
    assertFalse(persons.isEmpty());
  }

  @Test
  public void findById() {
    assertNotNull(personDao.findById(1));
  }

  @Test
  public void update() {
    Person person = personDao.findById(1);
    personDao.save(person);
  }

  @Test
  public void delete() {
    Person person = new Person();
    person.setEmail("john@acme.com");
    personDao.save(person);
    assertNotNull(person.getId());
    personDao.delete(person);
    assertNull(personDao.findById(person.getId()));
  }
}
