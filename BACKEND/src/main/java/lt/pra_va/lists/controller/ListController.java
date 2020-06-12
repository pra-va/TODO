package lt.pra_va.lists.controller;

import lt.pra_va.lists.dto.AllLists4Client;
import lt.pra_va.lists.dto.CreateListCommand;
import lt.pra_va.lists.model.List;
import lt.pra_va.lists.services.ListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ListController {

    @Autowired
    private ListServices services;

    @GetMapping("/hello")
    public String helloWorld() {
        return "<h1>Hello, World!</h1>";
    }

    @PostMapping("/create")
    public boolean createList(@RequestBody CreateListCommand command) {
        if (command != null) {
            services.createList(command.getListName(), command.getListItemPayloads());
            return true;
        }
        return false;
    }

    @GetMapping("/lists")
    public AllLists4Client getAllLists() {
        return services.getAllLists();
    }

}
