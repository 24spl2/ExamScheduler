import structure5.*;

public class Student {

	//instance vars:
	protected String name; //first middle last
  protected Vector<String> classes;


	/**Student Constructor
	* @param aName, class1, class2, class3, class4: all these are to be fully initialized
	* @pre none
	* @post a new student will be made with these values
	*/
	public Student(String aName, String class1, String class2, String class3, String class4) {
		name = aName;
    classes = new Vector<String>(4);
		classes.add(class1);
    classes.add(class2);
    classes.add(class3);
    classes.add(class4);
	}

	/**
	* @post will return the name of the student called
	*/
	public String getName() {
		return name;
	}

	/**
	* @post will return the address of the student called
	*/
	public Vector<String> getClasses() {
		return classes;
	}



	/**
	* @post will return a string representation of the student
	*/
	public String toString() {
		return "\n Name: " + name + "\n Classes: " + classes;
	}

}






/*
public void read() {
  Scanner sc = new Scanner(System.in);

  // If scanner is empty, no more students
  while (sc.hasNextLine()) {
    String name = sc.nextLine();
    String address = sc.nextLine();
    long campusPhone = sc.nextLong();
    int su = sc.nextInt();
    long homePhone = sc.nextLong();

    Student student = new Student(name, address, campusPhone, su, homePhone);
    phonebook.add(student);

    //get rid of the dashes between students
    if (sc.next().equals("--------------------")) {
      sc.nextLine();
    }
  }
}
*/
