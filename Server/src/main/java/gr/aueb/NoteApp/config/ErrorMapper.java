package gr.aueb.NoteApp.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ErrorMapper {

    public Map<String, String> createErrorMap(String errorMessage) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", errorMessage);
        return errorMap;
    }
}
