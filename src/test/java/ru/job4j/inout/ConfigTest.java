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
    public void whenIndexError() {
        String path = "Config/index.properties.txt";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenValueError() {
        String path = "Config/value.properties.txt";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyError() {
        String path = "Config/key.properties.txt";
        Config config = new Config(path);
        config.load();
    }
}