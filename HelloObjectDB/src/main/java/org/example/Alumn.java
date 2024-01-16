package org.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data @Entity
public class Alumn implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Teacher teacher;

    @Override
    public String toString() {
        return "Alumn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher.getName() +
                '}';
    }
}
