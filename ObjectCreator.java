import java.util.Scanner;

public class ObjectCreator {
    static Scanner sc = new Scanner(System.in);

    public ObjectCreator() {
    }

    public static Object main(String[] args) throws Exception {
        Object ob = null;
        System.out.println("Create objects");
        System.out.println("1.Primitive instance variables");
        System.out.println("2.Object contains references to other objects");
        System.out.println("3.Array of primitives");
        System.out.println("4.Array of object references");
        System.out.println("5.Instance of Java's collection classes");
        System.out.println("Make your decision: ");
        String input = sc.nextLine();

        if(input == "1"){
            ob = createPrimitive();
        } else if (input == "2") {
            ob = createRefernce();
        } else if (input == "3") {
            ob = createArrayPrimitive();
        } else if (input == "4") {
            ob = createArrayObject();
        } else if (input == "5") {
            ob = createCollection();
        } else {
            System.out.println("Error! Pls select from 1 to 5.");
        }
        return ob;
    }

    public static Object1 createPrimitive() {
        String inclass;
        String inid;
        String intype;
        String inname;
        String indeclaringclass;
        String invalue;

        System.out.println("Creating Primitive variables...");
        inclass = sc.nextLine();
        inid = sc.nextLine();
        intype = sc.nextLine();
        inname = sc.nextLine();
        indeclaringclass = sc.nextLine();
        invalue = sc.nextLine();

        return new Object1(inclass, inid, intype, inname, indeclaringclass, invalue);
    }

    public static Object2 createRefernce() {
        System.out.println("Creating Refernce Object...");
        Object1 inputObject1 = createPrimitive();
        return new Object2(inputObject1);
    }

    public static Object3 createArrayPrimitive() {
        int[] userList;
        int userinput;
        int sizeofArray;
        String inclass;
        String inid;
        String intype;
        String inname;
        String indeclaringclass;

        System.out.println("Creating primitive array...");
        System.out.println("Enter the size/length for array");

        sizeofArray = sc.nextInt();
        userList = new int[sizeofArray];

        inclass = sc.nextLine();
        inid = sc.nextLine();
        intype = sc.nextLine();
        inname = sc.nextLine();
        indeclaringclass = sc.nextLine();

        for (int i = 0; i < userList.length; i++) {
            System.out.println("Enter the number for array");
            userinput = sc.nextInt();
        }
        return new Object3(inclass, inid, intype, sizeofArray, userList, inname, indeclaringclass);
    }

    public static Object4 createArrayObject(){
        int Size;

        System.out.println("Creating Object array...");
        System.out.println("Enter the size/length for array");

        Size = sc.nextInt();
        Object4 object4 = new Object4(Size);

        for(int i = 0; i < Size; i++){
            object4.seteachobject(i, createPrimitive())
        }
        return object4;
    }

    public static Object5 createCollection() {
        System.out.println("Creating collection...");

        Object5 obj5 = new Object5();
        String input = null;
        while(input != "Exit"){
            obj5.collection(createPrimitive());
            System.out.println("contiue to add or use 'Exit' to quit");
            input = sc.nextLine();
        }
        return obj5;
    }
}
