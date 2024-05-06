/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsburbs;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Osm70
 */
class Player {
    public int HP;
    public String aspect;
    public int aspectID;
    public int godTier;
    @Override
    public String toString() {
        if(godTier==1) {
        return "Aspect: "+aspect+" HP: "+HP + " God Tier"; 
        }
        else {
   return "Aspect: "+aspect+" HP: "+HP; 
        }
    }
    public Player(int HP, int aspectID) {
        this.godTier=0;
        this.HP = HP;
         this.aspectID = aspectID;
    aspect="";
    switch (this.aspectID) {
        case 1:
            this.aspect="Space";
            break;
            case 2:
            this.aspect="Time";
            break;
            case 3:
            this.aspect="Mind";
            break;
            case 4:
            this.aspect="Heart";
            break;
            case 5:
            this.aspect="Hope";
            break;
            case 6:
            this.aspect="Rage";
            break;
            case 7:
            this.aspect="Breath";
            break;
            case 8:
            this.aspect="Blood";
            break;
            case 9:
            this.aspect="Life";
            break;
            case 10:
            this.aspect="Doom";
            break;
            case 11:
            this.aspect="Light";
            break;
            case 12:
            this.aspect="Void";
            break;
    }
       
        
    }
   
}
