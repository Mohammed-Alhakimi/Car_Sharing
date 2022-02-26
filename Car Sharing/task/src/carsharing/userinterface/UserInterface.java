package carsharing.userinterface;

import carsharing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInterface {


    public void ShowMainMenu() {
        print("1. Log in as a manager\n" +
                "2. Log in as a customer\n" +
                "3. Create a customer\n" +
                "0. Exit");
    }

    public String logInAsManager() {
        print("1. Company list\n" +
                "2. Create a company\n" +
                "0. Back");
        return new Scanner(System.in).nextLine();
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void newLine() {
        System.out.println();
    }

    public void listOfCompaniesPrint(List<Company> list) {
        print("\nChoose The company:");
        AtomicInteger listCounter = new AtomicInteger(1);
        list.stream()
                .sorted(Comparator
                        .comparing(Company::getId))
                .forEach(company -> print(listCounter.getAndIncrement() + ". " + company.getName()));
        print("0. Back");
    }

    public void printCarMainMenu() {
        print("1. Car list\n" +
                "2. Create a car\n" +
                "0. Back");
    }

    public void printCarList(List<Car> list) {
        AtomicInteger aCars = new AtomicInteger(1);
        print("\nCar list:");
        list.forEach(car -> print(aCars.getAndIncrement() + ". " + car.getName()));
        newLine();
    }

    public void printCarListRent(List<Car> list) {
        AtomicInteger aCars = new AtomicInteger(1);
        print("\nChoose a car::");
        list.forEach(car -> print(aCars.getAndIncrement() + ". " + car.getName()));
        print("0. Back");
    }

    public String getNameForCustomer() {
        print("Enter the customer name:");
        return new Scanner(System.in).nextLine();
    }

    public void printCustomerList(List<Customer> list) {
        AtomicInteger aCustomers = new AtomicInteger(1);
        print("Choose a customer:");
        list.forEach(customer -> print(aCustomers.getAndIncrement() + ". " + customer.getName()));
        print("0. Back");
    }

    public void printCustomerMenu() {
        print("1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back");
    }
}
