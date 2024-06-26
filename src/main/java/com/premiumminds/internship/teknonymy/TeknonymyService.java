package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// 0 - proprio
// profundidade maxima = 1 -> relação de pais 
// profundidade maxima = 2 -> relação de avos (+grand)
// profundidade maxima = 3 -> relação de bis (+great)

// distancia de geração = nº da geração

// maximo = (distancia de geração,nome do sucessor,agregado de nomes)
class TeknonymyService implements ITeknonymyService {

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name 
   */
  public String getTeknonymy(Person person) {
    String prefix = "";

    if (person.children() == null || person.children().length == 0) {
      return ""; // Se não tiver filhos, a profundidade é 0
    }
    // Male case
    if (person.sex() == 'M'){
      prefix = "father of ";
    }
    // Female case
    else if (person.sex() == 'F'){
      prefix = "mother of ";
    }
    return DFS(person,prefix);
  }

  public String DFS(Person person,String sufix){

    // inicializações
    Map<Integer, String> prefixes = new HashMap<>() {{
        put(1, "");
        put(2, "grand");
    }};

    String prefix = "";
    int profundidadeMaxima = 0;
    Person Pessoa_mais_distante = person;
    Deque<Object[]> deque = new ArrayDeque<>();
    deque.addLast(new Object[]{person,0});

    // nao tem filhos
    if (person.children() == null){
      return "";
    }

    while (!deque.isEmpty()) {
      Object[] tuple =  deque.removeLast();
      Person currentPerson = (Person) tuple[0];
      Integer depth = (Integer) tuple[1];

      // se a profundidade for maior ficamos com o novo
      if (depth > profundidadeMaxima){
        if (depth >= 3){
          prefix = "great-" + prefix;
        }
        else{
          prefix += prefixes.get(depth);
        }
        profundidadeMaxima = depth;
        Pessoa_mais_distante = currentPerson;
      }
      // ficamos com o mais velho neste caso
      else if (depth == profundidadeMaxima && currentPerson.dateOfBirth().compareTo(Pessoa_mais_distante.dateOfBirth()) < 0){
        Pessoa_mais_distante = currentPerson;
      }

      if (currentPerson.children() != null) {
          for (Person child : currentPerson.children()) {
              deque.addLast(new Object[]{child, depth+1});
          }
      }
    }
    return prefix + sufix + Pessoa_mais_distante.name();
  }
}
