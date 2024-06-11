package edu.badpals.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "t_wizards")
public class Wizard extends PanacheEntityBase {

    @Id
    @Column(name = "wizard_name")
    private String name;

    @Column(name = "wizard_dexterity")
    private int dexterity;

    @Column(name = "wizard_person")
    @Enumerated(EnumType.STRING)
    private WizardPerson person;

    public Wizard(){

    }

    public Wizard(String name, int dexterity, WizardPerson person) {
        this.name = name;
        this.dexterity = dexterity;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public WizardPerson getPerson() {
        return person;
    }

    public void setPerson(WizardPerson person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "name='" + name + '\'' +
                ", dexterity=" + dexterity +
                ", person=" + person +
                '}';
    }
}