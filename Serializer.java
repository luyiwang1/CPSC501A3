import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.KeyStore.Entry.Attribute;
import java.util.IdentityHashMap;
import java.util.function.IntToDoubleFunction;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

public class Serializer {
    private static IndentityHashMap<Object, String> map = new IdentityHashMap<>();
    private int id = 0;
    private Document doc;

    public Document serialize(Object obj) throws IllegalAccessException {
        id = 0;
        control(obj);
        Element root = new Element("serialized");
        Document doc = new Document(root);
        
        map.clear();
        return doc;
    }

    public static Document control(Object objtoSer) throws IllegalAccessException{
        Integer id = getId(objtoSer);
        
        Element objElement = createObjElement(objtoSer, id);

        if (objtoSer.getClass().isArray()) {
            objElement = serArray(objtoSer);
        } else {
            Field[] fields = objtoSer.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(objtoSer);
                Element fElement = createFElement(f);

                if (f.getType().isPrimitive()) {
                    Element VElement = createVElement(value);
                    fElement.addContent(VElement);
                } else {
                    Element refElement = createRefElement(getId(value).toString());
                    fElement.addContent(refElement);
                    control(value);
                }
                objElement.addContent(fElement);
            }
        }
        Document.addContent(objElement);
    }

    public static Integer getId(Object object) {
        int objId = id;

        if (map.containsKey(object)) {
            objId = map.get(object);
        } else {
            map.put(object, objId);
            id++;
        }
        return objId;
    }

    public static Element createObjElement(Object obj, Integer id) {
        Element objElement = new Element("object");
        objElement.setAttribute(new Attribute("class", obj.getClass().getName()));
        objElement.setAttribute(new Attribute("id", id.toString()));
        return objElement;
    }

    public Element serArray(Object arrObject) throws IllegalAccessException {
        Element objElement = ObjArrayElement(arrObject, getId(arrObject));

        Class type = arrObject.getClass().getComponentType();

        for (int i = 0; i < Array.getLength(arrObject); i++) {
            if (Array.get(arrObject, i) == null) {
                Element nullElement = createVElement(null);
                objElement.addContent(nullElement);
            } else if (type.isPrimitive()) {
                Element VElement = createVElement(Array.get(arrObject, i));
                objElement.addContent(VElement);
            } else {
                Element refElement = createRefElement(getId(Array.get(arrObject, i)).toString());
                objElement.addContent(refElement);
                control(Array.get(arrObject, i));
            }
        }
        return objElement;
    }

    public Element ObjArrayElement(Object obj, Integer id) {
        Element objElement = new Element("object");
        objElement.setAttribute(new Attribute("class", obj.getClass().getName()));
        objElement.setAttribute(new Attribute("id", id.toString()));
        objElement.setAttribute(new Attribute("length", Integer.toString(Array.getLength(obj))));
        return objElement;
    }

    public static Element createFElement(Field field) {
        Element fElement = new Element("field");
        fElement.setAttribute(new Attribute("name", field.getName()));
        fElement.setAttribute(new Attribute("declaringClass", field.getDeclaringClass().getName()));
        return fElement;
    }

    public static Element createRefElement(String id) {
        Element refElement = new Element("reference");
        refElement.setText(id);
        return refElement;
    }

    private Element createVElement(Object objectValue) {
        Element VElement = new Element("value");
        if (objectValue == null) {
            VElement.setText("null");
        } else {
            VElement.setText(objectValue.toString());
        }
        return VElement;
    }

}
