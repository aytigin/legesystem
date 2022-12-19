public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {
  @Override
  public void leggTil(T x) {
    Node n = new Node(x);

    Node foerMeg = start.neste;
    while (foerMeg != slutt) { //fortsetter helt til sluttnoden

      if (n.objekt.compareTo(foerMeg.objekt) < 0)
        break;

      foerMeg = foerMeg.neste; //hopper til neste node
    }

    n.neste = foerMeg;
    n.forrige = foerMeg.forrige;

    foerMeg.forrige.neste = n;
    foerMeg.forrige = n;

    stoerrelse++;
  }

  @Override
  public T fjern(){
    if (stoerrelse == 0) {
      throw new TomListe();
    }

    Node n = slutt.forrige; //det siste elementet siden det er størst

    n.forrige.neste = slutt; //fjerner det
    slutt.forrige = n.forrige;

    stoerrelse--;

    return n.objekt;
  }

  @Override
  public void sett(int pos, T x) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void leggTil(int pos, T x) {
    throw new UnsupportedOperationException();
  }
}
