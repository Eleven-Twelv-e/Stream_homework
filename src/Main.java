import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
       Long countOfMinors = findMinors(persons);
        System.out.println("Number of minors -> "+countOfMinors);
        System.out.println("List of conscripts -> "+findConscripts(persons));
        System.out.println("List of able-bodied people -> "+collectAbleBodiedPeople(persons));

    }

    public static long findMinors(Collection<Person> persons){
      long count = persons.stream()
               .filter(x -> x.getAge()  < 18)
               .count();
       return count;
    }

    public static List<String> findConscripts(Collection<Person> persons){
        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27 && x.getSex().equals(Sex.MAN))
                .map(Person::toString)
                .collect(Collectors.toList());
        return conscripts;
    }

    public static List<Person> collectAbleBodiedPeople(Collection<Person> persons){
        List<Person> ableBodiedPeople = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        return ableBodiedPeople;
    }
}
