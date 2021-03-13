package net.plethora.folvark.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.plethora.folvark.models.ProductMap;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ConverterJsonService {

    @SneakyThrows
    public String toJSON(ProductMap product) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(product);
    }

    @SneakyThrows
    public String[] toJSONList(List<ProductMap> list) {
        String[] string = new String[list.size()];
        ObjectMapper mapper = new ObjectMapper();
        int i = -1;
        for (ProductMap productMap : list) {
            i++;
            string[i] = mapper.writeValueAsString(productMap);
        }
        return string;
    }
}