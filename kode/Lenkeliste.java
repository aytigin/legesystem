import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> {
  protected Node start, slutt;
  protected int stoerrelse = 0;

  protected class Node {
    T objekt;
    Node neste;
    Node forrige;

    Node(T obj) {
      objekt = obj;
    }
  }

  public Lenkeliste(){
    start = new Node(null);
    slutt = new Node(null);
    start.neste = slutt;
    slutt.forrige = start;
  }

  @Override
  public int stoerrelse(){
    return stoerrelse;
  }

  @Override
  public void leggTil(int pos, T x){
    if (pos < 0 || pos > stoerrelse){ //Kaster unntak ved ugyldig indeksnummer
      throw new UgyldigListeIndeks(pos);
    }

    Node n = new Node(x); //oppretter ny node med gitt innhold

    Node a = start.neste;
    for (int i = 0; i < pos; i++){ //finner elementet i gitt posisjon
      a = a.neste;
    }

    n.neste = a; // setter inn noden n mellom a og noden som kom før a
    n.forrige = a.forrige;

    a.forrige.neste = n;
    a.forrige = n;

    stoerrelse++; //øker størrelsen
  }

  @Override
  public void leggTil(T x){ //setter inn elementet på slutten av listen
    Node n = new Node(x);

    n.neste = slutt;
    n.forrige = slutt.forrige;

    slutt.forrige.neste = n;
    slutt.forrige = n;

    stoerrelse++;
  }

  @Override
  public void sett(int pos, T x){
    if (pos < 0 || pos >= stoerrelse){
      throw new UgyldigListeIndeks(pos);
    }

    Node n = new Node(x);

    Node a = start.neste;
    for (int i = 0; i < pos; i++){
      a = a.neste; //finner elementet i gitt posisjon
    }

    a.objekt = n.objekt; //bytter innholdet til noden i gitt posisjon med
                         //gitt innhold

  }


  @Override
  public T hent(int pos){
    if (pos < 0 || pos >= stoerrelse){
      throw new UgyldigListeIndeks(pos);
    }
    Node n = start.neste;

    for (int i = 0; i < pos; i++){
      n = n.neste; //finner noden i gitt posisjon
    }

    return n.objekt; //returnerer innholdet
  }

  @Override
  public T fjern(int pos){
    if (pos < 0 || pos >= stoerrelse){
      throw new UgyldigListeIndeks(pos);
    }
    Node n = start.neste;

    for (int i = 0; i < pos; i++){
      n = n.neste; //finner elementet i gitt posisjon
    }

    n.forrige.neste = n.neste; //fjerner elementet
    n.neste.forrige = n.forrige;

    stoerrelse--; //minsker størrelsen

    return n.objekt;
  }

  @Override
  public T fjern(){
    if (stoerrelse == 0) { //Kaster unntak dersom listen er tom
      throw new TomListe();
    }

    Node n = start.neste; //det første elementet

    start.neste = n.neste;
    n.neste.forrige  = start;

    stoerrelse--;

    return n.objekt;
  }

  @Override
  public String toString() {
    if (stoerrelse == 0)
      return "[]";

    String s = "[";
    for (Node n = start.neste; n != slutt; n = n.neste) {
      s += n.objekt + ", ";
    }
    return s.substring(0, s.length() - 2) + "]";
  }


  private class LenkelisteIterator implements Iterator<T> {

    private Node n = start.neste;

    public boolean hasNext() {
      return n != slutt;
    }

    public T next() {
      T verdi = n.objekt;
      n = n.neste;
      return verdi;
    }
  }

  public Iterator<T> iterator() {
    return new LenkelisteIterator();
  }


}
