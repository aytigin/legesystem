public class MilitaerResept extends HvitResept{
  public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, reit);
    super.beloep = 0;
  }
  public double prisAaBetale(){
    return super.beloep;
  }

  public String Farge(){
    return "Hvit resept";
  }
}
