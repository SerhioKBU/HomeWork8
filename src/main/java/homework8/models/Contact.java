package homework8.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@JacksonXmlRootElement(localName = "person")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class Contact implements Serializable {

    @JsonProperty("person-name")
    @JacksonXmlProperty(localName = "person-name")
    private String name;
    @JsonProperty("person-phoneNumber")
    @JacksonXmlProperty(localName = "person-phoneNumber")
    private String phoneNumber;

    public String toString() {
        return "Contact: " + name + " | " + "Tel.# " + phoneNumber + " |";
    }
}
