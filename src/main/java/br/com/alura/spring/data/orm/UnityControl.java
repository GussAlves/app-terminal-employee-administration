package br.com.alura.spring.data.orm;

import javax.persistence.*;

@Entity
@Table(name="unity_controls")
public class UnityControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return " UnityControl | id - " + id + " | description - "
                + description + " | address - " + address;
    }
}
