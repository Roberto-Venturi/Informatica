package ST0851_Programmazione.SimulazioneEsami.Biblioteca;

public class Tester {

  public static void main(String[] args) throws Exception {
    assertThrows(
        () -> { new Libro(null, "Autore"); },
        new IllegalArgumentException(),
        "Costruzione di un libro con titolo nullo: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> { new Libro("Titolo", null); },
        new IllegalArgumentException(),
        "Costruzione di un libro con autore nullo: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> { new Libro("", "Autore"); },
        new IllegalArgumentException(),
        "Costruzione di un libro con titolo vuoto: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> { new Libro("Titolo", ""); },
        new IllegalArgumentException(),
        "Costruzione di un libro con autore vuoto: deve essere rifiutata con eccezione"
    );

    Libro a1 = new Libro("Titolo A", "Autore A");
    Libro a2 = new Libro("Titolo A", "Autore A");
    Libro b1 = new Libro("Titolo B", "Autore A");
    Libro c1 = new Libro("Titolo A", "Autore B");

    assertEquals(
        () -> a1.getTitolo(),
        "Titolo A",
        "Getter del titolo: deve restituire esattamente il titolo impostato in costruzione"
    );

    assertEquals(
        () -> a1.getAutore(),
        "Autore A",
        "Getter dell'autore: deve restituire esattamente l'autore impostato in costruzione"
    );

    assertEquals(
        () -> a1.equals(a1),
        true,
        "Uguaglianza riflessiva: un libro deve essere uguale a sé stesso"
    );

    assertEquals(
        () -> a1.equals(a2),
        true,
        "Uguaglianza per contenuto: due libri con stesso titolo e stesso autore devono risultare uguali"
    );

    assertEquals(
        () -> a2.equals(a1),
        true,
        "Uguaglianza simmetrica: se due libri sono uguali, l'uguaglianza deve valere in entrambe le direzioni"
    );

    assertEquals(
        () -> a1.equals(b1),
        false,
        "Uguaglianza: libri con titolo diverso devono risultare diversi anche se l'autore coincide"
    );

    assertEquals(
        () -> a1.equals(c1),
        false,
        "Uguaglianza: libri con autore diverso devono risultare diversi anche se il titolo coincide"
    );

    assertEquals(
        () -> a1.equals("nonUnLibro"),
        false,
        "Uguaglianza: un libro deve risultare diverso da un oggetto di tipo differente"
    );

    Biblioteca biblioteca = new Biblioteca(4, 2.0f);

    assertArrayEquals(
        () -> biblioteca.daiTutti(),
        new String[][]{},
        "Elenco iniziale: una biblioteca appena creata deve restituire una matrice vuota"
    );

    assertThrows(
        () -> biblioteca.aggiungiLibro(null),
        new IllegalArgumentException(),
        "Inserimento nullo: l'aggiunta di un libro nullo deve essere rifiutata con eccezione"
    );

    assertEquals(
        () -> biblioteca.aggiungiLibro(a1),
        true,
        "Inserimento valido: aggiunta del primo libro in presenza di spazio disponibile"
    );

    assertEquals(
        () -> biblioteca.aggiungiLibro(b1),
        true,
        "Inserimento valido: aggiunta del secondo libro in presenza di spazio disponibile"
    );

    assertEquals(
        () -> biblioteca.aggiungiLibro(c1),
        true,
        "Inserimento valido: aggiunta del terzo libro in presenza di spazio disponibile"
    );

    assertEquals(
        () -> biblioteca.aggiungiLibro(a2),
        true,
        "Inserimento di duplicato logico: due istanze uguali per contenuto devono poter coesistere salvo vincoli non indicati"
    );

    Libro extra = new Libro("Titolo Extra", "Autore Extra");
    assertEquals(
        () -> biblioteca.aggiungiLibro(extra),
        false,
        "Saturazione capienza: l'aggiunta oltre il massimo deve fallire restituendo false"
    );

    assertArrayEquals(
        () -> biblioteca.daiTutti(),
        new String[][]{
            {"Titolo A", "Autore A"},
            {"Titolo B", "Autore A"},
            {"Titolo A", "Autore B"},
            {"Titolo A", "Autore A"}
        },
        "Elenco completo: la matrice deve contenere tutti i libri aggiunti nelle coppie titolo-autore"
    );

    assertThrows(
        () -> biblioteca.prestaLibro(null, "Autore A"),
        new IllegalArgumentException(),
        "Prestito con titolo nullo: deve essere rifiutato con eccezione"
    );

    assertThrows(
        () -> biblioteca.prestaLibro("Titolo A", null),
        new IllegalArgumentException(),
        "Prestito con autore nullo: deve essere rifiutato con eccezione"
    );

    assertThrows(
        () -> biblioteca.prestaLibro("", "Autore A"),
        new IllegalArgumentException(),
        "Prestito con titolo vuoto: deve essere rifiutato con eccezione"
    );

    assertThrows(
        () -> biblioteca.prestaLibro("Titolo A", ""),
        new IllegalArgumentException(),
        "Prestito con autore vuoto: deve essere rifiutato con eccezione"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo Inesistente", "Autore Inesistente"),
        false,
        "Prestito di libro assente: deve restituire false"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo B", "Autore A"),
        true,
        "Prestito di libro presente e disponibile: deve restituire true e segnare il libro come in prestito"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo B", "Autore A"),
        false,
        "Prestito ripetuto dello stesso libro: deve restituire false poiché non più disponibile"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo A", "Autore A"),
        true,
        "Prestito con copie multiple: deve riuscire se esiste almeno una copia disponibile"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo A", "Autore A"),
        true,
        "Prestito con copie multiple: una seconda richiesta deve riuscire se era presente una seconda copia disponibile"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo A", "Autore A"),
        false,
        "Prestito con copie multiple: una terza richiesta deve fallire se tutte le copie risultano in prestito"
    );

    assertThrows(
        () -> biblioteca.restituisciLibro(null, "Autore A", 0),
        new IllegalArgumentException(),
        "Restituzione con titolo nullo: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> biblioteca.restituisciLibro("Titolo A", null, 0),
        new IllegalArgumentException(),
        "Restituzione con autore nullo: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> biblioteca.restituisciLibro("", "Autore A", 0),
        new IllegalArgumentException(),
        "Restituzione con titolo vuoto: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> biblioteca.restituisciLibro("Titolo A", "", 0),
        new IllegalArgumentException(),
        "Restituzione con autore vuoto: deve essere rifiutata con eccezione"
    );

    assertThrows(
        () -> biblioteca.restituisciLibro("Titolo A", "Autore A", -1),
        new IllegalArgumentException(),
        "Restituzione con giorni di ritardo negativi: deve essere rifiutata con eccezione"
    );

    assertEquals(
        () -> biblioteca.restituisciLibro("Titolo Inesistente", "Autore Inesistente", 3),
        -1f,
        "Restituzione di libro assente: deve restituire -1"
    );

    assertEquals(
        () -> biblioteca.restituisciLibro("Titolo A", "Autore B", 1),
        -1f,
        "Restituzione di libro presente ma non in prestito: deve restituire -1"
    );

    assertEquals(
        () -> biblioteca.restituisciLibro("Titolo B", "Autore A", 0),
        0f,
        "Restituzione puntuale: deve restituire mora moltiplicata per zero e rendere il libro nuovamente disponibile"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo B", "Autore A"),
        true,
        "Nuovo prestito dopo restituzione: il libro restituito deve tornare disponibile"
    );

    assertEquals(
        () -> biblioteca.restituisciLibro("Titolo B", "Autore A", 4),
        8.0f,
        "Restituzione con ritardo: deve calcolare correttamente la mora come prodotto tra mora giornaliera e giorni di ritardo"
    );

    assertEquals(
        () -> biblioteca.restituisciLibro("Titolo A", "Autore A", 2),
        4.0f,
        "Restituzione con copie multiple: deve riuscire se almeno una copia risulta in prestito, aggiornando la disponibilità di una copia"
    );

    assertEquals(
        () -> biblioteca.prestaLibro("Titolo A", "Autore A"),
        true,
        "Prestito dopo una restituzione parziale tra copie: deve riuscire se una copia è stata resa nuovamente disponibile"
    );

    runAndPrintAll();
  }


  @SuppressWarnings("unused")
  private static void assertNotThorws(Runnable function, String description){
    assertNotThorws(function, description, new String[0]);
  }

  private static void assertNotThorws(Runnable function, String description, String[] input){
    testList.add(new Tester(description, function, input));
  }

  @SuppressWarnings("unused")
  private static void assertThrows(Runnable function, Exception expectedException, String description){
    testList.add(new Tester(description, function, expectedException));
  }

  @SuppressWarnings("unused")
  private static void assertArrayEquals(java.util.function.Supplier<Object[]> function, Object[] output, String description){
    assertArrayEquals(function, output, description, new String[0]);
  }

  private static void assertArrayEquals(java.util.function.Supplier<Object[]> function, Object[] output, String description, String[] input){
    testList.add(new Tester(description, function, input, output));
  }

  @SuppressWarnings("unused")
  private static void assertEquals(java.util.function.Supplier<Object> function, Object output, String description){
    assertEquals(function, output, description, new String[0]);
  }

  private static void assertEquals(java.util.function.Supplier<Object> function, Object output, String description, String[] input){
    testList.add(new Tester(description, function, input, output));
  }

  private static void runAndPrintAll(){
    Tester.printResults(testList.stream().map(x -> x.run()).toList());
  }

  private static java.util.List<Tester> testList = new java.util.ArrayList<>();

  private final String description;
  private final java.util.function.Supplier<Object> function;
  private final java.util.function.Supplier<Object[]> functionArray;
  private final String[] input;
  private final Object output;
  private final Object[] outputArray;

  public Tester(String description, java.util.function.Supplier<Object[]> function, String[] input, Object[] output) {
    this.description = description;
    this.functionArray = function;
    this.function = null;
    this.output = null;
    this.input = input;
    this.outputArray = output;
  }

  public Tester(String description, java.util.function.Supplier<Object> function, String[] input, Object output) {
    this.description = description;
    this.function = function;
    this.input = input;
    this.output = output;
    this.outputArray = null;
    this.functionArray = null;
  }

  public Tester(String description, Runnable function, String[] input) {
    this(description, () -> { function.run(); return 0; }, input, 0);
  }

  public Tester(String description, Runnable function, Exception expectedException) {
    this.description = description;
    this.function = () -> { function.run(); return 0; };
    this.input = new String[0];
    this.output = expectedException;
    this.outputArray = null;
    this.functionArray = null;
  }

  public TestResult run(){
    return function == null ? runArray() : runSingle();
  }

  public TestResult runArray() {
    try {
      Object[] out = functionArray.get();
      return outputArray == out || java.util.Arrays.deepEquals(out, outputArray) ? success() : failure(formatCall() + formatArray(out) + " != " + formatArray(outputArray));
    } catch (Exception e) {
      return error(e);
    }
  }

  public String formatArray(Object[] a){
    return "[" + String.join(", ", java.util.Arrays.asList(a).stream().map(x -> x.toString()).toList()) + "]";
  }

  public TestResult runSingle() {
    try {
      Object out = function.get();
      if (isExpectingException()) {
        return failure(description + ": Nessuna eccezione lanciata");
      }
      return output == out || java.util.Objects.equals(output, out) ? success() : failure(formatCall() + out + " != " + output);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  private TestResult handleException(Exception e){
    if (!(output instanceof Throwable t)) return error(e);
    Class<?> expected = t.getClass();
    return expected.isInstance(e)
        ? success()
        : failure(description + ": Eccezione diversa: attesa "
        + expected.getName() + " ma ottenuta " + e.getClass().getName()
        + " (" + e.getMessage() + ")");
  }

  private boolean isExpectingException() {
    return (output instanceof Throwable)
        || (output instanceof Class<?> c && Throwable.class.isAssignableFrom(c));
  }

  private String formatCall() {
    StringBuilder sb = new StringBuilder(description).append(": ");
    for (int i = 0; i < input.length; i++) {
      sb.append("[").append(input[i]).append("]");
      sb.append(i + 1 == input.length ? " => " : " ");
    }
    return sb.toString();
  }

  private TestResult success() {
    return new TestResult(TestResultType.SUCCESS, null);
  }

  private TestResult failure(String msg) {
    return new TestResult(TestResultType.FAILURE, msg);
  }

  private TestResult error(Exception e) {
    return new TestResult(TestResultType.ERROR, description + ": [" + e.getClass().getName() + "] " + e.getMessage());
  }

  public static void printResults(java.util.List<TestResult> results){
    int tot = results.size();
    int successes = (int)results.stream().filter(x -> x.type() == TestResultType.SUCCESS).count();
    int failures = (int)results.stream().filter(x -> x.type() == TestResultType.FAILURE).count();
    int errors = (int)results.stream().filter(x -> x.type() == TestResultType.ERROR).count();
    System.out.println("Test superati [" + successes + "/" + tot + "]");
    if(failures > 0){
      System.out.println("Fallimenti: " + failures);
      for(TestResult tr : results){
        if(tr.type() == TestResultType.FAILURE)
          System.out.println("- " + tr.message());
      }
    }
    if(errors > 0){
      System.out.println("Errori: " + errors);
      for(TestResult tr : results){
        if(tr.type() == TestResultType.ERROR)
          System.out.println("- " + tr.message());
      }
    }
  }

  private record TestResult(TestResultType type, String message) { }

  private enum TestResultType {
    SUCCESS,
    FAILURE,
    ERROR
  }
}


