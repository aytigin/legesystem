public class Pasient {
  private String navn;
  private String foedselsnummer;
  private static int teller = 0;
  private int id;
  private Stabel<Resept> reseptliste = new Stabel<Resept>();

  public Pasient(String navn, String foedselsnummer){
    this.navn = navn;
    this.foedselsnummer = foedselsnummer;
    id = teller;
    teller++;
  }

  public String hentNavn() {
    return navn;
  }

  public void leggTilResept(Resept resept) {
    reseptliste.leggPaa(resept);
  }

  public Lenkeliste hentResepter(){
    return reseptliste;
  }

  @Override
  public String toString(){
    return id + ": " + navn + " (fødselsnummer: " + foedselsnummer + ")";
  }
}
