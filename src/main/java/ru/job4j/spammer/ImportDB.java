package ru.job4j.spammer;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class ImportDB
 * (3. Загрузка базы спамеров. [#20459])
 * переводит бд Users(name, email) из
 * файла.txt в PostgreSQL
 *
 * @author Ruslan Shuyski
 * @version 1
 */
@Slf4j
public class ImportDB {

    private final Properties cfg;
    private final String dump;


    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(this.dump))) {
            rd.lines().filter(f -> !f.isBlank()).forEach(f -> {
                int indexOne = f.indexOf(';');
                int indexTwo = f.indexOf(';', indexOne + 1);
                if (indexOne == -1 || indexTwo == -1) {
                    throw new IllegalArgumentException();
                }
                String key = f.substring(0, indexOne);
                String value = f.substring(indexOne + 1, indexTwo);
                if (key.isEmpty() || value.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                users.add(new User(key, value));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
            print(cnt, "users");
        }
    }

    public void print(Connection connection, String tableName) throws SQLException {
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(
                    "select * from " + tableName
            );
            while (resultSet.next()) {
                log.info("Id = {}, Name = {}, Email = {}",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public static void main(String[] args) throws Exception {
            Properties cfg = new Properties();
            try (FileInputStream in = new FileInputStream("files/3. Загрузка базы спамеров/app.properties")) {
                cfg.load(in);
            }
            ImportDB db = new ImportDB(cfg, "files/3. Загрузка базы спамеров/dump.txt");
            db.save(db.load());
        }
    }
}
