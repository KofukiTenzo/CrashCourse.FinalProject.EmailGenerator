package com.tenzo.projJava.crashcourse.kharchenko;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        Scanner scan = new Scanner(System.in);
        Person user = null;
        boolean isCancel = false;
        int answer;


        while (!isCancel) {

            if (user == null) {
                System.out.println("******************************************");
                System.out.println("YOUR PERSONAL COMPANY EMAIL");
                System.out.println("1: Registration.");
                System.out.println("2: Login.");
                System.out.println("0: Exit");

                System.out.print("Enter: ");
                answer = scan.nextInt();

                switch (answer) {
                    case 1:
                        user = person.registration();
                        break;
                    case 2:
                        user = person.login();
                        if (user == null) System.out.println("LOGIN FAILED");
                        break;
                    case 0:
                        person.exit();
                        isCancel = true;
                        break;
                }

            } else {
                System.out.println("******************************************");
                System.out.println(String.format("HELLO %s", user.getFullName().toUpperCase()));
                System.out.println("1: Show info.");
                System.out.println("2: Change password.");
                System.out.println("3: Change mail box capacity.");
                System.out.println("4: Set\\Change alter email.");
                System.out.println("0: Exit");

                System.out.print("Enter: ");
                answer = scan.nextInt();

                switch (answer) {
                    case 1:
                        System.out.printf("\nNAME: %s", user.getFullName());
                        System.out.printf("\nDEPARTMENT: %s", user.getDepartment());
                        System.out.printf("\nEMAIL: %s", user.getEmail());
                        System.out.printf("\nPASSWORD: %s", user.getPassword());
                        System.out.printf("\nMAILBOX CAPACITY: %d MB", user.getCapacity());
                        System.out.printf("\nALTER EMAIL: %s\n", user.getAlterEmail());
                        break;
                    case 2:
                        user.changePass();
                        break;
                    case 3:
                        user.changeMailCapacity();
                        break;
                    case 4:
                        user.changeAlterEmail();
                        break;
                    case 0:
                        person.exit();
                        isCancel = true;
                        break;
                }
            }
        }

        scan.close();
    }
}
