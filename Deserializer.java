import java.util.HashMap;

import javax.swing.text.Document;

public class Deserializer {
    private HashMap<String, Object> map = new HashMap<>();

    public Object deserialize(Document doc) throws Exception {
        Element rootNode = doc.getRootElements();
        List<Element> list = rootNode.getChilren("Object");
    }
}
