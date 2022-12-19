public class BlaaResept extends Resept {
  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, reit);
    super.beloep = super.beloep * 0.25;
  }
  public double prisAaBetale(){
    return super.beloep;
  }
  public String Farge(){
    return "Blaa resept";
  }

}
