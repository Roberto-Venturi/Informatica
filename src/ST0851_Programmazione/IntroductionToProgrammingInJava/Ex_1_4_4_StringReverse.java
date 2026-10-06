import java.util.Arrays;

public class Ex_1_4_4_StringReverse {

  public static void main(String[] args) {
    String[] text = {"Sotto","La","Panca","Capra","Campa","Crepa"};
    int elems = text.length;
    System.out.println(Arrays.toString(text));
    for (int ii=0; ii<elems/2; ii++) {
      String temp = text[ii];
      text[ii] = text[elems -1 - ii];
      text[elems -1 - ii] = temp;
    }
    System.out.println("\r\n----- Reversed -----");
    System.out.println(Arrays.toString(text));
  }

}
