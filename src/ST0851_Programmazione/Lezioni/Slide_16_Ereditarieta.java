package ST0851_Programmazione.Lezioni;

public class Slide_16_Ereditarieta {

  /*
  Elencare tutte le relazioni di sottotipo che si possono
  dedurre date le seguenti definizioni (incomplete) di classi

  Class A { ... }
  class B extends A { ... }
  class C extends B { ... }
  class D extends A { ... }
  class E { ... }

  B <: A
  C <: B
  C <: A
  D <: A
  A, B, C, D, E <: Object

   */

  public static class Counter {
    protected int counter;

    public Counter() {
      this.counter = -1;
    }

    public int get_next() {
      counter++;
      return counter;
    }
  }

  public static class CounterBase extends Counter {
    int base;

    public CounterBase(int base) {
      this.base = base;
    }

    public int get_next() {
      counter++;
      if (counter == base)
        counter = 0;
      return counter;
    }
  }

  public static void incAndPrint(Counter counter) {
    StdOut.println(counter.getClass().toString() + " " + counter.get_next());
  }

  static void main(String[] args) {
    Counter c1 = new Counter();
    StdOut.println("(new Counter()).get_next() => " + c1.get_next());
    StdOut.println("               .get_next() => " + c1.get_next());
    StdOut.println("               .get_next() => " + c1.get_next());
    StdOut.println("               .get_next() => " + c1.get_next());
    StdOut.println("               .get_next() => " + c1.get_next());

    CounterBase cb = new CounterBase(3);
    StdOut.println("(new CounterBase(3)).get_next() => " + cb.get_next());
    StdOut.println("                    .get_next() => " + cb.get_next());
    StdOut.println("                    .get_next() => " + cb.get_next());
    StdOut.println("                    .get_next() => " + cb.get_next());
    StdOut.println("                    .get_next() => " + cb.get_next());
  }


}
