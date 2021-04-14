package net.plethora.folvark.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.plethora.folvark.models.ProductMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConverterJsonService <T> {

    private final ObjectMapper mapper;

    public ConverterJsonService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @SneakyThrows
    public String toJSON(T object) {
        return mapper.writeValueAsString(object);
    }

    @SneakyThrows
    public String[] toJSONList(List<T> list) {
        String[] string = new String[list.size()];
        int i = -1;
        for (T object : list) {
            i++;
            string[i] = mapper.writeValueAsString(object);
        }
        return string;
    }
}