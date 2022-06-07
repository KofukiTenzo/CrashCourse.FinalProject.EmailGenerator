package com.tenzo.projJava.crashcourse.kharchenko;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Person {

    private String fullName;
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;
    private int capacity;
    private String alterEmail;

    Scanner scanner = new Scanner(System.in);

    private static HashMap<HashMap<String, String>, Person> persons = new HashMap<>();

    static {
        Person admin = new Person();
        admin.firstName = "Ruslan";
        admin.lastName = "Kharchenko";
        admin.fullName = "admin";
        admin.password = "admin";
        admin.department = "Administration";
        admin.capacity = 5000;
        admin.email = admin.emailGenerator(admin.firstName, admin.lastName, admin.department);

        HashMap<String, String> buffer = new HashMap<>();
        buffer.put(admin.fullName, admin.password);

        persons.put(buffer, admin);
    }

    public Person() {
    }

    private Person(String firstName, String lastName, String department, String email, String password, String alterEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.department = department;
        this.email = email;
        this.password = password;
        this.capacity = 500;
        this.alterEmail = alterEmail;
    }

    private Person userCreation() {
        String firstName, lastName, dep = "", email, pass, altEmail;
        int answer = 4;
        String ans;

        System.out.println("NEW EMPLOYEE CREATION");
        System.out.print("Enter first name: ");
        firstName = scanner.next();
        System.out.print("Enter last name: ");
        lastName = scanner.next();
        while (answer > 3 || answer < 0) {
            System.out.println("Chuse your department:");
            System.out.println("1: Sales.");
            System.out.println("2: Development.");
            System.out.println("3: Accounting.");
            System.out.println("0: None.");

            answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    dep = "Sales";
                    break;
                case 2:
                    dep = "Development";
                    break;
                case 3:
                    dep = "Accounting";
                    break;
                case 0:
                    dep = "";
                    break;
            }
        }

        email = emailGenerator(firstName, lastName, dep);

        pass = passwordGenerator();

        System.out.print("Do you want to add alter email? [y/n]: ");
        ans = scanner.next();
        if (ans.toLowerCase().equals("y")) {
            String newAlterEmail = "";
            String regex = "^(.+)@(.+)\\.(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = null;

            System.out.print("Enter new alter email [\"exit\" for escape]: ");
            altEmail = scanner.next();
            while (!(pattern.matcher(altEmail).matches())) {
                if (altEmail.equals("exit")) {
                    altEmail = "";
                    break;
                }
                System.out.println("Incorrect email!!!");
                System.out.print("Try again: ");
                altEmail = scanner.next();
            }
        } else altEmail = "";

        return new Person(firstName, lastName, dep, email, pass, altEmail);
    }

    public Person registration() {
        Person user = userCreation();
        HashMap<String, String> buffer = new HashMap<>();

        buffer.put(user.getFullName(), user.getPassword());

        persons.put(buffer, user);

        return user;
    }

    public Person login() {
        HashMap<String, String> buffer = new HashMap<>();
        String fullName, pass;

        System.out.println("LOGIN");
        System.out.print("Enter user full name: ");
        fullName = scanner.next();
        System.out.print("Enter user password: ");
        pass = scanner.next();

        buffer.put(fullName, pass);

        return persons.get(buffer);
    }

    private String emailGenerator(String firstName, String lastName, String department) {
        if (!department.equals(""))
            return String.format("%s.%s@%s.company.com", firstName, lastName, department).toLowerCase();
        else return String.format("%s.%s@company.com", firstName, lastName).toLowerCase();
    }

    private String passwordGenerator() {
        String password = new Random()
                .ints(10, 48, 122)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
        return password;
    }

    public void changePass() {
        String newPass;

        System.out.print("Enter new password [\"exit\" for escape]: ");
        while ((newPass = scanner.next()).length() < 8) {
            if (newPass.equals("exit")) return;
            System.out.println("Password length must be more than 7!!!");
            System.out.print("Try again: ");
        }

        setPassword(newPass);
    }

    public void changeMailCapacity() {
        String newCapacity;

        System.out.print("Enter new capacity [\"exit\" for escape]: ");
        while (Integer.parseInt(newCapacity = scanner.next()) < 50) {
            if (newCapacity.equals("exit")) return;
            System.out.println("Capacity must be more than 49 MB!!!");
            System.out.print("Try again: ");
        }

        setCapacity(Integer.parseInt(newCapacity));
    }

    public void changeAlterEmail() {
        String newAlterEmail = "";
        String regex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        System.out.print("Enter new alter email [\"exit\" for escape]: ");
        newAlterEmail = scanner.next();
        while (!(pattern.matcher(newAlterEmail).matches())) {
            if (newAlterEmail.equals("exit")) return;
            System.out.println("Incorrect email!!!");
            System.out.print("Try again: ");
            newAlterEmail = scanner.next();
        }

        setAlterEmail(newAlterEmail);
    }

    public void exit() {
        scanner.close();
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private void setAlterEmail(String alterEmail) {
        this.alterEmail = alterEmail;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAlterEmail() {
        return alterEmail;
    }
}
