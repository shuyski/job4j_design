package ru.job4j.jdbc.preparestatement;

import lombok.extern.slf4j.Slf4j;
import ru.job4j.jdbc.TableEditor;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Class PrepareStatementDemo
 * (0.2. PrepareStatement [#379307])
 * предназначен для DML операций –
 * INSERT, SELECT, UPDATE, DELETE
 *
 * @author Ruslan Shuyski
 * @version 1
 */

@Slf4j
public class PrepareStatementDemo {

    private Connection connection;

   // static Logger log = LoggerFactory.getLogger("PrepareStatementDemo");

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        City city = new City(1, "Name1", 900);
        City city1 = new City(2, "Name2", 800);
        City city2 = new City(3, "Name3", 700);
        City city3 = new City(1, "Name4", 999);
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("files/0.2. PrepareStatement/file1.properties");
        properties.load(in);
        TableEditor table = new TableEditor(properties);
        table.createTable(properties.getProperty("nameTable"));
        table.addColumn(properties.getProperty("nameTable"),
                properties.getProperty("nameColumn1"),
                properties.getProperty("typeColumn1"));
        table.addColumn(properties.getProperty("nameTable"),
                properties.getProperty("nameColumn2"),
                properties.getProperty("typeColumn2"));

        PrepareStatementDemo demo = new PrepareStatementDemo();
        demo.insert(city);
        demo.insert(city);
        demo.insert(city);
        demo.delete(2);
        demo.delete(3);
        demo.delete(4);
        demo.insert(city1);
        demo.insert(city2);
        demo.update(city3);
        List<City> list = demo.findAll();
        for (City value : list) {
            System.out.println(value.getId()
                    + " "
                    + value.getName() + " "
                    + value.getPopulation());
        }
        getTableScheme(demo.connection, properties.getProperty("nameTable"), list);
        System.setProperty("log.name", "ancdes");
        log.debug("start");
        table.dropTable(properties.getProperty("nameTable"));
    }

    public static void getTableScheme(Connection connection, String tableName, List<City> list) throws Exception {
//        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
//        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
//        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
//        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(
                    "select * from " + tableName
            );
            var metaData = resultSet.getMetaData();
                while (resultSet.next()) {
                    log.info("Id = {}, Name = {}, Population = {}",
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));
            }
        }
//            var metaData = selection.getMetaData();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                buffer.add(String.format("%-15s|%-15s%n",
//                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
//                );
//            }
//        }
//        return buffer.toString();
    }
}
