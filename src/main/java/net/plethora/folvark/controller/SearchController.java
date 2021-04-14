package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.ConverterJsonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class SearchController {

    private final DaoProductMap daoProductMap;
    //    private DaoNewsPortal daoNewsPortal;
    private final ConverterJsonService<ProductMap> converterJsonService;

    public SearchController(DaoProductMap daoProductMap, ConverterJsonService<ProductMap> converterJsonService) {
        this.daoProductMap = daoProductMap;
        this.converterJsonService = converterJsonService;
    }

    @PostMapping("/search")
    public @ResponseBody
    String[] search(@RequestBody String input) {
        String sr = input.replace("\"", "");
        return converterJsonService.toJSONList(daoProductMap.findByName(sr));
    }

    @GetMapping("/search/{input}")
    public String searchAll(@PathVariable("input") String input, Model model) {

        return "map-page";
    }

}