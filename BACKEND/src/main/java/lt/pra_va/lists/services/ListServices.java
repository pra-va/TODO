package lt.pra_va.lists.services;

import lt.pra_va.lists.dao.ListItemRepository;
import lt.pra_va.lists.dao.ListRepository;
import lt.pra_va.lists.dto.AllLists4Client;
import lt.pra_va.lists.dto.List4Client;
import lt.pra_va.lists.dto.ListItem4Client;
import lt.pra_va.lists.dto.ListItemPayload;
import lt.pra_va.lists.model.List;
import lt.pra_va.lists.model.ListItem;
import lt.pra_va.lists.model.ListItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
public class ListServices {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ListItemRepository listItemRepository;

    @Transactional
    public void createList(String name, java.util.List<ListItemPayload> listItemPayloads) {
       List newList = new List();
       newList.setName(name);
       int itemIterator = 0;

       for (ListItemPayload item: listItemPayloads) {
            ListItem listItem = new ListItem();
            ListItemDetails listDetails = new ListItemDetails();
            listDetails.setDateCreated(new Date(System.currentTimeMillis()));
            listDetails.setListItem(listItem);
            listItem.setListItemDetails(listDetails);
            listItem.setPayload(item.getPayload());
            listItem.setStatus(item.getImportanceStatus());
            newList.addItem(listItem, itemIterator++);
            listItemRepository.save(listItem);
       }

        listRepository.save(newList);
    }

    @Transactional
    public void addItems(String name, java.util.List<ListItemPayload> listItemPayloads) {
        List list = listRepository.findListByName(name);
        int itemIterator = list.getNextKey();

        for (ListItemPayload item: listItemPayloads) {
            ListItem listItem = new ListItem();
            ListItemDetails listDetails = new ListItemDetails();
            listDetails.setDateCreated(new Date(System.currentTimeMillis()));
            listDetails.setListItem(listItem);
            listItem.setListItemDetails(listDetails);
            listItem.setPayload(item.getPayload());
            listItem.setStatus(item.getImportanceStatus());
            list.addItem(listItem, itemIterator++);
            listItemRepository.save(listItem);
        }

        listRepository.save(list);
    }

    public AllLists4Client getAllLists() {
        java.util.List<List> lists = listRepository.findAll();
        
        AllLists4Client allLists4Client = new AllLists4Client();
        java.util.List<List4Client> listsForClients = new ArrayList<>();

        for (List list: lists) {
            List4Client list4Client = new List4Client();
            java.util.List<ListItem4Client> listItem4Clients = new ArrayList<>();
            for (Map.Entry<Integer, ListItem> entry : list.getItems().entrySet()) {
                listItem4Clients.add(new ListItem4Client(
                        entry.getKey(),
                        entry.getValue().getPayload(),
                        entry.getValue().getListItemDetails().getDateCreated(),
                        entry.getValue().getListItemDetails().getDateEdited(),
                        entry.getValue().getStatus())
                );
            }
            list4Client.setItems(listItem4Clients);
            list4Client.setListName(list.getName());
            listsForClients.add(list4Client);
        }

        allLists4Client.setLists(listsForClients);

        return allLists4Client;
    }
}



















