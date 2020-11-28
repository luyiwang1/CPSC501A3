import java.lang.reflect.Field;
import java.util.List;


public class Object1{

    private List<Object> objects;
    private String class;
    private int id;
    private String type;
    private List<Object> fields;
    private String name;
    private String declaringclass;
    private int value;



    public Object1(List<Object> objects) {
        this.objects = objects;
    }

    public Object1(int id, String type, List<Object> fields){
        this.id = id;
        this.type = type;
        this.fields = fields;
    }

    public Object1(String name, String declaringclass, int value) {
        this.name = name;
        this.declaringclass = declaringclass;
        this.value = value;
    }

	public Object1(String inclass, String inid, String intype, String inname, String indeclaringclass, String invalue) {
	}

}
