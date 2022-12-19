public abstract class Legemiddel {
  protected String navn;
  protected double pris;
  protected double virkestoff;
  protected static int teller = 0;
  protected int id = 0;

  public Legemiddel(String navn, double pris, double virkestoff) {
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    id = teller;
    teller++;
  }

  public int hentId() {
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public double hentPris() {
    return pris;
  }

  public double hentVirkestoff() {
    return virkestoff;
  }

  public void settNyPris(double nyPris) {
    pris = nyPris;
  }

  @Override
  public String toString(){
    return id + ": " + navn;
    //". Pris: " + pris + ". Virkestoff: " + virkestoff
  }
}
