package net.plethora.folvark.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.plethora.folvark.models.Comment;
import net.plethora.folvark.models.Reply;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConverterJsonService<T> {

    private final ObjectMapper mapper;

    public ConverterJsonService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @SneakyThrows
    public String toJSON(T object) {
        String ii = mapper.writeValueAsString(object);
        System.out.println(ii);
        return ii;
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

    @SneakyThrows
    public Reply formJSON(String json) {
        return mapper.readValue(json, Reply.class);
    }
}