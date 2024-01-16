package org.example;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Alumn> alumns = new ArrayList<>(0);

    public void addAlumn(Alumn a){
        a.setTeacher(this);
        alumns.add(a);
    }
}
