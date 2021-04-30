package ru.job4j.inout.duplicates;

import java.util.Objects;

/** 4.2. Поиск дубликатов [#459954]
 * Class FileProperty
 * модель данных фалйла
 * size - размер файла
 * name - имя файла
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class FileProperty {
    private long size;
    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return "FileProperty{"
                + "size="
                + size
                + ", name='"
                + name
                + '\''
                + '}';
    }
}