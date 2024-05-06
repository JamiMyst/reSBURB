import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Osm70, juxtaposedMantle
 */
class Player {
    public static int rng(int min, int max) {
            int r = ThreadLocalRandom.current().nextInt(min, max + 1);
            return r;
    }
    
    public int HP;
    public String sburbClass;
    public String aspect;
    public String strifeSpec;
    public String denizen;
    
    public int classID = 0;
    public int aspectID = 0;
    public int strifeID = 0;
    public int strifeStat;
    public int denizenID = 0;
    public int STR = 1;
    public int CON = 1;
    public int DEX = 1;
    public int INT = 1;
    public int WIS = 1;
    public int CHA = 1;
    
    public int godTier;
    public int gristTotal;
    @Override
    public String toString() {
        if(godTier==1) {
            return sburbClass +" of "+aspect+" HP: "+HP+" (God Tier) "; 
        } else {
            return sburbClass +" of "+aspect+" HP: "+HP; 
        }
    }
    
    public String detailString() {
        if(godTier==1) {
            return (sburbClass +" of "+aspect + "  -  "+STR+"/"+CON+"/"+DEX+"/"+INT+"/"+WIS+"/"+CHA + "\n" +
            "  - HP: " + HP + " Grist: " + gristTotal + "\n" +
            "  - "+strifeSpec+"kind" + "\n" +
            "  - God Tiered");
        } else {
            return (sburbClass +" of "+aspect + "  -  "+STR+"/"+CON+"/"+DEX+"/"+INT+"/"+WIS+"/"+CHA + "\n" +
            "  - HP: " + HP + " Grist: " + gristTotal + "\n" +
            "  - "+strifeSpec+"kind (+" + strifeStat + ")");
        }
    }
        
    public Player(int HP, int classID, int aspectID, int strifeID, int denizenID) {
        this.godTier=0;
        this.HP = HP;
        this.classID = classID;
        this.aspectID = aspectID;
        this.strifeID = strifeID;
        this.denizenID = denizenID;
        sburbClass="";
        aspect="";
        switch (this.classID) {
            case 1:
                this.sburbClass="Thief";
                this.STR+=1;
                this.CON+=0;
                this.DEX+=2;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 2:
                this.sburbClass="Rogue";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=2;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=1;
                break;
            case 3:
                this.sburbClass="Bard";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=2;
                break;
            case 4:
                this.sburbClass="Prince";
                this.STR+=1;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=2;
                break;
            case 5:
                this.sburbClass="Heir";
                this.STR+=2;
                this.CON+=1;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 6:
                this.sburbClass="Page";
                this.STR+=0;
                this.CON+=2;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 7:
                this.sburbClass="Seer";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=1;
                this.WIS+=2;
                this.CHA+=0;
                break;
            case 8:
                this.sburbClass="Maid";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=2;
                this.WIS+=1;
                this.CHA+=0;
                break;
            case 9:
                this.sburbClass="Sylph";
                this.STR+=0;
                this.CON+=1;
                this.DEX+=0;
                this.INT+=2;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 10:
                this.sburbClass="Knight";
                this.STR+=1;
                this.CON+=2;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 11:
                this.sburbClass="Witch";
                this.STR+=2;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=1;
                this.CHA+=0;
                break;
            case 12:
                this.sburbClass="Mage";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=2;
                this.CHA+=0;
                break;
            case 13:
                this.sburbClass="Lord";
                this.STR+=2;
                this.CON+=1;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 14:
                this.sburbClass="Muse";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=2;
                this.WIS+=1;
                this.CHA+=1;
                break;
            default:
                System.out.println("[WARNING]: Out of range 'classID' declaration.");
                this.sburbClass="Hero";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
        }
        switch (this.aspectID) {
            case 1:
                this.aspect="Space";
                this.STR+=0;
                this.CON+=1;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=2;
                this.CHA+=0;
                break;
            case 2:
                this.aspect="Time";
                this.STR+=2;
                this.CON+=0;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 3:
                this.aspect="Mind";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=2;
                this.WIS+=0;
                this.CHA+=1;
                break;
            case 4:
                this.aspect="Heart";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=1;
                this.CHA+=2;
                break;
            case 5:
                this.aspect="Hope";
                this.STR+=0;
                this.CON+=1;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=1;
                break;
            case 6:
                this.aspect="Rage";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 7:
                this.aspect="Breath";
                this.STR+=0;
                this.CON+=2;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=1;
                break;
            case 8:
                this.aspect="Blood";
                this.STR+=1;
                this.CON+=0;
                this.DEX+=2;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 9:
                this.aspect="Life";
                this.STR+=0;
                this.CON+=1;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=2;
                this.CHA+=0;
                break;
            case 10:
                this.aspect="Doom";
                this.STR+=2;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=1;
                this.WIS+=0;
                this.CHA+=0;
                break;
            case 11:
                this.aspect="Light";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=2;
                this.WIS+=1;
                this.CHA+=0;
                break;
            case 12:
                this.aspect="Void";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=1;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=2;
                break;
            default: 
                System.out.println("[WARNING]: Out of range 'aspectID' declaration.");
                this.aspect="Null";
                this.STR+=0;
                this.CON+=0;
                this.DEX+=0;
                this.INT+=0;
                this.WIS+=0;
                this.CHA+=0;
                break;
        }
        switch (this.strifeID) {
            case 1:
                this.strifeSpec="aerosol";
                this.strifeStat+=INT;
                break;
            case 2:
                this.strifeSpec="ball";
                this.strifeStat+=DEX;
                break;
            case 3:
                this.strifeSpec="barbwire";
                this.strifeStat+=INT;
                break;
            case 4:
                this.strifeSpec="bat";
                this.strifeStat+=STR;
                break;
            case 5:
                this.strifeSpec="blade";
                this.strifeStat+=STR;
                break;
            case 6:
                this.strifeSpec="bomb";
                this.strifeStat+=DEX;
                break;
            case 7:
                this.strifeSpec="bow";
                this.strifeStat+=DEX;
                break;
            case 8:
                this.strifeSpec="bowlgpin";
                this.strifeStat+=STR;
                break;
            case 9:
                this.strifeSpec="branch";
                this.strifeStat+=STR;
                break;
            case 10:
                this.strifeSpec="brass";
                this.strifeStat+=CHA;
                break;
            case 11:
                this.strifeSpec="broom";
                this.strifeStat+=INT;
                break;
            case 12:
                this.strifeSpec="bunny";
                this.strifeStat+=WIS;
                break;
            case 13:
                this.strifeSpec="bust";
                this.strifeStat+=INT;
                break;
            case 14:
                this.strifeSpec="candlstk";
                this.strifeStat+=INT;
                break;
            case 15:
                this.strifeSpec="cane";
                this.strifeStat+=WIS;
                break;
            case 16:
                this.strifeSpec="chain";
                this.strifeStat+=STR;
                break;
            case 17:
                this.strifeSpec="chainsaw";
                this.strifeStat+=DEX;
                break;
            case 18:
                this.strifeSpec="chisel";
                this.strifeStat+=CHA;
                break;
            case 19:
                this.strifeSpec="claw";
                this.strifeStat+=STR;
                break;
            case 20:
                this.strifeSpec="cleaver";
                this.strifeStat+=STR;
                break;
            case 21:
                this.strifeSpec="club";
                this.strifeStat+=STR;
                break;
            case 22:
                this.strifeSpec="cord";
                this.strifeStat+=DEX;
                break;
            case 23:
                this.strifeSpec="crowbar";
                this.strifeStat+=DEX;
                break;
            case 24:
                this.strifeSpec="curliron";
                this.strifeStat+=STR;
                break;
            case 25:
                this.strifeSpec="dart";
                this.strifeStat+=DEX;
                break;
            case 26:
                this.strifeSpec="dice";
                this.strifeStat+=(WIS-1);
                break;
            case 27:
                this.strifeSpec="dumbbell";
                this.strifeStat+=STR;
                break;
            case 28:
                this.strifeSpec="fan";
                this.strifeStat+=DEX;
                break;
            case 29:
                this.strifeSpec="fireext";
                this.strifeStat+=INT;
                break;
            case 30:
                this.strifeSpec="firework";
                this.strifeStat+=DEX;
                break;
            case 31:
                this.strifeSpec="flshlght";
                this.strifeStat+=STR;
                break;
            case 32:
                this.strifeSpec="fncysnta";
                this.strifeStat+=WIS;
                break;
            case 33:
                this.strifeSpec="glove";
                this.strifeStat+=DEX;
                break;
            case 34:
                this.strifeSpec="golfclub";
                this.strifeStat+=STR;
                break;
            case 35:
                this.strifeSpec="hairdryr";
                this.strifeStat+=(INT+1);
                break;
            case 36:
                this.strifeSpec="hammer";
                this.strifeStat+=STR;
                break;
            case 37:
                this.strifeSpec="hatchet";
                this.strifeStat+=STR;
                break;
            case 38:
                this.strifeSpec="hckystck";
                this.strifeStat+=DEX;
                break;
            case 39:
                this.strifeSpec="hose";
                this.strifeStat+=DEX;
                break;
            case 40:
                this.strifeSpec="icepick";
                this.strifeStat+=CHA;
                break;
            case 41:
                this.strifeSpec="iceskate";
                this.strifeStat+=DEX;
                break;
            case 42:
                this.strifeSpec="iron";
                this.strifeStat+=2;
                break;
            case 43:
                this.strifeSpec="joker";
                this.strifeStat+=WIS;
                break;
            case 44:
                this.strifeSpec="jumprope";
                this.strifeStat+=WIS;
                break;
            case 45:
                this.strifeSpec="knife";
                this.strifeStat+=STR;
                break;
            case 46:
                this.strifeSpec="ladle";
                this.strifeStat+=DEX;
                break;
            case 47:
                this.strifeSpec="lamp";
                this.strifeStat+=WIS;
                break;
            case 48:
                this.strifeSpec="lance";
                this.strifeStat+=STR;
                break;
            case 49:
                this.strifeSpec="lcrsstck";
                this.strifeStat+=STR;
                break;
            case 50:
                this.strifeSpec="marble";
                this.strifeStat+=0;
                break;
            case 51:
                this.strifeSpec="mop";
                this.strifeStat+=STR;
                break;
            case 52:
                this.strifeSpec="nailgun";
                this.strifeStat+=DEX;
                break;
            case 53:
                this.strifeSpec="needle";
                this.strifeStat+=DEX;
                break;
            case 54:
                this.strifeSpec="paddle";
                this.strifeStat+=STR;
                break;
            case 55:
                this.strifeSpec="paper";
                this.strifeStat+=WIS;
                break;
            case 56:
                this.strifeSpec="peprmill";
                this.strifeStat+=WIS;
                break;
            case 57:
                this.strifeSpec="peprspry";
                this.strifeStat+=DEX;
                break;
            case 58:
                this.strifeSpec="pipe";
                this.strifeStat+=STR;
                break;
            case 59:
                this.strifeSpec="pistol";
                this.strifeStat+=DEX;
                break;
            case 60:
                this.strifeSpec="pizzactr";
                this.strifeStat+=STR;
                break;
            case 61:
                this.strifeSpec="plank";
                this.strifeStat+=STR;
                break;
            case 62:
                this.strifeSpec="plier";
                this.strifeStat+=STR;
                break;
            case 63:
                this.strifeSpec="plunger";
                this.strifeStat+=STR;
                break;
            case 64:
                this.strifeSpec="poker";
                this.strifeStat+=CHA;
                break;
            case 65:
                this.strifeSpec="puppet";
                this.strifeStat+=WIS;
                break;
            case 66:
                this.strifeSpec="rake";
                this.strifeStat+=DEX;
                break;
            case 67:
                this.strifeSpec="razor";
                this.strifeStat+=STR;
                break;
            case 68:
                this.strifeSpec="rifle";
                this.strifeStat+=DEX;
                break;
            case 69:
                this.strifeSpec="rock";
                this.strifeStat+=STR;
                break;
            case 70:
                this.strifeSpec="rollpin";
                this.strifeStat+=STR;
                break;
            case 71:
                this.strifeSpec="saw";
                this.strifeStat+=STR;
                break;
            case 72:
                this.strifeSpec="scissor";
                this.strifeStat+=CHA;
                break;
            case 73:
                this.strifeSpec="scrwdrvr";
                this.strifeStat+=DEX;
                break;
            case 74:
                this.strifeSpec="scythe";
                this.strifeStat+=STR;
                break;
            case 75:
                this.strifeSpec="shoe";
                this.strifeStat+=DEX;
                break;
            case 76:
                this.strifeSpec="shotgun";
                this.strifeStat+=DEX;
                break;
            case 77:
                this.strifeSpec="shovel";
                this.strifeStat+=STR;
                break;
            case 78:
                this.strifeSpec="sickle";
                this.strifeStat+=STR;
                break;
            case 79:
                this.strifeSpec="spade";
                this.strifeStat+=WIS;
                break;
            case 80:
                this.strifeSpec="spatula";
                this.strifeStat+=WIS;
                break;
            case 81:
                this.strifeSpec="spear";
                this.strifeStat+=DEX;
                break;
            case 82:
                this.strifeSpec="spoon";
                this.strifeStat+=DEX;
                break;
            case 83:
                this.strifeSpec="stapler";
                this.strifeStat+=CHA;
                break;
            case 84:
                this.strifeSpec="stungun";
                this.strifeStat+=DEX;
                break;
            case 85:
                this.strifeSpec="tableleg";
                this.strifeStat+=STR;
                break;
            case 86:
                this.strifeSpec="thrwstar";
                this.strifeStat+=DEX;
                break;
            case 87:
                this.strifeSpec="tongs";
                this.strifeStat+=WIS;
                break;
            case 88:
                this.strifeSpec="trophy";
                this.strifeStat+=STR;
                break;
            case 89:
                this.strifeSpec="umbrella";
                this.strifeStat+=DEX;
                break;
            case 90:
                this.strifeSpec="vacuum";
                this.strifeStat+=STR;
                break;
            case 91:
                this.strifeSpec="wand";
                this.strifeStat+=WIS;
                break;
            case 92:
                this.strifeSpec="woodwind";
                this.strifeStat+=CHA;
                break;
            case 93:
                this.strifeSpec="wrench";
                this.strifeStat+=STR;
                break;
            case 94:
                this.strifeSpec="yoyo";
                this.strifeStat+=DEX;
                break;
            
            default:
                System.out.println("[WARNING]: Out of range 'strifeID' declaration.");
                this.strifeSpec="ERROR";
                this.strifeStat+=2;
                break;
        }
        if (this.aspectID!=1) {
            switch (this.denizenID) {
                case 1:
                    denizen = "Psyche";
                    break;
                case 2:
                    denizen = "Cetus";
                    break;
                case 3:
                    denizen = "Hephaestus";
                    break;
                case 4:
                    denizen = "Typheus";
                    break;
                case 5:
                    denizen = "Nix";
                    break;
                case 6:
                    denizen = "Hemera";
                    break;
                case 7:
                    denizen = "Abraxas";
                    break;
                case 8:
                    denizen = "Yaldabaoth";
                    break;
                case 9:
                    denizen = "Moros";
                    break;
                case 10:
                    denizen = "Ares";
                    break;
                case 11:
                    denizen = "Eirene";
                    break;
                case 12:
                    denizen = "??????";
                    break;
                default:
                    System.out.println("[WARNING]: Out of range 'denizenID' declaration.");
                    denizen = "The Author";
                    break;
            }
        } else {
            denizen = "Echidna";
        }
    }
}
