package homework8.utils;

import homework8.models.Contact;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@RequiredArgsConstructor
public class ListUtils {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static <T> void forEach(List<T> array, Consumer<T> consumer) {
        for (T item : array) {
            consumer.accept(item);
        }
    }

    public static <T> List<T> filter(List<T> array, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : array) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <Type, ReduceValue> ReduceValue reduce(List<Type> list,
                                                         ReduceValue initValue,
                                                         BiFunction<Type,
                                                                 ReduceValue,
                                                                 ReduceValue> biFunction) {
        for (Type elem : list) {
            initValue = biFunction.apply(elem, initValue);
        } return initValue;
    }

    public static <T> boolean anyMatch(List<T> array, Predicate<T> predicate) {
        List<T> sort = new ArrayList<>();
        for (T element : array) {
            if (!predicate.test(element)) {
                sort.add(element);
            } else {
                return true;
            }
        } return false;
    }

    public static <T> boolean allMatch(List<T> array, Predicate<T> predicate) {
        List<T> sort = new ArrayList<>();
        for (T element : array) {
            if (predicate.test(element)) {
                sort.add(element);
            } else {
                return false;
            }
        } return true;

    }

    public static <T,R> List<R> map(List<T> array, Function<T,R> function) {
        List<R> result = new ArrayList();
        for(T element: array)  {
            R res = function.apply(element);
            result.add(res);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> array = Arrays.asList(5,4,9,16);
        List<Contact> namesList = new ArrayList<>();
        namesList.add(new Contact("Serhii", "+7099 7891266"));
        namesList.add(new Contact("Messi", "+7077 7891255"));
        namesList.add(new Contact("Ronaldo", "+7011 7897712"));
        namesList.add(new Contact("Shevchenko", "+7044 7890077"));

        System.out.println("------------------All Match STREAM----------------------");
        System.out.println(namesList.stream().anyMatch(name -> name.getName().startsWith("Mes")));

        System.out.println("------------------All Match----------------------");
        boolean matchValues = allMatch(array, sortNumber -> sortNumber%8==0);
        System.out.println("Are the all numbers from the list can divide on 8?: " + ANSI_RED + matchValues +ANSI_RESET);
        System.out.println("------------------Any Match----------------------");

        boolean anyMatchedValue = anyMatch(array, (sortNumber) -> {
            return sortNumber%8==0;
        });
        System.out.println("Is the any number from the list can divide on 8?: " + ANSI_RED + anyMatchedValue +ANSI_RESET);
        System.out.println("--------------------------------------------------");

        boolean anyMatchedValueVer2 = anyMatch(namesList, (sortNumber) -> "Serhio".equals(sortNumber.getName()));
        boolean anyMatchedValueVer3 = anyMatch(namesList, (sortNumber) -> "+7011 7897712".equals(sortNumber.getPhoneNumber()));
        System.out.println("Is the name exist...?: " + ANSI_RED + anyMatchedValueVer2 +ANSI_RESET);
        System.out.println("Is the number exist...?: " + ANSI_RED + anyMatchedValueVer3 +ANSI_RESET);
        System.out.println("------------------Contacts------------------------");

        forEach(namesList, (System.out::println));
        System.out.println("-------------Changed Contacts---------------------");

        List<Contact> changedContacts = map(namesList, changes -> new Contact(changes.getName().toUpperCase(Locale.ROOT),
                                                                              changes.getPhoneNumber().substring(0,5)));

        forEach(changedContacts, (System.out::println));
        System.out.println("--------------------------------------------------");

        FileWriter writer = new FileWriter("name_list.txt");
        for (Contact element: namesList) {
            String name = element.getName();
            String number = element.getPhoneNumber();
            writer.write(name + " " + number + System.getProperty("line.separator"));
        }
        writer.close();

        List<Integer> values = Stream.of("32", "43", "74", "54", "3").map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("New list: " + values);
        System.out.println("--------------------------------------------------");
    }
}
