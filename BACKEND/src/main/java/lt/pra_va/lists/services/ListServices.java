package lt.pra_va.lists.services;

import lt.pra_va.lists.dao.ListRepository;
import lt.pra_va.lists.model.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ListServices {

    @Autowired
    private ListRepository listRepository;

    public void createList(String name, String[] items) {
        List list = new List();
        list.addMultipleItems(items);
        list.setName(name);
        listRepository.save(list);
    }

    public void addItems(String[] items, String name) {
        List list = listRepository.findListByName(name);
        list.addMultipleItems(items);
        listRepository.save(list);
    }

    public java.util.List<List> getAllLists() {
        return listRepository.findAll();
    }
}
