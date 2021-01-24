package lt.pra_va.template.controller;

import lt.pra_va.template.services.ListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TemplateController {

    @Autowired
    private ListServices services;

    @GetMapping("/hello")
    public String helloWorld() {
        return "<h1>Hello, World!</h1>";
    }

}
