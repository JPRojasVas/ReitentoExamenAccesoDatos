package edu.badpals.repositorio;


import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Order;
import edu.badpals.domain.Wizard;
import edu.badpals.domain.WizardPerson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class Repositorio {

    public Optional<Wizard> loadWizard(String name) {

        Optional<Wizard> wizard = Wizard.find("name = ?1", name).firstResultOptional();
        return wizard;

    }


    public Optional<MagicalItem> loadItem(String name) {

        Optional<MagicalItem> item = MagicalItem.find("name = ?1", name).firstResultOptional();
        return item;
    }

    public Optional<MagicalItem> loadItem(MagicalItem magicalItem) {

        List<MagicalItem> items = MagicalItem.listAll();

        MagicalItem itemFilter = null;

        for (MagicalItem item : items) {
            if (item.getName().equals(magicalItem.getName()) && item.getQuality() == magicalItem.getQuality() && item.getType().equals(magicalItem.getType())) {
                itemFilter = item;
            }

        }

        return Optional.ofNullable(itemFilter);

    }

    public List<MagicalItem> loadItems(String name) {

        List<MagicalItem> items = MagicalItem.listAll();
        List<MagicalItem> filterItems = new ArrayList<>();

        for (MagicalItem item : items) {

            if (item.getName().equals(name)) {
                filterItems.add(item);
            }
        }
        return filterItems;

    }

    @Transactional
    public Optional<Order> placeOrder(String wizard, String magicalItem) {

        Optional<Wizard> mago = Wizard.findByIdOptional(wizard);
        Optional<MagicalItem> objeto = MagicalItem.find("name = ?1", magicalItem).firstResultOptional();

        Order ordenFiltrada = null;

        if (mago.isPresent() && objeto.isPresent() && mago.get().getPerson().compareTo(WizardPerson.MUDBLOOD) != 0) {

            Order order = new Order(loadWizard(wizard).get(), loadItem(magicalItem).get());

            ordenFiltrada = order;
            ordenFiltrada.persist();
        }

        return Optional.ofNullable(ordenFiltrada);

    }

    @Transactional
    public MagicalItem createItem(String name, int quality, String type) {

        MagicalItem item = new MagicalItem(name, quality, type);
        item.persist();
        return item;

    }

    @Transactional
    public void createItems(List<MagicalItem> itemList){

        for (MagicalItem item : itemList){

            item.persist();

        }

    }

    @Transactional
    public void deleteItem(MagicalItem magicalItem){

        Optional<MagicalItem> itemToDelete = loadItem(magicalItem);

        if (itemToDelete.isPresent()){

            itemToDelete.get().delete();


        }

    }

}
