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
  @Test
	public void Test0() {
		// Generation 1
		Person person1 = new Person("Person1", 'F', null, LocalDateTime.of(1980, 6, 28, 0, 0));
		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "";
		assertEquals(expected, result);
	}

	@Test
	public void Test1() {
		// Generation 2
		Person person2 = new Person("Person2", 'M', null, LocalDateTime.of(1918, 8, 5, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'F', new Person[] { person2 }, LocalDateTime.of(1920, 6, 28, 0, 0));

		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "mother of Person2";
		assertEquals(expected, result);
	}

	@Test
	public void Test2() {
		// Generation 2
		Person person2 = new Person("Person2", 'M', null, LocalDateTime.of(1918, 8, 5, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'M', new Person[] { person2 }, LocalDateTime.of(1920, 6, 28, 0, 0));
		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "father of Person2";
		assertEquals(expected, result);
	}

	@Test
	public void Test3() {
		// Generation 3
		Person person4 = new Person("Person4", 'F', null, LocalDateTime.of(1955, 4, 30, 0, 0));
		Person person3 = new Person("Person3", 'M', null, LocalDateTime.of(1952, 10, 15, 0, 0));

		// Generation 2
		Person person2 = new Person("Person2", 'M', new Person[] { person3, person4 },
				LocalDateTime.of(1918, 8, 5, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'M', new Person[] { person2 }, LocalDateTime.of(1920, 6, 28, 0, 0));

		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "grandfather of Person3";
		assertEquals(expected, result);
	}

	@Test
	public void Test4() {
		// Generation 3
		Person person7 = new Person("Person7", 'F', null, LocalDateTime.of(1955, 4, 30, 0, 0));
		Person person6 = new Person("Person6", 'M', null, LocalDateTime.of(1952, 10, 15, 0, 0));

		// Generation 2
		Person person5 = new Person("Person5", 'M', new Person[] { person7 }, LocalDateTime.of(1918, 8, 5, 0, 0));
		Person person4 = new Person("Person4", 'F', null, LocalDateTime.of(1922, 3, 18, 0, 0));
		Person person3 = new Person("Person3", 'M', null, LocalDateTime.of(1940, 12, 10, 0, 0));
		Person person2 = new Person("Person2", 'M', new Person[] { person6 }, LocalDateTime.of(1938, 4, 15, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'F', new Person[] { person2, person3, person4, person5 },
				LocalDateTime.of(1920, 6, 28, 0, 0));

		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "grandmother of Person6";
		assertEquals(expected, result);
	}

	@Test
	public void Test5() {
		// Generation 4
		Person person10 = new Person("Person10", 'M', null, LocalDateTime.of(1995, 2, 28, 0, 0));
		Person person9 = new Person("Person9", 'F', null, LocalDateTime.of(1990, 5, 14, 0, 0));
		Person person8 = new Person("Person8", 'F', null, LocalDateTime.of(1998, 7, 20, 0, 0));

		// Generation 3
		Person person7 = new Person("Person7", 'F', new Person[] { person9 }, LocalDateTime.of(1955, 4, 30, 0, 0));
		Person person6 = new Person("Person6", 'M', new Person[] { person8, person10 },
				LocalDateTime.of(1952, 10, 15, 0, 0));

		// Generation 2
		Person person5 = new Person("Person5", 'M', new Person[] { person7 }, LocalDateTime.of(1918, 8, 5, 0, 0));
		Person person4 = new Person("Person4", 'F', null, LocalDateTime.of(1922, 3, 18, 0, 0));
		Person person3 = new Person("Person3", 'M', null, LocalDateTime.of(1940, 12, 10, 0, 0));
		Person person2 = new Person("Person2", 'M', new Person[] { person6 }, LocalDateTime.of(1938, 4, 15, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'F', new Person[] { person2, person3, person4, person5 },
				LocalDateTime.of(1920, 6, 28, 0, 0));

		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "great-grandmother of Person9";
		assertEquals(expected, result);
	}

	@Test
	public void Test6() {

		// Generation 4
		Person person9 = new Person("Person9", 'M', null, LocalDateTime.of(1978, 6, 18, 0, 0));
		Person person8 = new Person("Person8", 'M', null, LocalDateTime.of(1981, 9, 5, 0, 0));

		// Generation 3
		Person person7 = new Person("Person7", 'F', null, LocalDateTime.of(1955, 4, 30, 0, 0));
		Person person6 = new Person("Person6", 'M', new Person[] { person8, person9 },
				LocalDateTime.of(1952, 10, 15, 0, 0));

		// Generation 2
		Person person5 = new Person("Person5", 'M', new Person[] { person7 }, LocalDateTime.of(1918, 8, 5, 0, 0));
		Person person4 = new Person("Person4", 'F', null, LocalDateTime.of(1922, 3, 18, 0, 0));
		Person person3 = new Person("Person3", 'M', null, LocalDateTime.of(1940, 12, 10, 0, 0));
		Person person2 = new Person("Person2", 'M', new Person[] { person6 }, LocalDateTime.of(1938, 4, 15, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'M', new Person[] { person2, person3, person4, person5 },
				LocalDateTime.of(1920, 6, 28, 0, 0));

		String result = new TeknonymyService().getTeknonymy(person1);
		String expected = "great-grandfather of Person9";
		assertEquals(expected, result);
	}

	@Test
	public void Test7() {
		// Generation 10
		Person person32 = new Person("Person32", 'M', null, LocalDateTime.of(2075, 2, 28, 0, 0));
		Person person31 = new Person("Person31", 'F', null, LocalDateTime.of(2078, 5, 14, 0, 0));
		Person person30 = new Person("Person30", 'F', null, LocalDateTime.of(2083, 7, 20, 0, 0));
		Person person29 = new Person("Person29", 'M', null, LocalDateTime.of(2070, 11, 8, 0, 0));

		// Generation 9
		Person person28 = new Person("Person28", 'M', null, LocalDateTime.of(2040, 4, 25, 0, 0));
		Person person27 = new Person("Person27", 'F', null, LocalDateTime.of(2043, 9, 30, 0, 0));
		Person person26 = new Person("Person26", 'F', null, LocalDateTime.of(2048, 12, 15, 0, 0));
		Person person25 = new Person("Person25", 'M', new Person[] { person29, person30, person31, person32 },
				LocalDateTime.of(2035, 6, 20, 0, 0));

		// Generation 8
		Person person24 = new Person("Person24", 'M', null, LocalDateTime.of(2010, 2, 15, 0, 0));
		Person person23 = new Person("Person23", 'F', new Person[] { person26, person28 },
				LocalDateTime.of(2013, 8, 20, 0, 0));
		Person person22 = new Person("Person22", 'F', null, LocalDateTime.of(2018, 5, 10, 0, 0));
		Person person21 = new Person("Person21", 'M', new Person[] { person25, person27 },
				LocalDateTime.of(2005, 10, 5, 0, 0));

		// Generation 7
		Person person20 = new Person("Person20", 'M', new Person[] { person24 }, LocalDateTime.of(1990, 2, 28, 0, 0));
		Person person19 = new Person("Person19", 'F', null, LocalDateTime.of(1993, 5, 14, 0, 0));
		Person person18 = new Person("Person18", 'F', new Person[] { person21, person22, person23 },
				LocalDateTime.of(1998, 7, 20, 0, 0));

		// Generation 6
		Person person17 = new Person("Person17", 'F', null, LocalDateTime.of(1995, 9, 8, 0, 0));
		Person person16 = new Person("Person16", 'M', new Person[] { person18 }, LocalDateTime.of(1984, 11, 3, 0, 0));
		Person person15 = new Person("Person15", 'F', new Person[] { person19, person20 },
				LocalDateTime.of(1983, 8, 15, 0, 0));

		// Generation 5
		Person person14 = new Person("Person14", 'M', null, LocalDateTime.of(1991, 1, 20, 0, 0));
		Person person13 = new Person("Person13", 'F', null, LocalDateTime.of(2015, 7, 12, 0, 0));
		Person person12 = new Person("Person12", 'M', null, LocalDateTime.of(2018, 10, 30, 0, 0));
		Person person11 = new Person("Person11", 'F', new Person[] { person16, person17 },
				LocalDateTime.of(1994, 3, 8, 0, 0));
		Person person10 = new Person("Person10", 'F', new Person[] { person15 }, LocalDateTime.of(1985, 9, 25, 0, 0));

		// Generation 4
		Person person9 = new Person("Person9", 'M', new Person[] { person11, person13, person14 },
				LocalDateTime.of(1978, 6, 18, 0, 0));
		Person person8 = new Person("Person8", 'M', new Person[] { person10, person12 },
				LocalDateTime.of(1981, 9, 5, 0, 0));

		// Generation 3
		Person person7 = new Person("Person7", 'F', null, LocalDateTime.of(1955, 4, 30, 0, 0));
		Person person6 = new Person("Person6", 'M', new Person[] { person8, person9 },
				LocalDateTime.of(1952, 10, 15, 0, 0));

		// Generation 2
		Person person5 = new Person("Person5", 'M', new Person[] { person7 }, LocalDateTime.of(1918, 8, 5, 0, 0));
		Person person4 = new Person("Person4", 'F', null, LocalDateTime.of(1922, 3, 18, 0, 0));
		Person person3 = new Person("Person3", 'M', null, LocalDateTime.of(1940, 12, 10, 0, 0));
		Person person2 = new Person("Person2", 'M', new Person[] { person6 }, LocalDateTime.of(1938, 4, 15, 0, 0));

		// Generation 1
		Person person1 = new Person("Person1", 'M', new Person[] { person2, person3, person4, person5 },
				LocalDateTime.of(1920, 6, 28, 0, 0));

//		long initialTime = System.nanoTime();
		String result = new TeknonymyService().getTeknonymy(person1);
//		long endTime = System.nanoTime() - initialTime;
//		System.out.println("Total Time: " + endTime);
		String expected = "great-great-great-great-great-great-great-grandfather of Person29";
		assertEquals(expected, result);
	}

	@Test
	public void Test8() {
		// Generation 15
		Person person14 = new Person("Person14", 'M', null, LocalDateTime.of(1991, 1, 20, 0, 0));
		// Generation 14
		Person person13 = new Person("Person13", 'F', new Person[] { person14 }, LocalDateTime.of(2015, 7, 12, 0, 0));
		// Generation 12
		Person person12 = new Person("Person12", 'M', new Person[] { person13 }, LocalDateTime.of(2018, 10, 30, 0, 0));
		// Generation 11
		Person person11 = new Person("Person11", 'F', new Person[] { person12 }, LocalDateTime.of(1994, 3, 8, 0, 0));
		// Generation 10
		Person person10 = new Person("Person10", 'F', new Person[] { person11 }, LocalDateTime.of(1985, 9, 25, 0, 0));
		// Generation 9
		Person person9 = new Person("Person9", 'M', new Person[] { person10 }, LocalDateTime.of(1978, 6, 18, 0, 0));
		// Generation 8
		Person person8 = new Person("Person8", 'M', new Person[] { person9 }, LocalDateTime.of(1981, 9, 5, 0, 0));
		// Generation 7
		Person person7 = new Person("Person7", 'F', new Person[] { person8 }, LocalDateTime.of(1955, 4, 30, 0, 0));
		// Generation 6
		Person person6 = new Person("Person6", 'M', new Person[] { person7 }, LocalDateTime.of(1952, 10, 15, 0, 0));
		// Generation 5
		Person person5 = new Person("Person5", 'M', new Person[] { person6 }, LocalDateTime.of(1918, 8, 5, 0, 0));
		// Generation 4
		Person person4 = new Person("Person4", 'F', new Person[] { person5 }, LocalDateTime.of(1922, 3, 18, 0, 0));
		// Generation 3
		Person person3 = new Person("Person3", 'M', new Person[] { person4 }, LocalDateTime.of(1940, 12, 10, 0, 0));
		// Generation 2
		Person person2 = new Person("Person2", 'M', new Person[] { person3 }, LocalDateTime.of(1938, 4, 15, 0, 0));
		// Generation 1
		Person person1 = new Person("Person1", 'F', new Person[] { person2 }, LocalDateTime.of(1920, 6, 28, 0, 0));

//		long initialTime = System.nanoTime();
		String result = new TeknonymyService().getTeknonymy(person1);
//		long endTime = System.nanoTime() - initialTime;
//		System.out.println("Total Time: " + endTime);
		String expected = "great-great-great-great-great-great-great-great-great-great-great-grandmother of Person14";
		assertEquals(expected, result);
	}
  @Test
    public void PersonMultipleChildrenTest() {
        Person personWithTwoChildren = new Person(
                "João",
                'M',
                new Person[] {
                        new Person("Vasco", 'M', null, LocalDateTime.of(2000, 1, 1, 0, 0)),
                        new Person("Carol", 'F', null, LocalDateTime.of(2002, 1, 1, 0, 0))
                },
                LocalDateTime.of(1975, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(personWithTwoChildren);
        String expected = "father of Vasco";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithMultipleChildrenAndGrandChildren() {
        Person personWithThreeGrandchildren = new Person(
                "David",
                'M',
                new Person[] {
                        new Person("Eva", 'F', new Person[] {
                                new Person("José", 'M', null, LocalDateTime.of(2020, 1, 1, 0, 0)),
                                new Person("Graça", 'F', null, LocalDateTime.of(2022, 1, 1, 0, 0)),
                                new Person("Maria", 'F', null, LocalDateTime.of(2024, 1, 1, 0, 0))
                        }, LocalDateTime.of(1990, 1, 1, 0, 0)),
                        new Person("Ricardo", 'M', null, LocalDateTime.of(1989, 1, 1, 0, 0)),
                },
                LocalDateTime.of(1965, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(personWithThreeGrandchildren);
        String expected = "grandfather of José";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithGreatGrandChild() {
        Person personWithGreatGrandchild = new Person(
                "Irene",
                'F',
                new Person[] {
                        new Person("Vasco", 'M', new Person[] {
                                new Person("Carla", 'F', new Person[] {
                                        new Person("Leandro", 'M', null, LocalDateTime.of(2050, 1, 1, 0, 0)),
                                        new Person("Marta", 'F', null, LocalDateTime.of(2052, 1, 1, 0, 0))
                                }, LocalDateTime.of(2025, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0))
                },
                LocalDateTime.of(1970, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(personWithGreatGrandchild);
        String expected = "great-grandmother of Leandro";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithGreatGreatGrandchild() {
        Person personWithGreatGreatGrandchild = new Person(
                "Nancy",
                'F',
                new Person[] {
                        new Person("Oscar", 'M', new Person[] {
                                new Person("Paul", 'M', new Person[] {
                                        new Person("Quinn", 'F', new Person[] {
                                                new Person("Ruby", 'F', null, LocalDateTime.of(2075, 1, 1, 0, 0))
                                        }, LocalDateTime.of(2050, 1, 1, 0, 0))
                                }, LocalDateTime.of(2025, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0))
                },
                LocalDateTime.of(1975, 1, 1, 0, 0));

        String result = new TeknonymyService().getTeknonymy(personWithGreatGreatGrandchild);
        String expected = "great-great-grandmother of Ruby";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithBigFamilyTree() {
        // Confusa esta árvore

        // Matilde tem 3 filhos: Lara, Mario e Nicole
        // Lara tem 2 filhos: Maria, Pedro
        // Pedro tem 0 fihos
        // Maria tem 1 filha: Patricia
        // Patricia tem 1 filha: Clara
        // Clara tem 1 filha: Iris
        // Iris tem 1 filha: Paula
        // Mario tem 1 filho: João
        // João tem 1 filho: Paula
        // Nicole tem 0 filhos

        Person personWithDeepFamilyTree = new Person(
                "Matilde",
                'F',
                new Person[] {
                        new Person("Lara", 'F', new Person[] {
                                new Person("Maria", 'F', new Person[] {

                                        new Person("Patricia", 'F', new Person[] {

                                                new Person("Clara", 'M', new Person[] {

                                                        new Person("Iris", 'F', new Person[] {
                                                                new Person("Paula", 'F', null,
                                                                        LocalDateTime.of(2125, 1, 1, 0, 0))
                                                        },
                                                                LocalDateTime.of(2100, 1, 1, 0, 0))
                                                }, LocalDateTime.of(2075, 1, 1, 0, 0))
                                        }, LocalDateTime.of(2050, 1, 1, 0, 0))
                                }, LocalDateTime.of(2025, 1, 1, 0, 0)),

                                new Person("Pedro", 'M', null, LocalDateTime.of(2030, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0)),

                        new Person("Mario", 'M', new Person[] {
                                new Person("João", 'M', new Person[] {
                                        new Person("Paula", 'F', null, LocalDateTime.of(2045, 1, 1, 0, 0))
                                }, LocalDateTime.of(2020, 1, 1, 0, 0))
                        }, LocalDateTime.of(2002, 1, 1, 0, 0)),

                        new Person("Nicole", 'F', null, LocalDateTime.of(2005, 1, 1, 0, 0))
                },
                LocalDateTime.of(1970, 1, 1, 0, 0));

        String result = new TeknonymyService().getTeknonymy(personWithDeepFamilyTree);
        String expected = "great-great-great-great-grandmother of Paula";
        assertEquals(expected, result);
    }
    @Test
    public void PersonTwoChildTest() {
        Person person = new Person(
                "John",
                'M',
                new Person[]{
                        new Person("Holy", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
                        new Person("Molly", 'F', null, LocalDateTime.of(1047, 1, 1, 0, 0))
                },
                LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        String expected = "father of Holy";
        assertEquals(expected, result);
    }

    @Test
    public void PersonThreeChildTest() {
        Person person = new Person(
                "John",
                'M',
                new Person[]{
                        new Person("Holy", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
                        new Person("Molly", 'F', null, LocalDateTime.of(1047, 1, 1, 0, 0)),
                        new Person("Dolly", 'F', null, LocalDateTime.of(1048, 1, 1, 0, 0)),
                },
                LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        String expected = "father of Holy";
        assertEquals(expected, result);
    }

    @Test
    public void PersonThreeChildThreeGrandchildTest() {
        Person person = new Person(
                "John",
                'M',
                new Person[]{
                        new Person("Holy", 'F',
                                new Person[]{
                                        new Person("Hanz", 'F', null, LocalDateTime.of(1067, 1, 1, 0, 0)),
                                        new Person("Franz", 'F', null, LocalDateTime.of(1068, 1, 1, 0, 0)),
                                        new Person("Polibanz", 'F', null, LocalDateTime.of(1069, 1, 1, 0, 0))},
                                LocalDateTime.of(1046, 1, 1, 0, 0)),
                        new Person("Molly", 'F', null, LocalDateTime.of(1047, 1, 1, 0, 0)),
                        new Person("Dolly", 'F', null, LocalDateTime.of(1048, 1, 1, 0, 0)),
                },
                LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        String expected = "grandfather of Hanz";
        assertEquals(expected, result);
    }

    @Test
    public void PersonThreeChild6GrandchildTest() {
        Person person = new Person(
                "John",
                'M',
                new Person[]{
                        new Person("Holy", 'F',
                                new Person[]{
                                        new Person("Hanz", 'M', null, LocalDateTime.of(1067, 1, 1, 0, 0)),
                                        new Person("Franz", 'M', null, LocalDateTime.of(1068, 1, 1, 0, 0)),
                                        new Person("Polibanz", 'M', null, LocalDateTime.of(1069, 1, 1, 0, 0))},
                                LocalDateTime.of(1046, 1, 1, 0, 0)),
                        new Person("Molly", 'F',
                                new Person[]{
                                        new Person("Billy", 'F', null, LocalDateTime.of(1070, 1, 1, 0, 0)),
                                        new Person("Milly", 'F', null, LocalDateTime.of(1071, 1, 1, 0, 0)),
                                        new Person("Lilly", 'F', null, LocalDateTime.of(1072, 1, 1, 0, 0))},
                                LocalDateTime.of(1047, 1, 1, 0, 0)),
                        new Person("Dolly", 'F',
                                new Person[]{
                                        new Person("Rosie", 'F', null, LocalDateTime.of(1073, 1, 1, 0, 0)),
                                        new Person("Julie", 'F', null, LocalDateTime.of(1074, 1, 1, 0, 0)),
                                        new Person("Sophie", 'F', null, LocalDateTime.of(1075, 1, 1, 0, 0))},
                                LocalDateTime.of(1048, 1, 1, 0, 0)),
                },
                LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        String expected = "grandfather of Hanz";
        assertEquals(expected, result);
    }

    @Test
    public void BigFamily() {
        Person person = new Person(
                "John",
                'M',
                new Person[]{
                        new Person("Holy", 'F',
                                new Person[]{
                                        new Person("Hanz", 'M',
                                                new Person[]{
                                                        new Person("Daisy", 'F',
                                                                new Person[]{
                                                                        new Person("Maggie", 'F', null, LocalDateTime.of(1107, 1, 1, 1, 0)),
                                                                        new Person("Cassie", 'F', null, LocalDateTime.of(1107, 1, 1, 0, 0))},
                                                                LocalDateTime.of(1100, 1, 1, 0, 0)),
                                                        new Person("Lacy", 'F', null, LocalDateTime.of(1101, 1, 1, 0, 0)),
                                                        new Person("Macy", 'F', null, LocalDateTime.of(1102, 1, 1, 0, 0))},
                                                LocalDateTime.of(1067, 1, 1, 0, 0)),
                                        new Person("Franz", 'M',
                                                new Person[]{
                                                        new Person("Hattie", 'F', null, LocalDateTime.of(1106, 1, 1, 0, 0))},
                                                LocalDateTime.of(1068, 1, 1, 0, 0)),
                                        new Person("Polibanz", 'M',
                                                new Person[]{
                                                        new Person("Cindy", 'F', null, LocalDateTime.of(1103, 1, 1, 0, 0)),
                                                        new Person("Mindy", 'F', null, LocalDateTime.of(1104, 1, 1, 0, 0)),
                                                        new Person("Tiffany", 'F', null, LocalDateTime.of(1105, 1, 1, 0, 0))},
                                                LocalDateTime.of(1069, 1, 1, 0, 0))},
                                LocalDateTime.of(1046, 1, 1, 0, 0)),
                        new Person("Molly", 'F',
                                new Person[]{
                                        new Person("Billy", 'F',
                                                new Person[]{
                                                        new Person("Abby", 'F', null, LocalDateTime.of(1108, 1, 1, 0, 0)),
                                                        new Person("Fanny", 'F', null, LocalDateTime.of(1109, 1, 1, 0, 0)),
                                                        new Person("Brandy", 'F', null, LocalDateTime.of(1110, 1, 1, 0, 0))},
                                                LocalDateTime.of(1070, 1, 1, 0, 0)),
                                        new Person("Milly", 'F',
                                                new Person[]{
                                                        new Person("Emma", 'F', null, LocalDateTime.of(1111, 1, 1, 0, 0)),
                                                        new Person("Gemma", 'F', null, LocalDateTime.of(1112, 1, 1, 0, 0))},
                                                LocalDateTime.of(1071, 1, 1, 0, 0)),
                                        new Person("Lilly", 'F',
                                                new Person[]{
                                                        new Person("Giulia", 'F',
                                                                new Person[]{
                                                                        new Person("Rosie", 'F',
                                                                                new Person[]{
                                                                                        new Person("Julie", 'F',
                                                                                                new Person[]{
                                                                                                        new Person("Sophie", 'F', null, LocalDateTime.of(1075, 1, 1, 0, 0))}, LocalDateTime.of(1074, 1, 1, 0, 0)),
                                                                                        new Person("Mark", 'M', null, LocalDateTime.of(1075, 1, 1, 0, 0))}, LocalDateTime.of(1073, 1, 1, 0, 0)),},
                                                                LocalDateTime.of(1113, 1, 1, 0, 0)),
                                                        new Person("Livia", 'F', null, LocalDateTime.of(1114, 1, 1, 0, 0))},
                                                LocalDateTime.of(1072, 1, 1, 0, 0))},
                                LocalDateTime.of(1047, 1, 1, 0, 0)),
                        new Person("Dolly", 'F', null, LocalDateTime.of(1048, 1, 1, 0, 0)),
                },
                LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        String expected = "great-great-great-great-grandfather of Sophie";
        assertEquals(expected, result);
    }
    @Test
  public void PersonChildrenTest() {
    Person person = new Person(
        "Emma",
        'F',
        new Person[]{ 
            new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
            new Person("David",'M', null, LocalDateTime.of(1045, 1, 1, 0, 0)),
            new Person("Anna",'F', null, LocalDateTime.of(1047, 1, 1, 0, 0))
        }, LocalDateTime.of(1026, 1, 1, 0, 0));

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "mother of David";
    assertEquals(result, expected);
  }

  @Test
  public void PersonLevelFiveOneDescendantTest() {
    Person person = new Person(
        "Emma",
        'F',
        new Person[]{ 
            new Person("Holy",'F', new Person[]{ 
                new Person("David",'M', new Person[]{ 
                    new Person("Anna",'F', new Person[]{ 
                        new Person("Fiora",'F', new Person[]{ 
                            new Person("Annie",'F', null, LocalDateTime.of(1050, 1, 1, 0, 0)) 
                        }, LocalDateTime.of(1049, 1, 1, 0, 0)) 
                    }, LocalDateTime.of(1048, 1, 1, 0, 0)) 
                }, LocalDateTime.of(1047, 1, 1, 0, 0)) 
            }, LocalDateTime.of(1046, 1, 1, 0, 0)) 
        }, LocalDateTime.of(1045, 1, 1, 0, 0)
    );

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-great-grandmother of Annie";
    assertEquals(result, expected);
  }

  @Test
  public void PersonLevelTwoOneDescendantTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ 
            new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
            new Person("David",'M', new Person[]{
                new Person("Anna",'F', null, LocalDateTime.of(1100, 1, 1, 0, 0)) 
            }, LocalDateTime.of(1080, 1, 1, 0, 0)) 
        }, LocalDateTime.of(1026, 1, 1, 0, 0)
    );

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Anna";
    assertEquals(result, expected);
  }

  @Test
  public void PersonLevelThreeMultipleDescendantTest() {
    Person person = new Person(
        "Emma",
        'F',
        new Person[]{
            new Person("Holy",'F', new Person[]{ 
                new Person("Anna",'F', new Person[]{
                    new Person("Maria",'F', null, LocalDateTime.of(1099, 1, 1, 0, 0)),
                    new Person("Gabriel",'M', null, LocalDateTime.of(1100, 1, 1, 0, 0))
                }, LocalDateTime.of(1047, 1, 1, 0, 0))
            }, LocalDateTime.of(1047, 1, 1, 0, 0)),
            new Person("David",'M', new Person[]{ 
                new Person("Wei",'M', new Person[]{ 
                    new Person("Garen",'M', null, LocalDateTime.of(1098, 1, 1, 0, 0)),
                    new Person("Fiora",'F', null, LocalDateTime.of(1101, 1, 1, 0, 0))
                }, LocalDateTime.of(1047, 1, 1, 0, 0))
            }, LocalDateTime.of(1047, 1, 1, 0, 0)),
        }, LocalDateTime.of(1026, 1, 1, 0, 0)
    );

    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandmother of Garen";
    assertEquals(result, expected);
  }

}