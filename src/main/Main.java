package main;

import person.Person;

import javax.swing.*;
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

        List<Person> people = new ArrayList<>();
        List<Person> emails;
        List<Person> peopleSum;
        Double sum = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\estudos\\people.txt"))) {

            String line = br.readLine();
            Double salary = parseDouble(showInputDialog("ENTER SALARY:"));

            while (line != null) {
                String[] fields = line.split(",");
                people.add(new Person(fields[0], fields[1], parseDouble(fields[2])));
                line = br.readLine();
            }

            emails = people.stream().filter(p -> p.getSalary() >= salary).collect(toList());
            StringBuilder response = new StringBuilder("EMAIL OF PEOPLE WHOSE SALARY IS MORE THAN R$ " + format("%.2f", salary) + "\n");
            emails.forEach(email -> response.append(email.getEmail()).append("\n"));
            showMessageDialog(null, response);

            peopleSum = people.stream().filter(p -> p.getName().charAt(0) == 'M').collect(toList());
            sum = peopleSum.stream().map(p -> p.getSalary()).reduce(0.0, (x, y) ->  x + y);
            showMessageDialog(null, "A SOMA DOS SALÁRIOS DOS NOMES QUE COMEÇAM EM 'M' É: R$ " + format("%.2f", sum));


        } catch (IOException e) {
            showMessageDialog(null, e.getMessage());
        }

    }
}
