package homework8.models;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@RequiredArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "person")
//@NoArgsConstructor
public class Contact implements Serializable {
    @JacksonXmlProperty(localName = "person-name")
    private String name;
    @JacksonXmlProperty(localName = "person-phoneNumber")
    private String phoneNumber;


    public String toString() {
        return "Contact: " + name + " | " + "Tel.# " + phoneNumber + " |";
    }
}
