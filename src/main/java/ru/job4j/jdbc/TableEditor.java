package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Class TableEditor
 * 0.1. Statement [#379306]
 *
 * Создаёт, удаляет таблицу
 * Создаёт, удаляет, переименовывает
 * колонки
 * Параметры из файла *.properties
 * Выводим на экран результат
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, login, password);
    }

    public void getStatement(String script) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(script);
            }
        }

    public void createTable(String tableName) throws Exception {
                String script = String.format(
                        "create table if not exists "
                                + tableName
                                + "(%s)",
                        "id serial primary key"
                );
                getStatement(script);
        System.out.println(getTableScheme(getConnection(), tableName));
    }

    public void dropTable(String tableName) throws Exception {
                String script = "drop table "
                        + tableName
                        + ";";
                getStatement(script);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
                String script = "alter table "
                        + tableName
                        + " add "
                        + columnName
                        + " "
                        + type
                        + ";";
                getStatement(script);
        System.out.println(getTableScheme(getConnection(), tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
                String script = "alter table "
                        + tableName
                        + " drop column "
                        + columnName
                        + ";";
                getStatement(script);
        System.out.println(getTableScheme(getConnection(), tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
                String script = "alter table "
                        + tableName
                        + " rename column "
                        + columnName
                        + " to "
                        + newColumnName
                        + ";";
                getStatement(script);
        System.out.println(getTableScheme(getConnection(), tableName));
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("files/0.1. Statement/file.properties");
        properties.load(in);
        TableEditor table = new TableEditor(properties);

        table.createTable(properties.getProperty("nameTable"));
        table.addColumn(properties.getProperty("nameTable"),
                properties.getProperty("nameColumn1"),
                properties.getProperty("typeColumn1"));
        table.addColumn(properties.getProperty("nameTable"),
                properties.getProperty("nameColumn2"),
                properties.getProperty("typeColumn2"));
        table.dropColumn(properties.getProperty("nameTable"), properties.getProperty("nameColumn2"));
        table.renameColumn(properties.getProperty("nameTable"),
                properties.getProperty("nameColumn1"), "newName");
        table.dropTable(properties.getProperty("nameTable"));
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
