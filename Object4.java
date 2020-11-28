public class Object4 {
    private Object1[] obj1List;

    public Object4(){
    }

    public Object4(int length){
        obj1List = new Object1[length];
    }

    public void seteachobject(int item, Object1 obj1){
        obj1List[item] = obj1;
    }

}
