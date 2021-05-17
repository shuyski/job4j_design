package ru.job4j.inout.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory
            .getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Ruslan Shuyski";
        int age = 27;
        char gender = 'M';
        float height = 183.5F;
        long dist = 50000;
        double pi = Math.PI;
        boolean pro = true;
        short weight = 1500;
        byte child = 1;
        LOG.debug("Имя : {}, "
                + " возраст : {},"
                + " рост : {},"
                + " пол : {},"
                + " расстояние от дома до работы в метрах : {},"
                + " число пи : {}, программист? : {},"
                + " вес авто в килограммах : {}, "
                + " оличество детей : {}",
                name, age, height, gender, dist, pi, pro, weight, child);
    }
}
