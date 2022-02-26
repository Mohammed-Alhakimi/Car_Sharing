package carsharing;

import carsharing.db.*;
import carsharing.userinterface.UserInterface;
import com.beust.jcommander.*;
import lombok.Getter;

import java.util.*;

import static carsharing.userinterface.UserInterface.*;

public class Main {
    @Parameter(names = {"-databaseFileName", "-f"}, description = "Specifies the file name for the database")
    @Getter
    private String file;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        JCommander
                .newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        if (main.getFile() != null) {
            DatabaseConnection.setDbStringURL(main.getFile());
        }

        UserInterface ui = new UserInterface();
        DAOImpl dao = new DAOImpl();
        dao.createCompanyTable();
        dao.createCarTable();
        dao.createCustomerTable();

        String mainChoice = "";
        while (!mainChoice.equals("0")) {
            ui.ShowMainMenu();
            mainChoice = scanner.nextLine();
            newLine();
            switch (mainChoice) {
                case "1":
                    String choice = ui.logInAsManager();
                    while (!"0".equals(choice)) {
                        switch (choice) {
                            case "1":
                                List<Company> listOfCompanies = dao.getListOfCompanies();
                                if (listOfCompanies.isEmpty()) {
                                    print("\nThe company list is empty!\n");
                                } else {
                                    String companyChoice = "";
                                    while (!"0".equals(companyChoice)) {
                                        ui.listOfCompaniesPrint(listOfCompanies);
                                        companyChoice = scanner.nextLine();

                                        if ("0".equals(companyChoice)) {
                                            break;
                                        } else if (Integer.parseInt(companyChoice) > listOfCompanies.size()) {
                                            print("\nThere is no such company");
                                        } else {
                                            Company company = listOfCompanies.get(Integer.parseInt(companyChoice) - 1);
                                            print("\n" + "'" + listOfCompanies.get(Integer.parseInt(companyChoice) - 1).getName() + "'" + " company");
                                            String carMenuChoice = "";
                                            while (!"0".equals(carMenuChoice)) {
                                                ui.printCarMainMenu();
                                                carMenuChoice = scanner.nextLine();
                                                switch (carMenuChoice) {
                                                    case "1":
                                                        List<Car> cars = dao.getCarsFromDepartmentID(company.getId());
                                                        if (cars.isEmpty()) {
                                                            print("\nThe car list is empty!\n");
                                                        } else {
                                                            ui.printCarList(cars);
                                                        }
                                                        break;
                                                    case "2":
                                                        print("\nEnter the car name:");
                                                        String carName = scanner.nextLine();
                                                        if (!carName.equals("")) {
                                                            String reply = dao.addCarToCarsTable(carName, company.getId()) != -1 ? "The car was added!\n" : "Failed to add the car\n";
                                                            print(reply);
                                                        }
                                                        break;
                                                    case "0":
                                                        companyChoice = "0";
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case "2":
                                print("\nEnter the company name:");
                                String reply = dao.addNewCompany(scanner.nextLine()) != -1 ?
                                        "\nThe company was created!\n" : "\nThe company wasn't created\n";
                                print(reply);
                                break;
                            default:
                                print("\nWrong choice!\n");
                        }
                        choice = ui.logInAsManager();
                    }
                    break;
                case "2":
                    List<Customer> customers = dao.getListOfCustomers();
                    if (customers.isEmpty()) {
                        print("\nThe customer list is empty!\n");
                    } else {
                        String customerChoice = "";
                        while (!"0".equals(customerChoice)) {
                            ui.printCustomerList(customers);
                            customerChoice = scanner.nextLine();
                            newLine();
                            try {
                                if (Integer.parseInt(customerChoice) > 0 && Integer.parseInt(customerChoice) <= customers.size()) {
                                    String customerMenuChoice;
                                    while (true) {
                                        ui.printCustomerMenu();
                                        customerMenuChoice = scanner.nextLine();
                                        if (customerMenuChoice.equals("0")) {
                                            break;
                                        } else {
                                            Customer customerChosen = customers.get(Integer.parseInt(customerChoice) - 1);
                                            String customerName = customerChosen.getName();
                                            switch (customerMenuChoice) {
                                                case "1":
                                                    if (dao.checkRented(customerName)) {
                                                        print("\nYou've already rented a car!\n");
                                                    } else {
                                                        List<Company> companies = dao.getListOfCompanies();
                                                        if (companies.isEmpty()) {
                                                            print("\nThe company list is empty!\n");
                                                        } else {
                                                            ui.listOfCompaniesPrint(companies);
                                                            String rentMenuCompanyChoice = scanner.nextLine();
                                                            Company company = companies.get(Integer.parseInt(rentMenuCompanyChoice) - 1);
                                                            if (!rentMenuCompanyChoice.equals("0")) {
                                                                List<Car> cars = dao.getCarsFromDepartmentID(company.getId());
                                                                Company companyChosen = companies.get(Integer.parseInt(rentMenuCompanyChoice) - 1);
                                                                if (cars.isEmpty()) {
                                                                    print("\nNo available cars in the '" + companyChosen.getName() + "' company.\n");
                                                                } else {
                                                                    ui.printCarListRent(cars);
                                                                    String carChoice = scanner.nextLine();
                                                                    if (!carChoice.equals("0")) {
                                                                        Car carChosen = cars.get(Integer.parseInt(carChoice) - 1);
                                                                        dao.assignCarToCustomer(customerName, carChosen.getId());
                                                                        print("\nYou rented '" + carChosen.getName() + "'\n");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case "2":
                                                    if (dao.checkRented(customerName)) {
                                                        dao.returnCar(customerName);
                                                        print("\nYou've returned a rented car!\n");
                                                    } else {
                                                        print("\nYou didn't rent a car!\n");
                                                    }
                                                    break;
                                                case "3":
                                                    boolean isRented = dao.checkRented(customerName);
                                                    if (isRented) {
                                                        print(dao.retrieveRentInfo(customerName));
                                                    } else {
                                                        print("\nYou didn't rent a car!\n");
                                                    }
                                                    break;
                                                default:
                                            }
                                        }
                                    }
                                } else {
                                    print("\nPlease choose a valid number\n");
                                }
                            } catch (NumberFormatException e) {
                                print("\nPlease enter a number\n");
                            }
                        }
                    }
                    break;
                case "3":
                    String customerToAdd = ui.getNameForCustomer();
                    String reply = dao.addNewCustomer(customerToAdd) != -1 ?
                            "\nThe customer was added!\n" : "\nThe customer wasn't added\n";
                    print(reply);
                    break;
                case "0":
                    break;
                default:
                    print("\nChoose a right number!\n");
                    break;
            }
        }
    }
}