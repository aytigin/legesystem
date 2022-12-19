public class Stabel<T> extends Lenkeliste<T> {
  public Stabel(){
    super();
  }

  public void leggPaa(T x){
    Node n = new Node(x); //ny node med gitt innhold

    n.neste = slutt; //legger inn noden foran slutt-noden
    n.forrige = slutt.forrige;

    slutt.forrige.neste = n;
    slutt.forrige = n;

    stoerrelse++;
  }

  public T taAv() {
    if (stoerrelse == 0) { //kaster unntak ved tom liste
      throw new TomListe();
    }

    Node n = slutt.forrige; //det siste elementet

    n.forrige.neste = slutt; //fjerner det siste elementet
    n.neste.forrige  = n.forrige;

    stoerrelse--; //minsker størrelsen

    return n.objekt; //returnerer innholdet til slettet element
  }
}
