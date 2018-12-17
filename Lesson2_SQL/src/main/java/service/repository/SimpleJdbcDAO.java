package service.repository;

import model.Client;
import model.Phone;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcDAO implements ClientDAO {

    private Connection connection;
    private PreparedStatement getPhonesPs;

    public SimpleJdbcDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public Client get(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Client WHERE ID = " + id);
            while ( resultSet.next() ) {
                Client client = createClient(resultSet);
                List<Phone> phones = getAllPhonesForClient(id);
                client.getPhones().addAll(phones);
                return client;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query", e);
        }

        return null;
    }



    @Override
    public List<Phone> getAllPhonesForClient(int clientId) {
        List<Phone> result = new ArrayList<>();

        try {
            if (getPhonesPs == null) {
                getPhonesPs = connection.prepareStatement("SELECT ID, PHONE_TYPE, PHONE_NUMBER " +
                        "FROM Phone " +
                        "WHERE CLIENT_ID = ?");
            }

            getPhonesPs.setInt(1, clientId);

            ResultSet resultSet = getPhonesPs.executeQuery();
            while (resultSet.next()) {
                result.add(createPhone(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query", e);
        }

        return result;
    }

    @Override
    public void saveClient(Client client) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Client (FIRST_NAME, LAST_NAME, BIRTH_DATE)" +
                    "VALUES (?, ? , ?)");

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getBirthDay().toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean removeClient(int clientId) {

        return false;
    }

    private Client createClient(ResultSet resultSet) throws SQLException {
        int clientId = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        LocalDate birthDay = LocalDate.parse(resultSet.getString(4));

        Client client = new Client();
        client.setId(clientId);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setBirthDay(birthDay);
        return client;
    }

    private Phone createPhone(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String phoneType = resultSet.getString(2);
        String phoneNumber = resultSet.getString(3);




        Phone phone = new Phone();
        phone.setId(id);
        phone.setType(phoneType);
        phone.setNumber(phoneNumber);

        return phone;
    }
}
