package carsharing.db;

import carsharing.*;

import java.sql.*;
import java.util.*;

public class DAOImpl implements DAO {

    /**
     * @return a list of all the companies in te database
     */
    public List<Company> getListOfCompanies() {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                List<Company> listRetrieved = new ArrayList<>();
                String sql = "SELECT * FROM COMPANY;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listRetrieved.add(new Company(rs.getInt(1), rs.getString(2)));
                }
                return listRetrieved.isEmpty() ? Collections.emptyList() : listRetrieved;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting the list of companies");
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getCarsFromDepartmentID(int companyId) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            String sql = "SELECT * FROM CAR WHERE COMPANY_ID = ?;";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setInt(1, companyId);
                List<Car> listCarsRetrieved = new ArrayList<>();
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    listCarsRetrieved.add(new Car(rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(1)));
                }
                return listCarsRetrieved.isEmpty() ? Collections.emptyList() : listCarsRetrieved;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Customer> getListOfCustomers() {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                List<Customer> listRetrieved = new ArrayList<>();
                String sql = "SELECT * FROM CUSTOMER;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listRetrieved.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
                return listRetrieved.isEmpty() ? Collections.emptyList() : listRetrieved;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting the list of companies");
        }
        return Collections.emptyList();
    }

    @Override
    public String retrieveRentInfo(String customerName) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {

                String sql = "SELECT * FROM CUSTOMER WHERE NAME= '" + customerName + "';";
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                String sql2 = "SELECT * FROM CAR WHERE ID= " + rs.getInt(3) + ";";
                ResultSet rs2 = st.executeQuery(sql2);
                rs2.next();
                String carName = rs2.getString(2);
                String sql3 = "SELECT * FROM COMPANY WHERE ID= " + rs2.getInt(3) + ";";
                ResultSet rs3 = st.executeQuery(sql3);
                rs3.next();
                String companyName = rs3.getString(2);
                return "\nYour rented car:\n" + carName + "\nCompany:\n" + companyName + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * @param name of the company
     * @return 1 if successful and -1 if failed
     */
    @Override
    public int addNewCompany(String name) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                String sql = " INSERT INTO COMPANY (NAME) VALUES ('" + name + "');";
                return st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int addCarToCarsTable(String carName, int companyId) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                String sql = " INSERT INTO CAR (NAME,COMPANY_ID) VALUES ('" + carName + "',"
                        + companyId + ");";
                return st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int addNewCustomer(String customerName) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                String sql = " INSERT INTO CUSTOMER (NAME) VALUES ('" + customerName + "');";
                return st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * Creates a table called Company
     */
    @Override
    public void createCompanyTable() {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                con.setAutoCommit(true);
                String sql = " CREATE TABLE IF NOT EXISTS COMPANY (" +
                        "ID INTEGER NOT NULL AUTO_INCREMENT," +
                        "NAME VARCHAR(60) UNIQUE NOT NULL," +
                        "PRIMARY KEY (ID)" +
                        "); ";
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a car table (A company might have many cars)
     */
    @Override
    public void createCarTable() {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                con.setAutoCommit(true);
                String sql = " CREATE TABLE IF NOT EXISTS CAR (" +
                        "ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                        "NAME VARCHAR(60) UNIQUE NOT NULL," +
                        "COMPANY_ID INTEGER NOT NULL," +
                        "CONSTRAINT fk_companyId FOREIGN KEY (COMPANY_ID)" +
                        "REFERENCES COMPANY(ID)" +
                        "ON UPDATE CASCADE" +
                        "); ";
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCustomerTable() {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                con.setAutoCommit(true);
                String sql = " CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                        "ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                        "NAME VARCHAR(60) UNIQUE NOT NULL," +
                        "RENTED_CAR_ID INTEGER DEFAULT NULL," +
                        "CONSTRAINT fk_carId FOREIGN KEY (RENTED_CAR_ID)" +
                        "REFERENCES CAR(ID)" +
                        "ON UPDATE CASCADE" +
                        "); ";
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignCarToCustomer(String customerName, int carId) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = ? WHERE NAME = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setInt(1, carId);
                st.setString(2, customerName);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnCar(String customerName) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE NAME = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, customerName);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkRented(String customerName) {
        try (Connection con = DatabaseConnection.connect()) {
            assert con != null;
            try (Statement st = con.createStatement()) {
                String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE NAME = '" + customerName + "';";
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                int id = rs.getInt(1);
                return id != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
