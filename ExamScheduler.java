import java.util.Scanner;
import java.util.Iterator;
import structure5.*;

public class ExamScheduler {
/*
  (method) read in data from file, creating student objects
    and somewhere (list/vector?) to store them
    steal from the comparator lab
*/

  protected Vector<Student> students = new Vector<Student>();
  protected GraphListUndirected<String, Integer> catalog
    = new GraphListUndirected<String, Integer>(); //specify types
  protected Vector<SinglyLinkedList> schedule = new Vector<SinglyLinkedList>();

  /** reads in students from a properly formatted file
  */
  public void read() {
    Scanner sc = new Scanner(System.in);

    // If scanner is empty, no more students
    while (sc.hasNextLine()) {
      String name = sc.nextLine();
      String class1 = sc.nextLine();
      String class2 = sc.nextLine();
      String class3 = sc.nextLine();
      String class4 = sc.nextLine();

      Student student = new Student(name, class1, class2, class3, class4);
      students.add(student);
    }
  }

  public void createGraph(Vector<Student> students) {
    for (Student student : students) {
      Vector<String> classes = student.getClasses();
      addVertices(classes);
      addEdges(classes);
    }
  }

  //adds edges
  protected void addEdges(Vector<String> classes) {
    for (int i = 0; i < 4; i++) {
      for (int j = 1; j < 4; j++) {
        if (i < j) {
          //create edge
          if (!catalog.containsEdge(classes.get(i), classes.get(j))) {
            catalog.addEdge(classes.get(i), classes.get(j), 1);
          } else {
            //update edge
            Edge<String, Integer> edge = catalog.getEdge(classes.get(i), classes.get(j));
            edge.setLabel(edge.label() + 1);
          }
        }
      }
    }

  }


  //add vertices to class
  protected void addVertices(Vector<String> classes) {
    for (int i = 0; i < 4; i++) {
      String course = classes.get(i);
      //add class vertex
      if (!catalog.contains(course)) {
        catalog.add(course);
      }
    }
  }


  public Vector<SinglyLinkedList> createSchedule() {

    while (!catalog.isEmpty()) {
      SinglyLinkedList<String> timeSlot = new SinglyLinkedList<String>();
      Iterator<String> it = catalog.iterator();
      timeSlot.add(it.next());

      while(it.hasNext()) {
        String temp = it.next();
        for(String course : timeSlot) {
          if (!catalog.containsEdge(course, temp)) {
            timeSlot.add(temp);
            catalog.remove(temp); //THIS IS VERY SUS
          }
        }
      }

      schedule.add(timeSlot);
    }
    return schedule;
  }


  public static void main(String[] args) {

  }
}


/*
while G is not empty:
  make an empty list L
  pick some vertex v in G //use the iterator
  L.add(v)
  for each vertex u =/= v in G: //use an iterator? graphs have nice iterators
    if u is not a neighbor of any vertex in L
      add u to L
  remove all vertices of L from G
  add L to C

  return C as the list of exam schedules
*/

/*
  (method) make a GraphListUndirected G of classes
  - vertex labels = course names
  - edges = ehhhh doesn't *really* matter. integer is good idea. options:
    - number of students taking both courses the edges connect
    - placeholder that isn't actually used (i.e. 0 or 1, probably)
  possible pseudocode:
    for each student:
      for each class:
        if a class is not in G
          add it to G
          connect it to all the other classes student is in, with an edge value of 1
        else if a class is in G
          if it's not connected to any of the student's other classes:
            connect it with an edge value of 1
          else increment the edge value by 1 //not necessary if we decide edge values don't matter

  make a structure C to hold a collection of lists //list if we're doing just the basics, bst if we want to try some extensions

  (method) find a decent schedule that fulfills the requirements
  pseudocode (from video):
    while G is not empty:
      make an empty list L
      pick some vertex v in G //use the iterator
      L.add(v)
      for each vertex u =/= v in G: //use an iterator? graphs have nice iterators
        if u is not a neighbor of any vertex in L
          add u to L
      remove all vertices of L from G
      add L to C

      return C as the list of exam schedules
*/
