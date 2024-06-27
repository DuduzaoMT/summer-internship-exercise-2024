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
  public void PersonOneChildTest_M() {
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
	public void PersonOneChildTest_F() {
		Person person = new Person(
        "John",
        'F',
        new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "mother of Holy";
    assertEquals(result, expected);
	}

  @Test
  public void PersonOneGenerationTest() {
    Person person = new Person(
        "Joaquim",
        'M',
        new Person[] {
                new Person("Eduardo", 'M', null, LocalDateTime.of(1999, 1, 1, 0, 0)),
                new Person("Ana", 'F', null, LocalDateTime.of(2008, 1, 1, 0, 0)),
                new Person("Anabela", 'F', null, LocalDateTime.of(2007, 1, 1, 0, 0)),
        },LocalDateTime.of(1934, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Eduardo";
    assertEquals(expected, result);
  }

  @Test
  public void PersonOneGenerationTest_v2() {
    Person person = new Person(
        "Roberto",
        'M',
        new Person[]{
                new Person("Ze", 'M', null, LocalDateTime.of(1945, 1, 1, 0, 0)),
                new Person("Ana", 'F', null, LocalDateTime.of(1945, 2, 1, 0, 0))
        },LocalDateTime.of(1836, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Ze";
    assertEquals(expected, result);
  }

  @Test
  public void PersonTwoGenerationTest() {
    Person person = new Person(
        "Helio",
        'M',
        new Person[] {
                new Person("Evandra", 'F', new Person[] {
                        new Person("José", 'M', null, LocalDateTime.of(2013, 1, 1, 0, 0)),
                        new Person("Jorge", 'M', null, LocalDateTime.of(2014, 1, 1, 0, 0)),
                        new Person("Filipe", 'F', null, LocalDateTime.of(2019, 1, 1, 0, 0))
                }, LocalDateTime.of(1978, 1, 1, 0, 0)),
                new Person("Bartolomeu", 'M', new Person[] {
                        new Person("Hilda", 'F', null, LocalDateTime.of(2012, 1, 1, 0, 0)),
              }, LocalDateTime.of(1956, 1, 1, 0, 0)),
        },LocalDateTime.of(1912, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Hilda";
    assertEquals(expected, result);
  }

  @Test
  public void PersonTwoGenerationTest_v2() {
    Person person = new Person(
        "Ze",
        'M',
        new Person[]{
                new Person("Joana", 'F', null, LocalDateTime.of(2001, 1, 1, 0, 0)),
                new Person("Ana", 'F', null, LocalDateTime.of(2002, 1, 1, 0, 0)),
                new Person("Joaquim", 'M',new Person[]{
                      new Person("Ana", 'F', null, LocalDateTime.of(2010, 1, 1, 0, 0)),
                      new Person("Francisca", 'F', null, LocalDateTime.of(2012, 1, 1, 0, 0)),
                      new Person("Roberto", 'M', null, LocalDateTime.of(2014, 1, 1, 0, 0))
                },LocalDateTime.of(2000, 1, 1, 0, 0)),
        },LocalDateTime.of(1945, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Ana";
    assertEquals(expected, result);
  }

  @Test
  public void PersonTwoGenerationTest_v3() {
    Person person = new Person(
        "Joao",
        'M',
        new Person[]{
            new Person("Eduardo",'M', new Person[]{
                    new Person("Joana",'F', null, LocalDateTime.of(2001, 1, 1, 0, 0)) 
            }, LocalDateTime.of(1990, 1, 1, 0, 0)),
            new Person("Alexandra",'F', null, LocalDateTime.of(1991, 1, 1, 0, 0)),
            new Person("Eduarda",'F', new Person[]{
                    new Person("Ana",'F', null, LocalDateTime.of(2002, 1, 1, 0, 0)) 
            }, LocalDateTime.of(1992, 1, 1, 0, 0)) 
        }, LocalDateTime.of(1985, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Joana";
    assertEquals(result, expected);
  }

  @Test
  public void PersonThreeGenerationTest() {
    Person person = new Person(
        "Irell",
        'F',
        new Person[] {
                new Person("Eduardo", 'M', new Person[] {
                        new Person("Filmena", 'F', new Person[] {
                                new Person("Joao", 'M', null, LocalDateTime.of(2035, 1, 1, 0, 0)),
                                new Person("Joana", 'F', null, LocalDateTime.of(2022, 1, 1, 0, 0))
                        }, LocalDateTime.of(2015, 1, 1, 0, 0))
                }, LocalDateTime.of(2000, 1, 1, 0, 0)),
                new Person("Eduarda", 'F', new Person[] {
                        new Person("Ze", 'M', new Person[] {
                                new Person("Filipe", 'M', null, LocalDateTime.of(2015, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0))
                }, LocalDateTime.of(1975, 1, 1, 0, 0))
        },LocalDateTime.of(1960, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandmother of Filipe";
    assertEquals(expected, result);
  }

  @Test
  public void PersonThreeGenerationTest_v2() {
    Person person = new Person(
        "Antonio",
        'M',
        new Person[]{
                new Person("Madalena", 'F',new Person[]{
                        new Person("Ana", 'F', null, LocalDateTime.of(1980, 1, 1, 0, 0)),
                        new Person("Manuela", 'F', null, LocalDateTime.of(1982, 1, 1, 0, 0)),
                        new Person("Antonio", 'M', null, LocalDateTime.of(1982, 1, 1, 0, 0))
                }, LocalDateTime.of(1849, 1, 1, 0, 0)),
                new Person("Dalia", 'F', new Person[]{
                        new Person("Marcio", 'M', null, LocalDateTime.of(1983, 1, 1, 0, 0)),
                        new Person("Artur", 'M', null, LocalDateTime.of(1985, 1, 1, 0, 0)),
                        new Person("Sofia", 'F', null, LocalDateTime.of(1985, 1, 1, 0, 0))
                }, LocalDateTime.of(1855, 1, 1, 0, 0)),
                new Person("Ana", 'F', new Person[]{
                  new Person("Leandro", 'M', null, LocalDateTime.of(1961, 1, 1, 0, 0)),
                  new Person("Francisca", 'F', new Person[]{
                          new Person("Roberto", 'M', null, LocalDateTime.of(1972, 1, 1, 0, 0)),
                          new Person("Helio", 'M', null, LocalDateTime.of(1988, 1, 1, 0, 0)),
                          new Person("Joana", 'F', null, LocalDateTime.of(1990, 1, 1, 0, 0))
                  }, LocalDateTime.of(1939, 1, 1, 0, 0)),
                  new Person("Alberta", 'F', null, LocalDateTime.of(1970, 1, 1, 0, 0))
                },LocalDateTime.of(1837, 1, 1, 0, 0))
        },LocalDateTime.of(1800, 1, 1, 0, 0));
            
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandfather of Roberto";
    assertEquals(expected, result);
  }

  @Test
  public void PersonFiveDeepGenerationTest() {
    Person person = new Person(
        "Joaquina",
        'F',
        new Person[]{ 
            new Person("Maria",'F', new Person[]{ 
                new Person("Joao",'M', new Person[]{ 
                    new Person("Roberto",'M', new Person[]{ 
                        new Person("Fiona",'F', new Person[]{ 
                            new Person("Alexandra",'F', null, LocalDateTime.of(2020, 1, 1, 0, 0)) 
                        }, LocalDateTime.of(2010, 1, 1, 0, 0)) 
                    }, LocalDateTime.of(2000, 1, 1, 0, 0)),
                    new Person("Manuel",'M', new Person[]{ 
                        new Person("Anuel",'M', null,LocalDateTime.of(2013, 1, 1, 0, 0)) 
                    }, LocalDateTime.of(2001, 1, 1, 0, 0)) 
                }, LocalDateTime.of(1985, 1, 1, 0, 0)) 
            }, LocalDateTime.of(1970, 1, 1, 0, 0)) 
        }, LocalDateTime.of(1945, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-great-grandmother of Alexandra";
    assertEquals(result, expected);
  }


  @Test
  public void PersonWiderThreeGenerationTest() {
    Person person = new Person(
        "Manuel",
        'M',
        new Person[]{
            new Person("Holy",'F', new Person[]{ 
                new Person("Joao",'M', new Person[]{
                    new Person("Maria",'F', null, LocalDateTime.of(2020, 1, 1, 0, 0))
                }, LocalDateTime.of(2001, 1, 1, 0, 0))
            }, LocalDateTime.of(1990, 1, 1, 0, 0)),
            new Person("David",'M', new Person[]{ 
                new Person("Roberto",'M', new Person[]{ 
                    new Person("Joaquim",'M', null, LocalDateTime.of(2019, 1, 1, 0, 0)),
                    new Person("Fiona",'F', null, LocalDateTime.of(2020, 1, 1, 0, 0))
                }, LocalDateTime.of(2000, 1, 1, 0, 0))
            }, LocalDateTime.of(1995, 1, 1, 0, 0)),
        }, LocalDateTime.of(1974, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandfather of Joaquim";
    assertEquals(result, expected);
  }

  @Test
  public void PersonFourDeepGenerationTest() {
    Person personWithGreatGreatGrandchild = new Person(
        "Joao",
        'M',
        new Person[] {
                new Person("Ze", 'M', new Person[] {
                        new Person("Manuel", 'M', new Person[] {
                                new Person("Raul", 'M', new Person[] {
                                        new Person("Filomena", 'F', null, LocalDateTime.of(2022, 1, 1, 0, 0))
                                }, LocalDateTime.of(2012, 1, 1, 0, 0))
                        }, LocalDateTime.of(2008, 1, 1, 0, 0)),
                        new Person("Manuela", 'F', new Person[] {
                                new Person("Joao", 'M', new Person[] {
                                        new Person("Ana", 'F', null, LocalDateTime.of(2023, 1, 1, 0, 0))
                                }, LocalDateTime.of(2013, 1, 1, 0, 0))
                        }, LocalDateTime.of(2006, 1, 1, 0, 0))
                }, LocalDateTime.of(1975, 1, 1, 0, 0))
        },LocalDateTime.of(1945, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(personWithGreatGreatGrandchild);
    String expected = "great-great-grandfather of Filomena";
    assertEquals(expected, result);
  }

  @Test
  public void PersonWiderSixGenerationTest() {
    Person person = new Person(
        "Beatriz",
        'F',
        new Person[] {
                new Person("Miguel", 'M', new Person[] {
                        new Person("António", 'M', new Person[] {
                                  new Person("João", 'M', null, LocalDateTime.of(2047, 1, 1, 0, 0))
                        }, LocalDateTime.of(2018, 1, 1, 0, 0))
                }, LocalDateTime.of(2001, 1, 1, 0, 0)),
                new Person("Joana", 'F', new Person[] {
                        new Person("Joao", 'M', new Person[] {
                                new Person("Rito", 'M', new Person[] {
                                        new Person("Clara", 'M', new Person[] {
                                                new Person("Igor", 'M', new Person[] {
                                                        new Person("Paulo", 'M', null,LocalDateTime.of(2200, 1, 1, 0, 0))
                                                },LocalDateTime.of(2140, 1, 1, 0, 0))
                                        }, LocalDateTime.of(2110, 1, 1, 0, 0)),
                                        new Person("Ortence", 'F', new Person[] {
                                                new Person("Florinda", 'M', new Person[] {
                                                        new Person("Paula", 'F', null,LocalDateTime.of(2202, 1, 1, 0, 0))
                                                },LocalDateTime.of(2140, 1, 1, 0, 0))
                                  }, LocalDateTime.of(2110, 1, 1, 0, 0))
                                }, LocalDateTime.of(2076, 1, 1, 0, 0))
                        }, LocalDateTime.of(2034, 1, 1, 0, 0)),
                        new Person("Pedro", 'M', null, LocalDateTime.of(2026, 1, 1, 0, 0))
                }, LocalDateTime.of(1999, 1, 1, 0, 0)),
                new Person("Sofia", 'F', null, LocalDateTime.of(2007, 1, 1, 0, 0))
        }, LocalDateTime.of(1970, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-great-great-grandmother of Paulo";
    assertEquals(expected, result);
  }

  @Test
  public void PersonFullTest() {
    Person person = new Person(
        "João",
        'M',
        new Person[]{
                new Person("Helena", 'F', new Person[]{
                        new Person("Paulo", 'M', new Person[]{
                              new Person("Cíntia", 'F', null, LocalDateTime.of(1993, 1, 1, 0, 0)),
                              new Person("Margarida", 'F', null, LocalDateTime.of(1994, 1, 1, 0, 0)),
                              new Person("Tiffany", 'F', null, LocalDateTime.of(1995, 1, 1, 0, 0))
                        }, LocalDateTime.of(1969, 1, 1, 0, 0)),
                        new Person("Henrique", 'M', new Person[]{
                                new Person("Daisy", 'F', new Person[]{
                                        new Person("Margarida", 'F', null, LocalDateTime.of(1997, 1, 1, 1, 0)),
                                        new Person("Cássia", 'F', null, LocalDateTime.of(1997, 1, 1, 0, 0)),
                                        new Person("Fanny", 'F', null, LocalDateTime.of(1999, 1, 1, 0, 0)),
                                },LocalDateTime.of(1990, 1, 1, 0, 0)),
                                new Person("Lúcia", 'F', null, LocalDateTime.of(1991, 1, 1, 0, 0)),
                                new Person("Márcia", 'F', null, LocalDateTime.of(1992, 1, 1, 0, 0)),
                                new Person("Ana", 'F', null, LocalDateTime.of(1989, 1, 1, 0, 0)),
                        }, LocalDateTime.of(1967, 1, 1, 0, 0)),
                        new Person("Francisco", 'M',new Person[]{
                                new Person("Hortênsia", 'F', null, LocalDateTime.of(1996, 1, 1, 0, 0))
                        },LocalDateTime.of(1968, 1, 1, 0, 0))
                },LocalDateTime.of(1946, 1, 1, 0, 0)),
                new Person("Dolores", 'F', new Person[]{
                        new Person("Vanessa", 'F', null, LocalDateTime.of(1976, 1, 1, 0, 0))
                }, LocalDateTime.of(1948, 1, 1, 0, 0)),
                new Person("Micaela", 'F', new Person[]{
                        new Person("Belinda", 'F', new Person[]{
                                new Person("Ana", 'F', null, LocalDateTime.of(1998, 1, 1, 0, 0)),
                                new Person("Brenda", 'F', null, LocalDateTime.of(2000, 1, 1, 0, 0))
                        }, LocalDateTime.of(1970, 1, 1, 0, 0)),
                        new Person("Mirela", 'F', new Person[]{
                                new Person("Ema", 'F', new Person[]{
                                        new Person("Zeca", 'F', null, LocalDateTime.of(2010, 1, 1, 0, 0))
                                }, LocalDateTime.of(2001, 1, 1, 0, 0)),
                                new Person("Gemma", 'F', null, LocalDateTime.of(2002, 1, 1, 0, 0))
                        },LocalDateTime.of(1971, 1, 1, 0, 0)),
                        new Person("Lívia", 'F',new Person[]{
                                new Person("Júlia", 'F', new Person[]{
                                        new Person("Rosa", 'F', new Person[]{
                                                new Person("Juliana", 'F',new Person[]{
                                                        new Person("Marco", 'M', null, LocalDateTime.of(2030, 1, 1, 0, 0))
                                                }, LocalDateTime.of(2020, 1, 1, 0, 0)),
                                                new Person("Sofia", 'F', null, LocalDateTime.of(2021, 1, 1, 0, 0))
                                        }, LocalDateTime.of(2010, 1, 1, 0, 0))
                                },LocalDateTime.of(2004, 1, 1, 0, 0)),
                                new Person("Lívia", 'F', null, LocalDateTime.of(2014, 1, 1, 0, 0))
                        },LocalDateTime.of(1972, 1, 1, 0, 0))
                  }, LocalDateTime.of(1947, 1, 1, 0, 0))
        }, LocalDateTime.of(1946, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-great-great-grandfather of Marco";
    assertEquals(expected, result);
  }

}