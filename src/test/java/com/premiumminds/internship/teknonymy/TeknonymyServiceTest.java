package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John",'M',null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }
  @Test
  public void PersonGrandTest() {
    Person person = new Person(
        "John",
        'F',
        new Person[]{ 
          new Person("Holy",'F', 
              new Person[]{ new Person("Ze",'F', 
                              new Person[] {new Person("Manuel",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
                                            new Person("Joaquim",'F', null, LocalDateTime.of(1043, 1, 1, 0, 0))}, 
                              LocalDateTime.of(1060, 1, 1, 0, 0)), 
                            new Person("Jupyter",'F',
                              new Person[] {new Person("Manuel",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
                                            new Person("Ze",'F', null, LocalDateTime.of(1040, 1, 1, 0, 0))}
                        , LocalDateTime.of(1010, 1, 1, 0, 0))}, 
          LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandmother of Ze";
    assertEquals(result, expected);
  }
}