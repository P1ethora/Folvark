package net.plethora.folvark.service;

import net.plethora.folvark.dao.repo.BagMapRepository;
import net.plethora.folvark.dao.repo.ElementsPersonRepository;
import net.plethora.folvark.dao.repo.ProductMapRepository;
import net.plethora.folvark.models.BagMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.enumerationmodels.ElementProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonaService {

    private final ElementsPersonRepository elementsPersonRepository;
    private final BagMapRepository bagMapRepository;
    private final ProductMapRepository productMapRepository;

    public PersonaService(ElementsPersonRepository elementsPersonRepository, BagMapRepository bagMapRepository,
                          ProductMapRepository productMapRepository) {
        this.elementsPersonRepository = elementsPersonRepository;
        this.bagMapRepository = bagMapRepository;
        this.productMapRepository = productMapRepository;
    }

    public List<ElementProfile> getElementsProfile() {
        return elementsPersonRepository.findAll();
    }

    public List<ProductMap> getPurchasedMap(String idBugMap) {
        ArrayList<ProductMap> productMaps = new ArrayList<>();
        ArrayList<String> maps = bagMapRepository.findById(idBugMap).orElse(null).getMaps();

        if (maps != null) {

            for (String idMap : maps) {
                productMaps.add(productMapRepository.findById(idMap).orElse(null));
            }
        }
        
        return productMaps;
    }

    public void saveBugMap(BagMap bagMap) {
        bagMapRepository.save(bagMap);
    }


}
