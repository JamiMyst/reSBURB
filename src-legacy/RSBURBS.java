/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsburbs;

import java.io.FileNotFoundException;
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

/**
 *
 * @author Osm70
 */
public class RSBURBS {
    
    
    
public static int turns;
public static int gt;
    public static int rng(int min, int max) { 
    int r = ThreadLocalRandom.current().nextInt(min, max + 1);
return r;
    }
    public static void gameOver() {
        System.out.println("You lost. GAME OVER");
        System.out.println("Press ENTER to exit");
        sc.nextLine();
         System.exit(0);
    }
    public static void spire() {
        System.out.println("Generating number of protyte spires");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        int spires=0;
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
            System.out.println("Players and spires do not match. You are in a doomed timeline.");
            gameOver();
        }
    }
   public static void single() {
       System.out.println("You are about to enter the game");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (rng(1,100)>30) {
           System.out.println("Your planet was left behind. You appeared in an empty place and died.");
           gameOver();
       }
       System.out.println("You took your planet with you.");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Locks and keys spawned");
        int locks = rng(1,100);
        int keys = rng(1,100);
         try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Locks: "+locks);
        System.out.println("Keys: "+keys);
         try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (keys<locks) {
             System.out.println("You don't have enough keys");
             gameOver();
       }
         System.out.println("You unlocked all your locks");
          try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println("The game is ready to start");
          int[] planets = new int[15];
          for (int i = 0; i < 15; i++) {
           planets[i]=1;
       }
       System.out.println("");
          System.out.println("The break started");
          for (int i = 0; i < 15; i++) {
              try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
              if (i==7) {
                  System.out.println("Planet 8 cannot be destroyed during the break");
                
                  continue;
              }
           int l = rng(1,100);
              if (l<6) {
                  System.out.println("Planet "+(i+1)+" was destroyed");
                  planets[i]=0;
              }
              else {
              System.out.println("Planet "+(i+1)+" survived");
              }
       }
          System.out.println("The break ended");
          try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println("You are now ready to start destroying planets");
          int left=80;
         for (int i = 0; i < 15; i++) {
           try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
             System.out.println("");
             System.out.println("");
             if (i==7) {
                 System.out.println("Skipping planet 8");
                 continue;
             }
             if (planets[i]!=1) {
                 System.out.println("Planet "+(i+1)+" was destroyed during the break");
                 left-=5;
                 continue;
             }
             int time=rng(5,90);
          
             System.out.println("Trying to destroy planet "+(i+1));
             try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
             System.out.println("Timer: "+left);
             System.out.println("Time it took: "+time);
             if (time>left) {
                 System.out.println("Time up. You blew up.");
                 gameOver();
             }
             System.out.println("You destroyed the planet");
             left-=5;
       }
 System.out.println("Trying to destroy planet 8");
 time=rng(5,90);
             try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
             System.out.println("Timer: "+left);
             System.out.println("Time it took: "+time);
             if (time>left) {
                 System.out.println("Time up. You blew up.");
                 gameOver();
             }
             System.out.println("You destroyed the planet");        
       System.out.println("");
       System.out.println("");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("You destroyed all planets");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Time to fight your denizen");
       Player a = new Player(rng(90,110),2);
       int eHP = rng(200,500);
       
       do {
           try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           int dmg = rng(1,10);
          if (a.godTier==1) {
                    dmg=dmg*2;
                }
                eHP-=dmg;
                System.out.println("The player attacked. Enemy HP: "+eHP);          
                dmg=rng(1,5);
             
                a.HP-=dmg;
                System.out.println("The player got hit. HP: "+a.HP);
                if (a.HP<1) {
                    System.out.println("The player died.");
                    if (a.godTier==1) {
                        System.out.println("The player is God Tier");
                        if (rng(1,100)<33) {
                            System.out.println("The player got revived. Their HP went up to 50");
                            a.HP=100;
                        }
                        else {
                            System.out.println("The player could not revive.");
                            gameOver();
                        }
                        
                    }
                    else {
                        System.out.println("The player is NOT God Tier");
                        if(rng(1,100)<10) {
                            System.out.println("The player was placed on their Quest Bed. The are God Tier now and their HP went up to 100");
                            a.HP=100;
                            a.godTier=1;
                        }
                        else {
                            System.out.println("The player could not God Tier.");
                            gameOver();
                        }
                    }
                }
       } while (eHP>1);
       System.out.println("You killed your denizen");
       win();
        
       
   }
   
   public static void multiStart() {
       
       spire();
       CopyOnWriteArrayList<Player> lst = new CopyOnWriteArrayList<Player>();
       Player tmp;
       System.out.println("Generating players");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       for (int i = 1; i <= players; i++) {
           do {
               match=0;
            tmp = new Player(rng(90,110),rng(1,12));
            for(Player id:lst) {
            if (tmp.aspectID==id.aspectID) match = 1;
            }
          
           } 
           while (match==1);
           
           lst.add(tmp);
           
       }
       p.addAll(lst);
    for(Player id:p) {
           System.out.println(id.toString());
           try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            }
    try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            add();
       }
        System.out.println("Players of Time and Space found");
        if (rng(1,100)<20) {
            System.out.println("No one prototyped their Kernelsprite pre-entry");
            add();
       }
        else {
            System.out.println("Prototyping OK");
            play();
        }
        
   }
   
   public static void play() {
      
       System.out.println("");
       System.out.println("");
       System.out.println("Starting");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Generating number of turns until Reckoning");
       turns=rng(50,200);
       
       
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("The reckoning starts in "+turns+" turns.");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Following all players");
       for(Player id:p) {
           System.out.println("");
           System.out.println("");
           System.out.println("Players alive: "+p.size());
           System.out.println("Now following player "+id.toString());
           countdown(id);
           }
       System.out.println("The Reckoning has started");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (p.size()==0) {
            System.out.println("But all players are dead");
            gameOver();
       }
        System.out.println("The players are getting ready to fight the Black King");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        int eHP=500;
        eHP+=(players*200);
       
        
       System.out.println("Black King's HP: "+eHP);
      
       boss(eHP);
       System.out.println("You defeated the Black King");
       System.out.println("Checking if end conditions are met...");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (ecto==0) {
           System.out.println("Ectobiology never happend");
           scratch();
       }
       if (frog==0) {
           System.out.println("The frog was not bred");
           System.out.println("Looking for a living space player...");
           try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           space=0;
          for(Player id:p) {
           
            if (id.aspectID==1) {
                space++;
            }
            }
           if (space==0) {
               System.out.println("Time player not found");
               scratch();
           }
           else {
               System.out.println("The frog was bred");
           }
       }
       System.out.println("Everything seems OK");
       System.out.println("");
       System.out.println("The players are getting ready to open the final door");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (rng(0,100)>2) {
           System.out.println("The players went through the door");
           win();
       }
       else {
           System.out.println("An invader entered your session and attacked you");
           try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
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

           System.out.println("Invder's HP: "+eHP);
           try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           boss(eHP);
           try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           System.out.println("You are attempting to escape your session");
           if (rng(1,100)<16) {
               System.out.println("You escaped.");
               try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
               System.out.println("The players entered a new session");
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
               System.out.println("The new session has "+players+" new players");
multiStart();
           }
           else {
               System.out.println("You couldn't escape");
               gameOver();
           }
       }
   }
   public static void win() {
    System.out.println("YOU WON");
        System.out.println("Press ENTER to exit");
        sc.nextLine();
         System.exit(0);
   }
   public static void scratch() {
       System.out.println("The game is no longer winnable");
   try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Attempting to scratch the session");
       try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (scratch!=0 || rng(1,2)==1) {
           System.out.println("You already are in a scratched session.");
           gameOver();
       }
       if (rng(1,100)>30) {
           System.out.println("The scratch failed");
           gameOver();
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
       System.out.println("You scratched the session");
       game();
   }
   
   
   public static void boss(int eHP) {
       int hp=eHP;
       do {
           try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           int targets=p.size()-1;
           if (targets==0) {
               System.out.println("All players are dead");
               gameOver();
           }
           System.out.println("Players are attacting");
           int hit = targets+gt;
          int m=rng(1,3);
          hp-=(hit*m);
           System.out.println("Boss HP: "+hp);
           if (hp<1) {
               System.out.println("The boss was defeated");
               return;
           }
           System.out.println("The boss is attacking");
          int id=rng(0,targets);
           System.out.println("Player [" +p.get(id)+"] got hit");
           p.get(id).HP-=(rng(2,15));
           System.out.println("HP: "+p.get(id).HP);
           if (p.get(id).HP<1) {
                    System.out.println("The player died.");
                    if (p.get(id).godTier==1) {
                        System.out.println("The player is God Tier");
                        if (rng(1,100)<33) {
                            System.out.println("The player got revived. Their HP went up to 50");
                            p.get(id).HP=100;
                        }
                        else {
                            System.out.println("The player could not revive.");
                            p.remove(p.get(id));
                            gt--;
                            
                        }
                        
                    }
                    else {
                        System.out.println("The player is NOT God Tier");
                        if(rng(1,100)<10) {
                            System.out.println("The player was placed on their Quest Bed. The are God Tier now and their HP went up to 100");
                            p.get(id).HP=100;
                            p.get(id).godTier=1;
                            gt++;
                        }
                        else {
                            System.out.println("The player could not God Tier.");
                            p.remove(p.get(id));
                            
                        }
                    }
                }
           
           
       } while (true);
       
       
   
   }
   public static int ecto=0;
   public static int frog=0;
   public static void countdown(Player a) {
       int eHP=0;
       System.out.println("");
       System.out.println("");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        int state=0;
       
        for (int i = turns; i > 0; i--) {
            try {
            TimeUnit.MILLISECONDS.sleep(666);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("");
            System.out.println("Turns left: "+i);
            System.out.println("");
             int s=rng(1,100);
            if (state==0) {
                if (s<5) {
                    System.out.println("The player did nothing");
                }
                if (s>5 && s<15) {
                    if (ecto==0) {
                        System.out.println("The player entered the ectobiology lab.");
                       state=1;
                       continue;
                    }
                    else {
                        System.out.println("The player did nothing");
                    }
                }
                if (s>15 && s<25) {
                    if (frog==0 && a.aspectID==1) {
                        System.out.println("The player started breeding frogs.");
                       state=2;
                       continue;
                    }
                    else {
                        System.out.println("The player did nothing");
                    } 
                }
                if (s>25 && s<100) {
                    System.out.println("The player entered a battle");
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
                    System.out.println("Player HP: "+a.HP);
                    continue;
                }
                if (s==100) {
                    if (a.godTier==0) {
                        System.out.println("The player became God Tier.");
                       a.godTier=1;
                       gt++;
                       continue;
                    }
                    else {
                        System.out.println("The player did nothing");
                    } 
                }
            }
            if (state==1) {
                if (s<70) {
                    System.out.println("The player created paradox clones of all players and left the lab");
                    ecto=1;
                    state=0;
                    continue;
                }
                else {
                    System.out.println("The player is looking around the lab");
                }
            }
            if (state==2) {
                if (s<40) {
                    System.out.println("The player created a perfect frog");
                    frog=1;
                    state=0;
                    continue;
                }
                else {
                    System.out.println("The player is still breeding frogs");
                }
            }
            if (state==3) {
                int dmg = rng(1,10);
                 if (a.godTier==1) {
                    dmg=dmg*2;
                }
                eHP-=dmg;
                System.out.println("The player attacked. Enemy HP: "+eHP);
                if (eHP<1) {
                    state=0;
                    a.HP+=20;
                    System.out.println("The player won the battle. Their HP went up by 20.");
                    System.out.println("HP: "+a.HP);
                }
                dmg=rng(1,5);
                a.HP-=dmg;
                System.out.println("The player got hit. HP: "+a.HP);
                if (a.HP<1) {
                    System.out.println("The player died.");
                    if (a.godTier==1) {
                        System.out.println("The player is God Tier");
                        if (rng(1,100)<33) {
                            System.out.println("The player got revived. Their HP went up to 50");
                            a.HP=100;
                        }
                        else {
                            System.out.println("The player could not revive.");
                            gt--;
                            p.remove(a);
                            return;
                        }
                        
                    }
                    else {
                        System.out.println("The player is NOT God Tier");
                        if(rng(1,100)<10) {
                            System.out.println("The player was placed on their Quest Bed. The are God Tier now and their HP went up to 100");
                            a.HP=100;
                            a.godTier=1;
                            gt++;
                        }
                        else {
                            System.out.println("The player could not God Tier.");
                            p.remove(a);
                            return;
                        }
                    }
                }
            }
       }
   }
   public static void add() {
       System.out.println("Maybe you can get help from a past session. Did you?");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (rng(1,100)<16) {
           System.out.println("Yes");
           System.out.println("Generating how many new players");
           int newPlayers=rng(2,12);
            try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           System.out.println("Generating players");
           ArrayList<Player> np = new ArrayList<Player>();
           np.add(new Player(rng(1,255),1));
           np.add(new Player(rng(1,255),2));
           Player tmp;
           match=0;
           for (int i = 1; i <= newPlayers-2; i++) {
           do {
               match=0;
            tmp = new Player(rng(90,110),rng(1,12));
            for(Player id:np) {
            if (tmp.aspectID==id.aspectID) match = 1;
            }
          
           } 
           while (match==1);
           for(Player id:np) {
            if (rng(1,2)==1) {
            id.godTier=1;
            gt++;
            }
            }
           np.add(tmp);
           
       }
           p.addAll(np);
           System.out.println("Listing all players");
            for(Player id:p) {
           System.out.println(id.toString());
           try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            }
            play();
       }
       else {
           System.out.println("No");
            try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
           gameOver();
       }
   }
    public static void game() {
        players=0;
    
        System.out.println("Generating number of players");
                try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (rng(1,4)) {
            case 1:
                players = 1;
                break;
                case 2:
                players = 4;
                break;
                case 3:
                players = 8;
                break;
                case 4:
                players = 12;
                break;
        }
        
        if (players==1) {
            System.out.println("Attempting to start a single player session");
                    try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
            int single = rng(1,2);
            if (single==1) {
                System.out.println("Single player session is ready to start");
                single();
            }
           else {
            System.out.println("More players joined, generating how many");
            try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public static void main(String[] args) {
         PrintStream out = null;

         scratch = 0;
        
        System.out.println("Realistic SBURB Simulator");
        System.out.println("Read the documentation first");
        System.out.println("Press ENTER to start");
        sc.nextLine();
        System.out.print("S");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("B");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("U");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("R");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("B");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBURBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        game();
           
    }
    
}
