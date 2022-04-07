import classes.Education;
import classes.Person;
import classes.Sex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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


        long numbeOfMinors = persons.stream().filter(p -> p.getAge() < 18).count();

        List<String> recruits = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p->p.getAge()<27)
                .filter(p->p.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());


        List<String> workables = persons.stream()
                        .filter(p -> (p.getSex().equals(Sex.MAN) && p.getAge()<65) || (p.getSex().equals(Sex.WOMAN) && p.getAge()<60)
                        && p.getAge()>=18 && p.getEducation().equals(Education.HIGHER))
                        .sorted(Comparator.comparing(Person::getFamily))
                        .map(Person::getFamily)
                        .collect(Collectors.toList());
    }
}
