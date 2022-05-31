package main;

import person.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\estudos\\people.txt"))) {

            List<Person> people = new ArrayList<>();

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                people.add(new Person(fields[0], fields[1], parseDouble(fields[2])));
                line = br.readLine();
            }

            Double salary = parseDouble(showInputDialog("ENTER SALARY:"));

            List<String> emails = people.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(Person::getEmail)
                    .sorted()
                    .collect(toList());
            StringBuilder emailsToString = new StringBuilder("Email of people whose salary is more than R$ " + format("%.2f", salary) + "\n");
            emails.forEach(email -> emailsToString.append(email).append("\n"));
            showMessageDialog(null, emailsToString);

            double sum = people.stream()
                    .filter(p -> p.getName().charAt(0) == 'M')
                    .map(Person::getSalary)
                    .reduce(0.0, Double::sum);
            showMessageDialog(null, "Sum of salary from people whose name starts with 'M': R$ " + format("%.2f", sum));


        } catch (IOException e) {
            showMessageDialog(null, e.getMessage());
        }

    }
}
