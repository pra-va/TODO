package lt.pra_va.lists.controller;

import lt.pra_va.lists.dto.CreateListCommand;
import lt.pra_va.lists.services.ListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    @Autowired
    private ListServices services;

    @GetMapping("/hello")
    public String helloWorld() {
        return "<h1>Hello, World!";
    }

    @PostMapping("/create")
    public int createList(@RequestBody CreateListCommand command) {
        if (command != null) {
            services.createList(command.getName(), command.getListItems());
        }
        return services.getAllLists().size();
    }

}
