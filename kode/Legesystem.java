import java.util.Scanner;

public class Legesystem {
  public static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
  public static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
  public static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
  public static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    System.out.println("\nVelkommen til Legesystemet!\n\nVelg et nummer:\n0) Avslutt programmet\n1) Vis oversikt\n2) Legg til element i systemet\n3) Bruk resept\n4) Skriv ut statistikk");
    String input1 = reader.nextLine();
    while(!"0".equals(input1)) { //fortsetter med mindre brukeren taster inn 0
      if (input1.equals("1")) {
        oversikt();
      } else if (input1.equals("2")) {
        System.out.println("1) lege, 2) pasient, 3) resept eller 4) legemiddel?: ");
        String input2 = reader.nextLine();
        if (input2.equals("1")) {
          System.out.println("Navn: ");     //lege
          String navn = reader.nextLine();

          System.out.println("Er legen en fastlege? Svar 1) ja eller 2) nei: ");
          String jaNei = reader.nextLine();

          if (jaNei.equals("1")) {
            System.out.println("Avtalenummer: ");
            int avtaleNr = Integer.parseInt(reader.nextLine());
            Fastlege fastlege = new Fastlege(navn, avtaleNr);
            leger.leggTil(fastlege);
          } else {
            Lege lege = new Lege(navn);
            leger.leggTil(lege);
          }

        } else if (input2.equals("2")) {
          System.out.println("Navn: ");    //pasient
          String navn = reader.nextLine();
          System.out.println("Fødelsnummer:");
          String foedselsNr = reader.nextLine();

          Pasient pasient = new Pasient(navn, foedselsNr);
          pasienter.leggTil(pasient);

        } else if (input2.equals("3")) {          //resept
          if (leger.stoerrelse() == 0 || pasienter.stoerrelse() == 0 || legemidler.stoerrelse() == 0){
            System.out.println("\nKan ikke legge til resept uten gyldig lege, pasient eller legemiddel");
          } else {
            System.out.println("Resept-type? 1) Blå eller 2) hvit: ");
            String farge = reader.nextLine();

            System.out.println(legemidler);
            System.out.println("Velg legemiddel: ");
            int legemiddelNr = Integer.parseInt(reader.nextLine());
            Legemiddel legemiddel = legemidler.hent(legemiddelNr);
            System.out.println(leger);
            System.out.println("Velg lege: ");
            int legeNr = Integer.parseInt(reader.nextLine());
            Lege lege = leger.hent(legeNr);
            System.out.println(pasienter);
            System.out.println("Velg pasient: ");
            int pasientNr = Integer.parseInt(reader.nextLine());
            Pasient pasient = pasienter.hent(pasientNr);
            System.out.println("Angi reit: ");
            int reit = Integer.parseInt(reader.nextLine());

            if (farge.equals("1")) {
              BlaaResept blaaresept = new BlaaResept(legemiddel, lege, pasient, reit);
              resepter.leggTil(blaaresept);
            } else if (farge.equals("2")) {
              System.out.println("1) Militærresept eller 2) Presept?");
              String mP = reader.nextLine();
              if (mP.equals("1")) {
                MilitaerResept mResept = new MilitaerResept(legemiddel, lege, pasient, reit);
                resepter.leggTil(mResept);
              } else if (mP.equals("2")) {
                PResept pResept = new PResept(legemiddel, lege, pasient, reit);
                resepter.leggTil(pResept);
              }
            }
          }

        } else if (input2.equals("4")) {
          System.out.println("Navnet til legemiddelet: ");   //legemiddel
          String legemiddelNavn = reader.nextLine();

          System.out.println("Pris: ");
          double legemiddelPris = Double.parseDouble(reader.nextLine());

          System.out.println("Virkestoff i mg: ");
          double legeMVirkestoff = Double.parseDouble(reader.nextLine());

          System.out.println("1) Narkotisk type, 2) Vanedannende type eller 3) Vanlig type?");
          String legemiddelType = reader.nextLine();

          if (legemiddelType.equals("3")) {
            LegemiddelC legemiddelC = new LegemiddelC(legemiddelNavn, legemiddelPris, legeMVirkestoff);
            legemidler.leggTil(legemiddelC);
          } else if (legemiddelType.equals("2")) {
            System.out.println("Vanedannende styrke: ");
            int vStyrke = Integer.parseInt(reader.nextLine());
            LegemiddelB legemiddelB = new LegemiddelB(legemiddelNavn, legemiddelPris, legeMVirkestoff, vStyrke);
            legemidler.leggTil(legemiddelB);
          } else if (legemiddelType.equals("1")) {
            System.out.println("Narkotisk styrke: ");
            int nStyrke = Integer.parseInt(reader.nextLine());
            LegemiddelA legemiddelA = new LegemiddelA(legemiddelNavn, legemiddelPris, legeMVirkestoff, nStyrke);
            legemidler.leggTil(legemiddelA);
          }
        }

      } else if (input1.equals("3")) { //bruk resept
        int gyldigeRes = 0;
        for (Resept res: resepter) {
          if (res.hentReit() > 0 ) {
            gyldigeRes++;
          }
        }
        if (resepter.stoerrelse() == 0 || gyldigeRes == 0){
          System.out.println("\nTom for gyldige resepter");
        } else {
          System.out.println("Hvilken pasient vil du se resepter for?\n" + pasienter);
          int pasIndex = Integer.parseInt(reader.nextLine());
          System.out.println("Valgt pasient: " + pasienter.hent(pasIndex));

          System.out.println("Hvilken resept vil du bruke?\n" + resepter);
          int resIndex = Integer.parseInt(reader.nextLine());
          Resept res = resepter.hent(resIndex);
          if (res.bruk() == false) {
            System.out.println("Kunne ikke bruke resept paa " + res.hentLegemiddel().hentNavn() + "(ingen gjenvaerende reit).");
          } else{
            System.out.println("Brukte resept på: " + res.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit: " + res.hentReit());
          }
        }


      } else if(input1.equals("4")) {
        skrivUtStats();
      }

      System.out.println("\n\nVelg et nummer:\n0) Avslutt programmet\n1) Vis oversikt\n2) Legg til element i systemet\n3) Bruk resept\n4) Skriv ut statistikk");
      input1 = reader.nextLine();
    }
    reader.close();
  }

  private static void oversikt() {
    System.out.println("\nPasienter: " + pasienter + "\nLeger: " + leger + "\nResepter: " + resepter + "\nLegemidler: " + legemidler);
  }

  public static void skrivUtStats(){
    int vTeller = 0; //antall vanedannende resepter
    int mTeller = 0; //antall militærresepter med vanedannende legemidler
    int nTeller = 0; //antall narkotiske resepter
    int lTeller = 0; //antall leger som har skrevet ut narkotiske legemidler
    int pTeller = 0; //antall pasienter med narkotiske legemidler
    double narkResPerlege = 0;
    double narkResPerPasient = 0;

    SortertLenkeliste<Lege> nLeger = new SortertLenkeliste<Lege>(); //legg til leger i alfabetisk rekkefølge
    SortertLenkeliste<String> nLegeNavn = new SortertLenkeliste<String>();
    Lenkeliste<String> nPasienter = new Lenkeliste<String>();

    for (Resept res: resepter) {
      if (res.hentLegemiddel() instanceof LegemiddelB) {
        vTeller++;
      }
    }

    for (Resept res: resepter) {
      if (res instanceof MilitaerResept) {
        if (res.hentLegemiddel() instanceof LegemiddelB) {
          mTeller++;
        }
      }
    }


    for (Resept res: resepter) {
      if (res.hentLegemiddel() instanceof LegemiddelA) {
        nTeller++;
      }
    }

    for (Resept res: resepter) {
      if (res.hentLegemiddel() instanceof LegemiddelA) {
        nLeger.leggTil(res.hentLege());
        lTeller++;
      }
    }

    for (Resept res: resepter) {
      if (res.hentLegemiddel() instanceof LegemiddelA) {
        if (res.hentReit() > 0) { //sjekker om resepten er gyldig
          nPasienter.leggTil(res.hentPasient().hentNavn());
          pTeller++;
        }
      }
    }

    if (nTeller == 0) {
      narkResPerlege = 0;
    } else {
      narkResPerlege = lTeller/ (double) nTeller;
      narkResPerPasient = pTeller/nTeller;
    }

    for (Lege l: nLeger) {
      nLegeNavn.leggTil(l.hentLegenavn());
    }

    System.out.println("\nTotalt antall utskrevne resepter på vanedannende legemidler: " + vTeller);
    System.out.println("Antall vanedannende resepter utskrevne til militære: " + mTeller);
    System.out.println("\n\nLeger som har skrevet ut resept på narkotiske legemidler: " + nLegeNavn + "\nAntall slike resepter per lege:" + narkResPerlege);
    System.out.println("\nPasienter som har minst en gyldig resept på narkotiske legemidler: " + nPasienter + "\nAntall per pasient: " + narkResPerPasient);

  }

}
