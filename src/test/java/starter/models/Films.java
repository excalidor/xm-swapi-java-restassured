package starter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Films {
    private int count;
    private String next;
    private String previous;
    private List<Film> results;
}
