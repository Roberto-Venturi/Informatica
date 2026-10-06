package ST0851_Programmazione.Lezioni;

public class Slide_20_Pacchetti {

  static class ClasseA {
    public void m1() {
      StdOut.println("ClasseA->m1()");
      m2();
    }

    private void m2() { //<- ATTENZIONE!!!!
      StdOut.println("ClasseA->m2()");
    }
  }

  static class ClasseB extends ClasseA {
    public void m2() {
      StdOut.println("ClasseB->m2()");
    }
  }


  static void main(String[] args) {
    ClasseA a = new ClasseB();
    a.m1();
//    File dir = new File(".");
//    StdOut.println(dir.getAbsoluteFile());




  }

}
