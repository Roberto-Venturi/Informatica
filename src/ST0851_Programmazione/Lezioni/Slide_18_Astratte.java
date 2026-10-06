package ST0851_Programmazione.Lezioni;

public class Slide_18_Astratte {

  static abstract class Figura { // CLASSE ASTRATTA
    private final double x;
    private final double y;

    public Figura(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public double get_x() {
      return x;
    }

    public double get_y() {
      return y;
    }

    public abstract double area(); // METODO ASTRATTO

    public abstract double perimetro(); // METODO ASTRATTO
  }

  static class Ellisse extends Figura {
    protected final double a;
    private final double b;

    public Ellisse(double x, double y, double a, double b) {
      super(x, y);
      this.a = a;
      this.b = b;
    }

    public double get_a() {
      return a;
    }

    public double get_b() {
      return b;
    }

    public double area() {
      return Math.PI * a * b;
    }

    public double perimetro() {
      return 2 * Math.PI * Math.sqrt((a * a + b * b) / 2);
    }
  }

  static class Cerchio extends Ellisse {
    public Cerchio(double x, double y, double r) {
      super(x, y, r, r);
    }

    public double get_raggio() {
      return super.get_a();
    }

    public double perimetro() {
      return 2 * Math.PI * a;
    }
  }

  static class PoligonoRegolare extends Figura {
    private final int lati;
    private final double apotema;
    private final double lato;

    public PoligonoRegolare(double x, double y, int lati, double apotema) {
      super(x, y);
      this.lati = lati;
      this.apotema = apotema;
      double raggio = apotema / Math.cos(Math.PI / lati);
      this.lato = 2 * raggio * Math.sin(Math.PI / lati);
    }

    public int get_lati() {
      return lati;
    }

    public double get_apotema() {
      return apotema;
    }

    public double get_lato() {
      return lato;
    }

    public double area() {
      return lati * (apotema * lato / 2);
    }

    public double perimetro() {
      return lati * lato;
    }
  }

  /*
  1. Per ogni coppia (C,D) di classi nella terza versione della gerarchia di classi,
     dire se C è sottotipo di D, se D è sottotipo di C,
     se C e D non sono in relazione (in tal caso, identificare
     l’antenato comune più specifico che è supertipo di entrambe)
     -----
     Ellisse <: Figura
     PoligonoRegolare <: Figura
     Cerchio <: Figura
     Cerchio <: Ellisse

     Ellisse e PoligonoRegolare (non in relazione) <: Figura
   */

  /*
  2. Ripetere l’esercizio precedente, ... con la gerarchia seguente:
     public abstract class A { }
     public abstract class B extends A { }
     public class C { }
     public class D extends B { }
     public class E extends B { }
     public class F extends C { }
     -----
     B <: A
     D <: B
     D <: A
     E <: B
     E <: A
     F <: C
     A e C (non in relazione)  <:  Object
   */

  /*
  3. Arricchire la gerarchia di classi (terza versione) in modo da aggiungere le classi
     Pentagono e Miriagono (poligono regolare con 10000 lati),
     assicurandosi di realizzare solo i metodi strettamente necessari
   */
  static class Pentagono extends PoligonoRegolare {
    public Pentagono(double x, double y, double raggio) {
      super(x, y, 5, raggio);
    }
  }

  static class Miriagono extends PoligonoRegolare {
    public Miriagono(double x, double y, double raggio) {
      super(x, y, 10000, raggio);
    }
  }

  /*
  4. Arricchire ulteriormente la gerarchia con le classi
     Trapezio, TrapezioIsoscele e TrapezioRettangolo
     (per quest’ultima, si può assumere che il lato obliquo sia sempre quello di destra)
   */
  static class Trapezio extends Figura {
    private final double baseMaggiore;
    private final double baseMinore;
    private final double latoSx;
    private final double latoDx;
    private final double altezza;

    public Trapezio(double baseMaggiore, double baseMinore, double latoSx, double latoDx) {
      super(0, 0);
      this.baseMaggiore = baseMaggiore;
      this.baseMinore = baseMinore;
      this.latoSx = latoSx;
      this.latoDx = latoDx;
      double sp = (latoDx + latoSx + (baseMaggiore - baseMinore)) / 2; //semiPerimetro
      double areaT = Math.sqrt(sp * (sp - latoSx) * (sp - latoDx) * (sp - (baseMaggiore - baseMinore)));
      this.altezza = 2 * areaT / (baseMaggiore - baseMinore);
    }

    public double getBaseMaggiore() {
      return baseMaggiore;
    }

    public double getBaseMinore() {
      return baseMinore;
    }

    public double getLatoSx() {
      return latoSx;
    }

    public double getLatoDx() {
      return latoDx;
    }

    public double getAltezza() {
      return altezza;
    }

    public double area() {
      return (baseMaggiore + baseMinore) * altezza / 2;
    }

    public double perimetro() {
      return baseMaggiore + baseMinore + latoSx + latoDx;
    }
  }

  static class TrapezioIsoscele extends Trapezio {
    public TrapezioIsoscele(double baseMaggiore, double baseMinore, double lato) {
      super(baseMaggiore, baseMinore, lato, lato);
    }

    public double getLato() {
      return super.getLatoDx();
    }
  }

  static class TrapezioRettangolo extends Trapezio {
    public TrapezioRettangolo(double baseMaggiore, double baseMinore, double lato) {
      double altezza = Math.sqrt(Math.pow(lato, 2) - Math.pow(baseMaggiore - baseMinore, 2));
      super(baseMaggiore, baseMinore, altezza, lato);
    }
  }


  static void main(String[] args) {
    TrapezioIsoscele ti = new TrapezioIsoscele(10, 6, 5);
    StdOut.print("new TrapezioIsoscele(10,6,5)  => ");
    StdOut.println(ti.getBaseMaggiore() + "   " + ti.getBaseMinore() + "   "
        + ti.getLatoSx() + "   " + ti.getLatoDx() +  "   h = "  + ti.getAltezza());
    StdOut.println("ti.getAltezza() = " + ti.getAltezza());
    StdOut.println("ti.area() = " + ti.area() + "  -  ti.perimetro() = " + ti.perimetro());
  }

}
