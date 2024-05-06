import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.io.FileInputStream;

/**
 *
 * @author Osm70, juxtaposedMantle
 */
public class RSBURBS {
    
    public static int turns;
    public static int gt;
    

    public static void quit() {
        if (autoQuit == true) { System.exit(0); }
        System.out.println("\nWhat would you like to do?");
        System.out.println("[q] -> Quit");
        System.out.println("[r] -> Restart");
        if (debug == true) {
            System.out.println("[x] - Examine Variables [DEBUG]");
        }
        
        String Keyinput="";
        if (autoRetry == false) {
            Keyinput = new String(sc.nextLine());
        } else {
            Keyinput = "r";
        }
        switch (Keyinput) {
            case "Quit": case "quit": case "q": case "Q": // Quit
                System.exit(0);
                break;
                
            case "Restart": case "restart": case "r": case "R": case "": // Restart
                turns = 0; gt = 0;
                sunk = 0; lostBreak = 0; minionCount = 0; locks = 0; keys = 0;
                ecto = 0; frog=0; scratch=0;  players=0; match=0; time=0; space=0;
                p.clear();
                game();
                break;
                
            case "Examine": case "examine": case "x": case "X": // Examine
                System.out.println("Total Turns: "+turns);
                System.out.println("gt: "+ gt);
                System.out.println("Standard Session Variables");
                System.out.println("  Ectobiology Performed: "+ecto+" | Frog Bred: "+frog+" | Scratched: "+scratch);
                System.out.println("Solo Stats:");
                System.out.println("  Sunk Planets: "+ sunk + " | Planets Lost in Break: " + lostBreak +" | Minions: " +minionCount+ 
                "\n  Locks: "+locks+" | Keys: "+keys);
                System.out.println("Players"+ p);
                quit();
                System.exit(0);
                
            default: // Error catch
                System.out.println("Invalid input, [" + Keyinput + "] is not a valid option.");
                quit();
                System.exit(0);
                break;
        }
    }
    
    // Generic Wait function
    public static void sysWait(String unitOfTime, int time) {
        if ((debug==true) || (speedUp==true)) {
            try {
                TimeUnit.MILLISECONDS.sleep(100); 
            } catch (InterruptedException ex) { 
                Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        switch (unitOfTime) {
            case "ms":
                try {
                    TimeUnit.MILLISECONDS.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "s":
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default: // no declared time unit
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("[WARNING]: Incorrect 'unitOfTime' declaration. Using seconds as default.");
                break;
        }   
    }
      
    public static int rng(int min, int max) { 
        if (debug == false) {
            int r = ThreadLocalRandom.current().nextInt(min, max + 1);
            return r;
        } else { 
            System.out.println("[DEBUG]: expected range: ("+min+", "+max+")\n");
            return sc.nextInt();
        }
    }

    // Game Over function for Dead Sessions
    public static void soloGameOver(String deathCause) {
        if (debug == true) {System.out.println("\n\n[DEBUG]: Debug Mode was enabled in this attempt.\n");}
        System.out.println("You lost. GAME OVER.\n");
        System.out.println("Stats:");
        System.out.println("  Lock/Key Ratio:    "+locks+"/"+keys); 
        if (deathCause != "Not Enough Keys.") {
            System.out.println("  Planets Sunk:      " + sunk + "/"+(15-lostBreak));
            if (lostBreak > 0) {
                System.out.println("  Lost in Break:     " + lostBreak + "/14");
            }
            System.out.println("  Minions Collected: " + minionCount + "/14");
        }
        System.out.println("  Cause of Failure:  " + deathCause);
        sysWait("ms", 50);
        quit();
    }
    
    public static void soloWin() {
        if (debug == true) {System.out.println("\n\n[DEBUG]: Debug Mode was enabled in this attempt.\n");}
        System.out.println("You won!\n");
        sysWait("ms", 50);
        System.out.println("Stats:");
        sysWait("ms", 50);
        System.out.println("  Lock/Key Ratio:    "+locks+"/"+keys); 
        sysWait("ms", 50);
        System.out.println("  Planets Sunk:      " + sunk + "/"+(15-lostBreak));
        sysWait("ms", 50);
        System.out.println("  Lost in Break:     " + lostBreak + "/14");
        sysWait("ms", 50);
        System.out.println("  Minions Collected: " + minionCount + "/14");
        sysWait("s", 1);
        quit(); 
    }
    
    // Game over function for Multiplayer Sessions
    public static void gameOver(String deathCause) {

        String frogB = "No";
        String ectoB = "No";
        String timeB = "No";
        String spaceB = "No";
        if (frog == 1)  { frogB = "Yes"; }
        if (ecto == 1)  { ectoB = "Yes"; }
        if (time == 1)  { timeB = "Yes"; }
        if (space == 1) { spaceB = "Yes"; }
        if (debug == true) {System.out.println("\n\n[DEBUG]: Debug Mode was enabled in this attempt.\n");}
        
        System.out.println("You lost. GAME OVER.\n");
        
        System.out.println("Stats:");
        System.out.println("  Players:     " + players);
        System.out.println("  Spires:      " + spires);
        System.out.println("  Frog Bred?   " + frogB);
        System.out.println("  Ectobiology? " + ectoB);
        System.out.println("  Required Aspects:");
        System.out.println("    Time:  " + timeB+ ",  Space: " + spaceB);
        System.out.println("  Cause of Failure: "+ deathCause);
        
        System.out.println("\nLiving Players:");
        for(Player id:p) {
            System.out.println("  " + id.detailString());
        }
        quit();
    }

    public static int spires=0;
    
    public static void spire() {
        System.out.println("Generating number of protoyping spires...");
        switch (rng(1,3)) {
            case 1:
                spires = 4;
                break;
            case 2:
                spires = 8;
                break;
            case 3:
                spires = 12;
                break;
        }
        System.out.println("Spires: "+spires);
        if (players!=spires) {
            System.out.println("Players and spires do not match. \nYou are in a doomed timeline.");
            gameOver("Spire Inconsistency.");
        }
    }
    
    public static int sunk = 0;
    public static int lostBreak = 0;
    public static int minionCount = 0;
    public static int locks = 0;
    public static int keys = 0;
    public static void single() {
        System.out.print("You are the ");
        Player p = new Player(rng(100,150), rng(1,14), rng(1,12), rng(1,94), 8);
        System.out.print(p.toString());
        sysWait("s", 1);
        System.out.println("\n\nYou are about to enter the game...");
        sysWait("s", 1);
        System.out.println("You took your planet with you.");
        sysWait("s", 1);
        System.out.println("Locks and keys spawned.");
        locks = rng(1,100);
        keys = rng(1,100);
        sysWait("s", 1);
        System.out.println("Locks: "+locks);
        System.out.println("Keys: "+keys);
        sysWait("s", 1);
        if (keys<locks) {
            System.out.println("You don't have enough keys");
            soloGameOver("Not Enough Keys.");
        }
        System.out.println("You unlocked all your locks");
        sysWait("s", 1);
        System.out.println("The game is ready to start.");
        int[] planets = new int[15];
        for (int i = 0; i < 15; i++) {
            planets[i]=1;
        }
        System.out.println("");
        System.out.println("BREAK.");
        for (int i = 0; i < 15; i++) {
            sysWait("s", 1);
            if (i==7) {
                System.out.println("Planet  8 cannot be destroyed during the break.");

                continue;
            }
            int l = rng(1,100);
            if (l<6) {
                if (i < 9) {
                    System.out.println("Planet  "+(i+1)+" was pocketed.");
                } else {
                    System.out.println("Planet "+(i+1)+" was pocketed.");
                }
                planets[i]=0;
                lostBreak += 1;
            }
            else {
                if (i < 9) {
                    System.out.println("Planet  "+(i+1)+" survived.");
                } else {
                    System.out.println("Planet "+(i+1)+" survived.");
                }
            }
        }
        System.out.println("The break ended.");
        sysWait("s", 1);
        System.out.println("You are now ready to start destroying planets.");
        int left=80;
        for (int i = 0; i < 15; i++) {
            sysWait("s", 1);
            System.out.println("");
            System.out.println("");
            if (i==7) {
                System.out.println("Skipping planet 8.");
                continue;
            }
            if (planets[i]!=1) {
                System.out.println("Planet "+(i+1)+" was destroyed during the break.");
                left-=5;
                minionCount += 1;
                continue;
            }
            int time=rng(5,90)-i;

            System.out.println("Trying to pocket planet "+(i+1)+"...");
            sysWait("s", 1);
            System.out.println("Timer: "+left);
            System.out.println("Time it took: "+time);
            if (time>left) {
                System.out.println("Time up. You blew up.");
                soloGameOver("Cue Bomb exploded.");
            }
            System.out.println("You pocketed the planet.");
            left-=5;
            sunk+=1;
            minionCount += 1;
        }
        System.out.println("Trying to pocket planet 8...");
        time=rng(5,90);
        sysWait("s", 1);
        System.out.println("Timer: "+left);
        System.out.println("Time it took: "+time);
        if (time>left) {
            System.out.println("Time up. You blew up.");
            soloGameOver("Cue Bomb exploded.");
        }
        System.out.println("You pocket the planet.");        
        System.out.println("");
        System.out.println("");
        sysWait("s", 1);
        System.out.println("You destroyed all planets!");
        sysWait("s", 1);
        System.out.println("Time to fight your denizen.");
        int eHP = rng(200,500);
        System.out.println(
            " _______  _______  ______    ___  _______  _______  __  " + "\n" +
            "|       ||       ||    _ |  |   ||       ||       ||  | " + "\n" +
            "|  _____||_     _||   |_||_ |   ||    ___||    ___||  | " + "\n" +
            "|_____  |  |   |  |    __  ||   ||    ___||    ___||__| " + "\n" +
            "|_______|  |___|  |___|  |_||___||___|    |_______||__| " + "\n"
        );    
        do {
            sysWait("s", 1);
            int dmg = rng(1,10);
            if (p.godTier==1) {
                dmg=dmg*2;
            }
            eHP-=dmg;
            System.out.println("The player attacked. Enemy HP: "+eHP);          
            dmg=rng(1,5);

            p.HP-=dmg;
            System.out.println("The player got hit. HP: "+p.HP);
            if (p.HP<1) {
                System.out.println("The player died.");
                if (p.godTier==1) {
                    System.out.println("The player is God Tier");
                    if (rng(1,100)<33) {
                        System.out.println("The player got revived. Their HP went up to 50");
                        p.HP=100;
                    }
                    else {
                        System.out.println("The player could not revive.");
                        soloGameOver("Killed by Denizen.");
                    }

                } else {
                    System.out.println("The player is NOT God Tier");
                    if(rng(1,100)<10) {
                        System.out.println("The player was placed on their Quest Bed. The are God Tier now and their HP went up to 100");
                        p.HP=100;
                        p.godTier=1;
                    } else {
                        System.out.println("The player could not God Tier.");
                        soloGameOver("Killed by Yaldabaoth.");
                    }
                }
            }
        } while (eHP>1);
        System.out.println("You killed Yaldabaoth.");
        win();
    }

    public static void multiStart() {

        spire();
        CopyOnWriteArrayList<Player> lst = new CopyOnWriteArrayList<Player>();
        Player tmp;
        System.out.println("Generating players");
        sysWait("s", 1);
        for (int i = 1; i <= players; i++) {
            do {
                match=0;
                
                //               Health        Class      Aspect     Weapon     Denizen
                tmp = new Player(rng(100,150), rng(1,12), rng(1,12), rng(1,94), rng(1,12));
                for(Player id:lst) {
                    if (
                        (tmp.aspectID==id.aspectID) || 
                        (tmp.classID==id.classID) || 
                        (tmp.denizenID==id.denizenID)
                    ) { 
                        match = 1;
                    }
                }
            } while (match==1);
            lst.add(tmp);
        }
        p.addAll(lst);
        for(Player id:p) {
            System.out.println(id.detailString());
            sysWait("ms", 500);
        }
        sysWait("s", 1);
        System.out.println("Checking for time and space players...");
        for(Player id:p) {
            if (id.aspectID==2) {
                time++;
            }
            if (id.aspectID==1) {
                space++;
            }
        }
        if (time==0) {
            System.out.println("Time player not found");
            add();
        }
        if (space==0) {
            System.out.println("Space player not found");
            scratch();
        }
        System.out.println("Players of Time and Space found");
        if (rng(1,100)<80) {
            System.out.println("No one prototyped their Kernelsprite pre-entry");
            scratch();
        } else {
            System.out.println("Prototyping OK.");
            play();
        }

    }

    public static void play() {

        System.out.println("");
        System.out.println("");
        System.out.println("Starting");
        sysWait("s", 1);
        System.out.println("Generating number of turns until Reckoning...");
        turns=rng(50,200);
        sysWait("s", 1);
        System.out.println("The reckoning starts in "+turns+" turns.");
        sysWait("s", 1);
        System.out.println("Following all players");
        for(Player id:p) {
            System.out.println("| | | | | | ");
            System.out.println("| | | | | | ");
            System.out.println("Players alive: "+p.size());
            System.out.println("Now following the "+id.sburbClass+" of "+id.aspect);
            countdown(id);
        }
        System.out.println("The Reckoning has started.");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (p.size()==0) {
            System.out.println("But all players are dead.");
            gameOver("Everyone Died.");
        }
        System.out.println("The players are getting ready to fight the Black King");
        sysWait("s", 2);
        int eHP=500;
        eHP+=(players*200);

        System.out.println("Black King's HP: "+eHP);
        boss(eHP);
        System.out.println("You defeated the Black King!");
        System.out.println("Checking if end conditions are met...");
        sysWait("s", 1);
        if (ecto==0) {
            System.out.println("Ectobiology never happened.");
            scratch();
        }
        if (frog==0) {
            System.out.println("The frog was not bred.");
            System.out.println("Looking for a living Hero of Space...");
            sysWait("s", 1);
            space=0;
            for(Player id:p) {
                if (id.aspectID==1) {
                    space++;
                }
            }
            if (space==0) {
                System.out.println("Hero of Space not found.");
                scratch();
            } else {
                System.out.println("The Genesis Frog was bred.");
            }
        }
        System.out.println("Everything seems OK.");
        System.out.println("");
        System.out.println("The players are getting ready to open the final door.");
        sysWait("s", 1);
        if (rng(0,100)>2) {
            System.out.println("The players went through the door.");
            win();
        } else {
            System.out.println("An invader entered your session and attacked you.");
            sysWait("s", 1);
            switch(rng(1,3)) {
                case 1:
                    eHP=1000;
                    break;
                case 2:
                    eHP=2000;
                    break;
                case 3:
                    eHP=3000;
                    break;
            }
            System.out.println("Invader's HP: "+eHP);
            sysWait("s", 1);
            boss(eHP);
            sysWait("s", 1);
            System.out.println("You are attempting to escape your session...");
            if (rng(1,100)<16) {
                System.out.println("You escaped.");
                sysWait("s", 1);
                System.out.println("The players entered a new session.");
                turns = 0;
                ecto = 0;
                frog = 0;
                match = 0;
                time = 0;
                space = 0;
                scratch = 0;
                switch(rng(1,3)) {
                    case 1:
                        players=4;
                        break;
                    case 2:
                        players=8;
                        break;
                    case 3:
                        players=12;
                        break;
                }
                System.out.println("The new session has "+players+" new players.");
                multiStart();
            } else {
                System.out.println("You couldn't escape.");
                gameOver("An invander killed you.");
            }
        }
    }

    public static void win() {
        String frogB = "No";
        String ectoB = "No";
        String timeB = "No";
        String spaceB = "No";
        int trueGristTotal = 0;
        int prospitDreamers = 0;
        int derseDreamers = 0;
        if (frog == 1) {
            frogB = "Yes";
        }
        if (ecto == 1) {
            ectoB = "Yes";
        }
        if (time == 1) {
            timeB = "Yes";
        }
        if (space == 1) {
            spaceB = "Yes";
        }
        for(Player id:p) {
            trueGristTotal+=id.gristTotal;
        }
        if (debug == true) {
            System.out.println("[DEBUG]: Debug Mode was enabled in this attempt.\n");
        }
        System.out.println("YOU WON!");
        System.out.println("Stats:");
        System.out.println("  Players:     " + players);
        System.out.println("  Spires:      " + spires);
        System.out.println("  Frog Bred?   " + frogB);
        System.out.println("  Ectobiology? " + ectoB);
        System.out.println("  Grist Total: " + trueGristTotal);
        System.out.println("  Prospit: "+prospitDreamers+" Derse: "+derseDreamers);
        System.out.println("  Required Aspects:");
        System.out.println("    Time:  Yes,  Space: Yes");
        System.out.println("\nPlayers:");
        for(Player id:p) {
            System.out.println("  " + id.toString());
        }
        System.out.println("Press [ENTER] to exit.");
        sc.nextLine();
        System.exit(0);
    }

    public static void scratch() {
        System.out.println(
             "       . -- ~~~ -- ." + "\n" +
             "   .-~            /  ~-." + "\n" +
             "  /              /      \\" + "\n" +
             " /              /        \\" + "\n" +
             "|           ,-,/          |" + "\n" +
             "|          | o |          |" + "\n" +
             "|          /'-'           |" + "\n" +
             " \\        /               /" + "\n" +
             "  \\      /               /" + "\n" +
             "   `-.  /            .-'" + "\n" +
             "       ~- . ___ . -~"
        );
        
        System.out.println("The game is no longer winnable.");
        sysWait("s", 1);
        System.out.println("Attempting to scratch the session...");
        
        sysWait("s", 1);
        if (scratch!=0 || rng(1,2)==1) {
            System.out.println("You already are in a scratched session.");
            gameOver("Double scratch.");
        }
        if (rng(1,100)>30) {
            System.out.println("The scratch failed.");
            gameOver("You couldn't scratch.");
        }
        turns = 0;
        ecto = 0;
        frog = 0;
        players = 0;
        match = 0;
        time = 0;
        space = 0;
        p = new CopyOnWriteArrayList<Player>();
        scratch = 1;
        System.out.println("You scratched the session.");
        game();
    }

    public static void boss(int eHP) {
        System.out.println(
            " _______  _______  ______    ___  _______  _______  __  " + "\n" +
            "|       ||       ||    _ |  |   ||       ||       ||  | " + "\n" +
            "|  _____||_     _||   |_||_ |   ||    ___||    ___||  | " + "\n" +
            "|_____  |  |   |  |    __  ||   ||    ___||    ___||__| " + "\n" +
            "|_______|  |___|  |___|  |_||___||___|    |_______||__| " + "\n"
        );     
        int hp=eHP;
        int grist = eHP/3;
        do {
            sysWait("s", 1);
            int targets=p.size()-1;
            if (targets==0) {
                System.out.println("All players are dead.");
                gameOver("Everyone died.");
            }
            System.out.println("Players are attacking...");
            int hit = targets+gt;
            int m=rng(1,3);
            hp-=(hit*m);
            System.out.println("Boss HP: "+hp);
            if (hp<1) {
                System.out.println("The boss was defeated.");
                for (Player id:p) {
                    id.gristTotal += grist;
                }
                return;
            }
            System.out.println("The boss is attacking...");
            int id=rng(0,targets);
            System.out.println("Player [" +p.get(id)+"] got hit.");
            p.get(id).HP-=(rng(2,15));
            System.out.println("HP: "+p.get(id).HP);
            if (p.get(id).HP<1) {
                System.out.println("A Hero died.");
                if (p.get(id).godTier==1) {
                    System.out.println("The Hero is God Tier");
                    if (rng(1,100)<33) {
                        System.out.println("The Hero was revived. Their HP went up to 100");
                        p.get(id).HP=100;
                    } else {
                        System.out.println("The Hero could not revive.");
                        p.remove(p.get(id));
                        gt--;

                    }
                } else {
                    System.out.println("The Hero is NOT God Tier");
                    if(rng(1,100)<10) {
                        System.out.println("The Hero was placed on their Quest Bed. They are God Tier now and their HP went up to 100");
                        p.get(id).HP=100;
                        p.get(id).godTier=1;
                        gt++;
                    } else {
                        System.out.println("The Hero died a true death.");
                        p.remove(p.get(id));

                    }   
                }
            }
        } while (true);
    }

    public static int ecto=0;
    public static int frog=0;
    public static void countdown(Player a) {
        System.out.println(a.detailString());
        sysWait("s", 5);
        int eHP=0;
        System.out.println("");
        System.out.println("");
        sysWait("s", 2);
        int state=0;
        for (int i = turns; i > 0; i--) {
            sysWait("ms", 500);
            System.out.println("");
            System.out.println("Time left: " + i);
            System.out.println("");
            int s=rng(1,100);
            if (state==0) {
                if (s<5) {
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " did nothing.");
                }
                if (s>5 && s<15) {
                    if (ecto==0) {
                        System.out.println("The "+a.sburbClass+" of "+a.aspect + " entered the ectobiology lab.");
                        state=1;
                        continue;
                    } else {
                        System.out.println("The "+a.sburbClass+" of "+a.aspect + " twiddled their thumbs.");
                    }
                }
                    if (s>15 && s<25) {
                        if (frog==0 && a.aspectID==1) {
                            System.out.println("The "+a.sburbClass+" of Space started breeding frogs.");
                            state=2;
                            continue;
                        } else {
                        System.out.println("The "+a.sburbClass+" of "+a.aspect + " explored their dream moon.");
                    }
                }
                if (s>25 && s<100) {
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " entered a battle!");
                    state=3;
                    switch (rng(1,4)) {
                        case 1:
                            eHP = 10;
                            break;
                        case 2:
                            eHP = 20;
                            break;
                        case 3:
                            eHP = 30;
                            break;
                        case 4:
                            eHP = 50;
                            break;
                    }
                    System.out.println("Enemy HP: "+eHP);
                    System.out.println(a.sburbClass+" of "+a.aspect + "'s HP: "+a.HP);
                    
                    System.out.println(
                        " _______  _______  ______    ___  _______  _______  __  " + "\n" +
                        "|       ||       ||    _ |  |   ||       ||       ||  | " + "\n" +
                        "|  _____||_     _||   |_||_ |   ||    ___||    ___||  | " + "\n" +
                        "|_____  |  |   |  |    __  ||   ||    ___||    ___||__| " + "\n" +
                        "|_______|  |___|  |___|  |_||___||___|    |_______||__| " + "\n"
                    );  
                    continue;
                }
                if (s==100) {
                    if (a.godTier==0) {
                        System.out.println("The "+a.sburbClass+" of "+a.aspect + " became God Tier.");
                        a.godTier=1;
                        gt++;
                        continue;
                    } else {
                        System.out.println("The "+a.sburbClass+" of "+a.aspect + " found their Quest Bed, but they're already godtiered.");
                    } 
                }
            }
            if (state==1) {
                if (s<70) {
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + 
                    " created paradox clones of all players and left the lab.");
                    ecto=1;
                    state=0;
                    continue;
                } else {
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " is looking around the lab.");
                }
            }
            if (state==2) {
                if (s<40) {
                    System.out.println("The "+a.sburbClass+" of Space created a perfect frog!");
                    frog=1;
                    state=0;
                    continue;
                } else {
                    System.out.println("The "+a.sburbClass+" of Space is still breeding frogs...");
                }
            }
                if (state==3) {
                int dmg = rng(1,3);
                int action = rng(0,100);
                if (0 <= action && action < 80) { // Attack
                    dmg = rng(1,10)+(2*a.strifeStat);
                    if (a.godTier==1) {
                        dmg=dmg*2;
                    }
                    eHP-=dmg;
                    String flavorTxt = "";
                    int flavorNum = rng(0,3);
                    switch (flavorNum) {
                        case 0:
                            flavorTxt = "aggrieves";
                            break;
                        case 1:
                            flavorTxt = "aggresses";
                            break;
                        case 2:
                            flavorTxt = "assails";
                            break;
                        case 3:
                            flavorTxt = "assaults";
                            break;
                    }
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " " + flavorTxt +" the underling. Enemy HP: "+eHP);
                }
                if (80 <= action && action < 90) { // Defend
                    
                    if (a.godTier==1) {
                        dmg=dmg*2;
                    }
                    a.HP+=dmg;
                    String flavorTxt = "";
                    int flavorNum = rng(0,2);
                    switch (flavorNum) {
                        case 0:
                            flavorTxt = "abjures";
                            break;
                        case 1:
                            flavorTxt = "abstains from";
                            break;
                        case 2:
                            flavorTxt = "averts";
                            break;
                    }
                    
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " " + flavorTxt +" the underling's attack! ");
                }
                if (90 <= action && action < 100) { // Taunt
                    dmg-=3;
                    String flavorTxt = "";
                    int flavorNum = rng(0,2);
                    switch (flavorNum) {
                        case 0:
                            flavorTxt = "accurses";
                            break;
                        case 1:
                            flavorTxt = "antagonizes";
                            break;
                            case 2:
                            flavorTxt = "accuses";
                            break;
                    }
                    
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " " + flavorTxt +" the underling!");
                }
                if (action == 100 ) { // Anaylze
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " anaylzes the underling.");
                }
                
                if (eHP<1) {
                    state=0;
                    a.HP+=20;
                    int grist=rng(20,30);
                    a.gristTotal+=grist;
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " won the Strife! Their HP went up by 20. Their Grist increased by "+grist+".");
                    System.out.println("HP:    "+a.HP+"  Grist: "+a.gristTotal);
                    System.out.println("----------");
                }
                dmg+=rng(1,5);
                a.HP-=dmg;
                System.out.println("The "+a.sburbClass+" of "+a.aspect + " got hit. HP: "+a.HP);
                if (a.HP<1) {
                    System.out.println("The "+a.sburbClass+" of "+a.aspect + " has died...");
                    if (a.godTier==1) {
                        System.out.println(" but the "+a.sburbClass+" of "+a.aspect + " is God Tier!");
                        if (rng(1,100)<33) {
                            System.out.println("The "+a.sburbClass+" of "+a.aspect + " got revived. Their HP went up to 100.");
                            a.HP=100;
                        } else {
                            System.out.println("The "+a.sburbClass+" of "+a.aspect + " could not revive.");
                            gt--;
                            p.remove(a);
                            return;
                        }
                    } else {
                        System.out.println("Sadly, the "+a.sburbClass+" of "+a.aspect + " has NOT God Tiered.");
                        if(rng(1,100)<10) {
                            System.out.println("The "+a.sburbClass+" of "+a.aspect + " was placed on their Quest Bed. The are God Tier now and their HP went up to 100");
                            a.HP=100;
                            a.godTier=1;
                            gt++;
                        } else {
                            System.out.println("The "+a.sburbClass+" of "+a.aspect + " could not God Tier.");
                            p.remove(a);
                            return;
                        }
                    }
                }
                if (rng(1,100)<5) { 
                    state = 0;
                    System.out.println("The "+a.sburbClass+" absconds!");
                    System.out.println("HP: "+a.HP);
                    System.out.println("----------");
                }
            }
        }
    }

    public static void add() {
        System.out.println("Maybe you can get help from a past session. Did you?");
        sysWait("s", 1);
        if (rng(1,100)<16) {
            System.out.println("Yes!");
            int newPlayers=rng(2,12);
            sysWait("s", 1);
            System.out.println("Generating players...");
            ArrayList<Player> np = new ArrayList<Player>();
            //                Health        Class      Aspect     Weapon     Denizen
            np.add(new Player(rng(150,255), rng(1,12), 1,/*space*/rng(1,94), 1));
            np.add(new Player(rng(150,255), rng(1,12), 2,/*time*/ rng(1,94), 3));
            Player tmp;
            match=0;
            for (int i = 1; i <= newPlayers-2; i++) {
                do {
                    match=0;
                    //               Health        Class      Aspect     Weapon     Denizen
                    tmp = new Player(rng(100,150), rng(1,12), rng(1,12), rng(1,94), rng(1,12));
                    for(Player id:np) {
                        if (
                            (tmp.aspectID==id.aspectID) || 
                            (tmp.classID==id.classID) || 
                            (tmp.denizenID==id.denizenID)
                        ) { 
                            match = 1; 
                        }
                    }
                } while (match==1);
                for(Player id:np) {
                    if (rng(1,2)==1) {
                        id.godTier=1;
                        gt++;
                    }
                }
                np.add(tmp);
            }
            p.addAll(np);
            System.out.println("Listing all players...");
            for(Player id:p) {
                System.out.println(id.toString());
                sysWait("ms", 500);
            }
            play();
        } else {
            System.out.println("No.");
            sysWait("s", 1);
            gameOver("No one came to help.");
        }
    }

    public static void game() {
        players = 0;

        System.out.println("Generating number of players...");
        sysWait("s", 1);
        if ((forceSolo == false) || (forceMulti == false)) {
            switch (rng(1,4)) {
                case 1:
                    players = 1;
                    System.out.print(
                        "    __   _         " + "\n" +
                        "   |  |-' '-_      " + "\n" +
                        "   |         '-_   " + "\n" +
                        "_-'             '-_" + "\n" +
                        " |               | " + "\n" +
                        " |               | " + "\n" +
                        " |               | " + "\n" +
                        " |               | " + "\n" +
                        " |               | " + "\n" +
                        " |_______________| " + "\n"
                    );
                    break;
                case 2:
                    players = 4;
                    System.out.print(
                        "    __   _         " + "\n" +
                        "   |  |-' '-_      " + "\n" +
                        "   |         '-_   " + "\n" +
                        "_-'_____________'-_" + "\n" +
                        " |       |       | " + "\n" +
                        " |       |---,   | " + "\n" +
                        " |_______|___|___| " + "\n" +
                        " |       |       | " + "\n" +
                        " |       |       | " + "\n" +
                        " |_______|_______| " + "\n"
                    );
                    break;
                case 3:
                    players = 8;
                    System.out.print(
                        "    __   _         " + "\n" +
                        "   |  |-' '-_      " + "\n" +
                        "   |         '-_   " + "\n" +
                        "_-'_____________'-_" + "\n" +
                        " |       |       | " + "\n" +
                        " |       |       | " + "\n" +
                        " |_______|_______| " + "\n" +
                        " |       |       | " + "\n" +
                        " |       |---,   | " + "\n" +
                        " |_______|___|___| " + "\n" +
                        " |       |       | " + "\n" +
                        " |       |       | " + "\n" +
                        " |_______|_______| " + "\n" +
                        " |       |       | " + "\n" +
                        " |       |       | " + "\n" +
                        " |_______|_______| " + "\n"
                    );
                    break;
                case 4:
                    players = 12;
                    System.out.print(
                        "            __   _                 " + "\n" +
                        "           |  |-' '-_              " + "\n" +
                        "           |         '-_           " + "\n" +
                        "        _-'_____________'-_        " + "\n" +
                        "         |       |       |         " + "\n" +
                        "         |       |       |         " + "\n" +
                        "  _______|_______|_______|_______  " + "\n" +
                        " |       |       |       |       | " + "\n" +
                        " |       |       |---,   |       | " + "\n" +
                        " |_______|_______|___|___|_______| " + "\n" +
                        " |       |       |       |       | " + "\n" +
                        " |       |       |       |       | " + "\n" +
                        " |_______|_______|_______|_______| " + "\n" +
                        "         |       |       |         " + "\n" +
                        "         |       |       |         " + "\n" +
                        "         |_______|_______|         " + "\n"
                    );
                    break;
            }
        }
        
        if (forceSolo == true) {
            players = 1;
            System.out.print(
                "Forcing a dead session..." + "\n" +
                "You're alone." + "\n"
            );
        }
        if (forceMulti == true) {
            switch (rng(2,4)) {
                case 2: players = 4;
                    System.out.print(
                        "Forcing a normal session..." + "\n" +
                        "Four Players." + "\n"
                    );
                    break;
                case 3: players = 8;
                    System.out.print(
                        "Forcing a normal session..." + "\n" +
                        "Eight Players." + "\n"
                    );
                    break;
                case 4: players = 12;
                    System.out.print(
                        "Forcing a normal session..." + "\n" +
                        "Twelve Players." + "\n"
                    );
                    break;
            }
        }
        
        if (players==1) {
            System.out.println("Attempting to start a single player session...");
            sysWait("s", 1);
            int single = rng(1,2);
            if (single==1) {
                System.out.println("Single player session is ready to start.");
                single();
            } else {
                System.out.println("More players joined, but how many?");
                sysWait("s", 1);
                switch (rng(1,3)) {
                    case 1:
                        players = 4;
                        break;
                    case 2:
                        players = 8;
                        break;
                    case 3:
                        players = 12;
                        break;
                }
            }
        }
        System.out.println("Players: "+players);
        multiStart();
    }

    public static int scratch;
    public static int players;
    public static int match;
    public static int time=0;
    public static int space=0;
    public static Scanner sc = new Scanner(System.in);
    public static CopyOnWriteArrayList<Player> p = new CopyOnWriteArrayList<Player>();
    
    // config options
    public static boolean debug;
    public static boolean speedUp;
    public static boolean autoQuit;
    public static boolean autoRetry;
    public static boolean forceSolo;
    public static boolean forceMulti;
    
    public static void main(String[] args) throws IOException {
        PrintStream out = null;

        scratch = 0;
        try {
            String configFilePath = "settings.txt";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            debug = Boolean.parseBoolean(prop.getProperty("debug"));
            speedUp = Boolean.parseBoolean(prop.getProperty("speedUp"));
            autoQuit = Boolean.parseBoolean(prop.getProperty("autoQuit"));
            autoRetry = Boolean.parseBoolean(prop.getProperty("autoRetry"));
            
            forceSolo = Boolean.parseBoolean(prop.getProperty("forceSolo"));
            forceMulti = Boolean.parseBoolean(prop.getProperty("forceMulti"));
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }        
        System.out.println("RSBURBS version 3.0"+"\n");
        System.out.println("CREATED BY Osm70 and juxtaposedMantle." + "\n");
        if (debug == true) {
            System.out.println("[DEBUG]: Debug Mode is currently enabled.");
            System.out.println("[DEBUG]: Random Number Generation is disabled and will prompt user for an input.");
            System.out.println("[DEBUG]: If you would like to disable Debug Mode, open the config file and set 'debug' to false.\n");
        }
        System.out.println("RSBURBS client is running." + "\n");
        sysWait("s", 1);
        System.out.println("RSBURBS initializing..." + "\n");
        sysWait("s", 1);
        System.out.println("Press [ENTER] when ready.");
        sc.nextLine();
        game();
    }
}
