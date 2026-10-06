package ST0851_Programmazione.SimulazioneEsami.Visite_Mediche;

/**
 * @author Roberto Venturi aka Roven60
 */
public class Visita {
  String nome;
  String cognome;

  /**
   *
   * @param nome    nome del paziente richiedente visita
   * @param cognome conome del paziente richiedente visita
   */
  public Visita(String nome, String cognome) {
    if (nome == null || nome.length() < 2) {
      throw new IllegalArgumentException("Nome nullo o troppo corto");
    }
    if (cognome == null || cognome.length() < 2) {
      throw new IllegalArgumentException("Cognome nullo o troppo corto");
    }
    this.nome =  toTitleCase(nome);
    this.cognome = toTitleCase(cognome);
  }

  public String getNome() {
    return nome;
  }

  public String getCognome() {
    return cognome;
  }


   /**
   * @param name  a String containing a name or a surname or both
   * @return      the capitalized version of name
   */
  public static String toTitleCase(String name) {
    if (name == null) {
      return "";
    }
    name = name.trim();
    if (name.isEmpty()) {
      return "";
    }
    StringBuilder res = new StringBuilder();
    res.append(Character.toUpperCase(name.charAt(0)));
    name = name.substring(1).toLowerCase().trim();
    int idx = name.indexOf(" ");
    while (idx > 0) {
      res.append(name, 0, idx + 1);
      name = name.substring(idx + 1).trim();
      res.append(Character.toUpperCase(name.charAt(0)));
      name = name.substring(1).trim();
      idx = name.indexOf(" ");
    }
    res.append(name);
    return res.toString();
  }

}
