package ST0851_Programmazione.Lezioni;

public class Slide_04_Codifica {

  /*
  1.  Determinare la rappresentazione del numero x ∈ N (senza segno) con n bit?
      2 alla n -1
  2.  Quanti bit servono per rappresentare il numero x ∈ N (senza segno)?
      log in base 2 di N +1
  3.  Quale numero naturale è rappresentato dal numerale cn−1 ⋯ c2c1c0?


  4.  Convertire x(base b) in base b1 dove b e b1 appartengono a {2, 8, 16}
      da 2 a  8: prendendo i bit a gruppi di 3 (da destra) 001 100 110 = 146(8)
      da 2 a 16: prendendo i bit a gruppi di 4 (da destra) 0 0110 0110 = 066(16)
      da 8 a  2: convertendo ogni cifra in 3 bit      146(8) = 001 100 110 (2)
      da 16 a 2: convertendo ogni cifra in 4 bit      66(16) = 0110 0110 (2)
      da 8 a 16: passando per le conversioni 8 -> 2 -> 16
  5.  Determinare la rappresentazione in complemento a due con n bit
      del numero x che appartiene a Z
      Per i numeri positivi vedi sopra;
      per quelli negativi scrivo il numero positivo poi nego ogni bit e d aggiunto 1
  6.  Quale numero intero è rappresentato in complemento a due con n bit
      dal numerale C(pedice n -1) ... C2 C1 C0?
      Se il bit C(pedice n -1) è positivo opero la conversione da base 2 a base 10
      Altrimenti nego ogni bit, aggiungo 1 ed opero la conversione da base 2 a base 10
  7.  Calcolare la rappresentazione in complemento a due di x - y
      dove x ed y ed sono numeri interi rappresentati in complemento a due con n bit
      Si sommano i due numeri (fare attenzione agli overflow)
   */
}
