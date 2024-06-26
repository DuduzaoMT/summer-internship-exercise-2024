package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;

import java.util.ArrayDeque;
import java.util.Deque;

class TeknonymyService implements ITeknonymyService {

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name 
   */
  public String getTeknonymy(Person person) {

    // If there are no children, the depth is 0
    if (person.children() == null || person.children().length == 0) {
      return "";
    }
    String sufix = person.sex() == 'M' ? "father of " : "mother of ";
    return DFS(person, sufix);

  }

  /**
   * Method to compute the Teknonymy Name based on the deepest person in the genealogical tree
   * with the given person as the root.
   * 
   * @param person Person object representing the root of the tree
   * @param suffix String suffix ("mother of " or "father of ")
   * @return String representing the computed Teknonymy Name
   */
  public String DFS(Person person,String sufix){

    // Prefix builders to construct the Teknonymy Name
    StringBuilder prefixBuilder = new StringBuilder();
    StringBuilder greatprefixBuilder = new StringBuilder();
    int profundidadeMaxima = 0;
    Person pessoaMaisDistante = person;

    // Deque initialization for depth-first search
    Deque<Object[]> deque = new ArrayDeque<>();
    deque.addLast(new Object[]{person, 0});

    while (!deque.isEmpty()) {
        Object[] tuple = deque.removeLast();
        Person currentPerson = (Person) tuple[0];
        int depth = (int) tuple[1];

        // Building the prefix dynamically based on depth
        if (depth > profundidadeMaxima) {

            if (depth == 2) {
              prefixBuilder.append("grand");

            } else if (depth >= 3) {
              // The string is symmetric, meaning it can be constructed in O(1)
              greatprefixBuilder.append("great-");
            }
            profundidadeMaxima = depth;
            pessoaMaisDistante = currentPerson;

        } else if (depth == profundidadeMaxima && currentPerson.dateOfBirth().isBefore(pessoaMaisDistante.dateOfBirth())) {
          // Updating the furthest person based on date of birth if depths are equal
          pessoaMaisDistante = currentPerson;
        }

        // Adding children to the deque
        if (currentPerson.children() != null) {
          for (Person child : currentPerson.children()) {
            deque.addLast(new Object[]{child, depth + 1});
          }
        }
    }
    // Constructing the final Teknonymy Name with appropriate prefix, suffix, and furthest person's name
    greatprefixBuilder.append(prefixBuilder).append(sufix).append(pessoaMaisDistante.name());
    
    return greatprefixBuilder.toString();
  }
}
