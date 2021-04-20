package ru.job4j.inout;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenFileWithCommentAndSkippedLine() {
        String path = "Config/app.properties.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenValueError() {
        String path = "Config/app.check.txt";
        Config config = new Config(path);
        config.load();
        config.value("ключ");
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyError() {
        String path = "Config/app.check.txt";
        Config config = new Config(path);
        config.load();
        config.value("15");
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoComment() {
        String path = "Config/app.log.txt";
        Config config = new Config(path);
        config.load();
        config.value("# Фамилии");
    }
}
