public class Lege implements Comparable<Lege> {
  protected String navn;
  protected static int teller = 0;
  protected int id;
  protected Lenkeliste<Resept> reseptliste = new Lenkeliste<Resept>();

  public Lege(String navn){
    this.navn = navn;
    id = teller;
    teller++;
  }

  public void leggTilResept(Resept r){
    reseptliste.leggTil(r);
  }

  public Lenkeliste hentResepter(){
    return reseptliste;
  }

  public String hentLegenavn(){
    return navn;
  }

  public int hentId(){
    return id;
  }

  @Override
  public String toString(){
    return id + ": " + navn;
    //+ ". Navn: " + navn
  }

  @Override
  public int compareTo(Lege lege){
    String foersteLege = this.hentLegenavn();
    String andreLege = lege.hentLegenavn();

    return foersteLege.compareTo(andreLege);
  }

}
