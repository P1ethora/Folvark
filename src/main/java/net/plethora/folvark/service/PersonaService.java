package net.plethora.folvark.service;

import net.plethora.folvark.dao.repo.ElementsPersonRepository;
import net.plethora.folvark.models.enumerationmodels.ElementProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private final ElementsPersonRepository elementsPersonRepository;

    public PersonaService(ElementsPersonRepository elementsPersonRepository) {
        this.elementsPersonRepository = elementsPersonRepository;
    }

    public List<ElementProfile> getElementsProfile() {
        return elementsPersonRepository.findAll();
    }

}
