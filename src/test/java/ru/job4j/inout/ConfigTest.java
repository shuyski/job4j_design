package ru.job4j.inout;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenFileWithCommentAndSkippedLine() {
        String path = "files/Config/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIndexError() {
        String path = "files/Config/index.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenValueError() {
        String path = "files/Config/value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyError() {
        String path = "files/Config/key.properties";
        Config config = new Config(path);
        config.load();
    }
}