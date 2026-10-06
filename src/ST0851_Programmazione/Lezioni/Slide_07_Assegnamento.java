package ST0851_Programmazione.Lezioni;

public class Slide_07_Assegnamento {

  /*
  1.  Descrivere il valore prodotto e l’effetto della seguente espressioni impura, supponendo
      che a sia una variabile di tipo int
      a = a++
      incrementa di 1 la variabile a (dopo averle assegnato il suo stesso valore)
  2.  Se a è una variabile di tipo int, per quali valori di a le condizioni a++ > 0 e ++a > 0
      sono vere?
      a++ > 0 per a > 0 (l'incremento viene effettuato DOPO la verifica)
      ++a > 0 per a >= 0  (l'incremento viene effettuato PRIMA della verifica)
  3.  Scrivere un programma che legge un numero intero 0 <= x <64 e determina il numero
      di bit che occorrono per rappresentare x.
      Il programma deve stampare “fuori scala” se x non è compreso nell’intervallo indicato.
  */
  static void slide_07_3() {
    int x = (int) (Math.random() * 100) - 20;
    if (x < 0 || x > 64) {
      StdOut.println(x + " è fuori scala");
    } else {
      int bits = (int) (Math.log(x) / Math.log(2) + 0.9999);
      StdOut.println("per rappresentare " + x + " occorrono " + bits + " bits");
    }
  }

  /*
  4.  Scrivere un programma che legge un numero intero 0 <= x <64 e determina il numero di
      bit a 1 nella rappresentazione binaria di x,
      stampando “fuori scala” se non è compreso nell’intervallo indicato.
  */
  static void slide_07_4() {
    int x = (int) (Math.random() * 100) - 20;
    if (x < 0 || x > 64) {
      StdOut.println(x + " è fuori scala");
    } else {
      int temp = x;
      int bit1 = 0;
      bit1 += temp % 2;
      temp = temp / 2;
      bit1 += temp % 2;
      temp = temp / 2;
      bit1 += temp % 2;
      temp = temp / 2;
      bit1 += temp % 2;
      temp = temp / 2;
      bit1 += temp % 2;
      temp = temp / 2;
      bit1 += temp % 2;
      temp = temp / 2;
      bit1 += temp % 2;
      StdOut.println(Integer.toBinaryString(x));  // per verifica
      StdOut.println("per rappresentare " + x + " occorrono " + bit1 + " bits a 1");
    }
  }

  /*
  5. Scrivere un programma che legge una sequenza di tre numeri interi (memorizzati in tre
     variabili distinte) e conta il numero di inversioni della sequenza, ovvero quante volte un
     numero letto prima è più grande di un numero letto dopo.
     [se il terzo numero è minore del primo conta come inversione? ipotizzo SI]
   */
  static void slide_07_5() {
    int n1 = (int) (Math.random() * 100);
    int n2 = (int) (Math.random() * 100);
    int n3 = (int) (Math.random() * 100);
    int inv = 0;
    if (n1 > n2)
      inv++;
    if (n2 > n3)
      inv++;
    if (n1 > n3)
      inv++;
    StdOut.println("Input: " + n1 + ", " + n2 + ", " + n3 + " =>  " + inv + " inversioni");
  }

  static void main(String[] args) {
    slide_07_3();
    StdOut.println("--- 4 ---");
    slide_07_4();
    StdOut.println("--- 5 ---");
    slide_07_5();
  }
}
