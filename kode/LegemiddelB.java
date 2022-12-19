public class LegemiddelB extends Legemiddel {

  protected int vanedannendeStyrke;

  public LegemiddelB(String navn, double pris, double virkestoff, int vanedannendeStyrke){
    super(navn, pris, virkestoff);
    this.vanedannendeStyrke = vanedannendeStyrke;
  }

  public int hentVanedannendeStyrke() {
    return vanedannendeStyrke;
  }

  @Override
  public String toString() {
    return super.toString() + " Vanedannende: " + vanedannendeStyrke;
  }
}
