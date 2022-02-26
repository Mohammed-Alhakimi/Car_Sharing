package carsharing.db;

import carsharing.*;

import java.util.List;

public interface DAO {
    
    void assignCarToCustomer(String name, int id);

    void returnCar(String name);
    
    void createCompanyTable();

    void createCarTable();

    void createCustomerTable();

    List<Company> getListOfCompanies();

    List<Car> getCarsFromDepartmentID(int companyID);

    List<Customer> getListOfCustomers();

    String retrieveRentInfo(String customerName);

    int addNewCompany(String name);
    
    int addCarToCarsTable(String carName, int companyId);
    
    int addNewCustomer(String customerToAdd);
    
    boolean checkRented(String name);
    
}
