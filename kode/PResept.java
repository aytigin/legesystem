public class PResept extends HvitResept{
  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, reit);
    super.reit = 3; //utskrives med tre reit
    if (super.beloep < 116){
      super.beloep = 0;
    } else  {
      super.beloep = super.beloep - 116;
    }
  }
  public double prisAaBetale(){
    return super.beloep;
  }

  public String Farge(){
    return "Hvit resept";
  }

}
