package edu.badpals.repositorio;


import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Wizard;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;


@ApplicationScoped
public class Repositorio {

    public Optional<Wizard> loadWizard(String name){

        Optional<Wizard> wizard = Wizard.find("name = ?1", name).firstResultOptional();
        return wizard;

    }

    public Optional<MagicalItem> loadItem(String name){

        Optional<MagicalItem> item = MagicalItem.find("name = ?1", name).firstResultOptional();
        return item;
    }


}
