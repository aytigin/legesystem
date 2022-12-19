public abstract class Resept {

  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected Pasient p;
  protected int reit;
  protected static int teller = 0;
  protected int id;
  protected double beloep;

  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.p = p;
    this.reit = reit;
    id = teller;
    teller++;
    beloep = legemiddel.hentPris();


  }

  public int hentId(){
    return id;
  }

  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  public Lege hentLege(){
    return utskrivendeLege;
  }

  public Pasient hentPasient(){
    return p;
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){
    if(reit == 0) {
      return false;
    } else{
      reit = reit - 1; //?? bruk resepten
      return true;
    }
  }
  abstract public String Farge();
    //hent reseptens farge (hvit eller blå)

  abstract public double prisAaBetale();
    //hent prisen pasienten skal betale

  @Override
  public String toString(){
    return id + ": (" + legemiddel + " reit: " + reit + ")";
    //+ ". Legemiddel: " + legemiddel.hentNavn() + ". Utskrivende lege: " + utskrivendeLege.hentLegenavn() + ". Pasient: " + p + ". Reit: " + reit
  }
}
