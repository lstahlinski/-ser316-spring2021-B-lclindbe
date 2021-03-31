import java.util.concurrent.ThreadLocalRandom;

public class Mascotmon {
    String description;
    public String type;
    public Name name;
    public Stats stats;
    public double weatherBonus = 1.0; 
    public double typeBonus = 1.0;
    public int bufCounter = 0;

    /**
     * Constructor for Mascotmon.
     */
    public Mascotmon() {
        int rand = ThreadLocalRandom.current().nextInt(0, 4);
        if (rand == 0) {
            name = Name.ALBERT;
        } else if (rand == 1) {
            name = Name.RALPHIE;
        } else if (rand == 2) {
            name = Name.SPARKY;
        } else {
            name = Name.BULLY;
        }
        getType();
        getStats();
        getDescription();
    }

   
    /**
     * Gets Mastotmon's name and stats.
     */
    public Mascotmon(Name name) {
        this.name = name;
        getType();
        getStats();
        getDescription();
    }

    private void getType() {
        Type t = new Type(name);
        this.type = t.type;
    }

    private void getStats() {
        stats = new Stats(name);
        
        
    }

    private void getDescription() {
        Description desc = new Description(name);
        this.description = desc.description;
    }

    /**
     * Method randomly determines an attack to use based on the defending Mascotmon and
     * returns the base damage of the attack selected. The self-buff (attackNumber 0) can only be
     * used 3 times during a battle.
     * @return attack damage 
     */

    public Attack attack(int chosenAttackNumber, Boolean randomAttacks) {
        
        //double attack_Damage = 0;
        //int attackNumber = 0;

        if (randomAttacks == true) {
            while (true) {
                chosenAttackNumber = ThreadLocalRandom.current().nextInt(0, 4);
                if (chosenAttackNumber == 0 && bufCounter <= 2) {
                    bufCounter++;
                    break;
                } else if (chosenAttackNumber != 0) {
                    break;
                }
            }
        }
        

        String desc = "";
        Attack attack = null;

        switch (name) {
            case ALBERT:
                if (chosenAttackNumber == 0) {
                    desc = " uses Iron Scales, increasing defense stat by 10%";
                    stats.defense *= 1.10;
                    attack = new Attack(0, "None");
                } else if (chosenAttackNumber == 1) {
                    desc = " uses Death Roll";
                    attack = new Attack(stats.attack, "Ground");
                } else if (chosenAttackNumber == 2) {
                    desc = " uses Chomp";
                    attack = new Attack(stats.attack, "Normal");
                } else if (chosenAttackNumber == 3) {
                    desc = " uses Aqua Cannon";
                    attack = new Attack(stats.attack, "Water");
                }
                break;
            case RALPHIE:
                if (chosenAttackNumber == 0) {
                    desc = " uses Iron Hide, increasing defense stat by 10%";
                    stats.defense *= 1.10;
                    attack = new Attack(0, "None");
                } else if (chosenAttackNumber == 1) {
                    desc = " uses Ground Stomp";
                    attack = new Attack(stats.attack, "Ground");
                } else if (chosenAttackNumber == 2) {
                    desc = " uses Headbutt";
                    attack = new Attack(stats.attack, "Normal");
                } else if (chosenAttackNumber == 3) {
                    desc = " uses Flaming Horn";
                    attack = new Attack(stats.attack, "Fire");
                }
                break;
            case SPARKY:
                if (chosenAttackNumber == 0) {
                    desc = " uses Heat Up, increasing attack stat by 10%";
                    stats.attack *= 1.10;
                    attack = new Attack(0, "None");
                } else if (chosenAttackNumber == 1) {
                    desc = " uses Inferno";
                    attack = new Attack(stats.attack, "Fire");
                } else if (chosenAttackNumber == 2) {
                    desc = " uses Quick Attack";
                    attack = new Attack(stats.attack, "Normal");
                    System.out.println("Attack value: " + stats.attack);
                } else if (chosenAttackNumber == 3) {
                    desc = " uses Earthquake";
                    attack = new Attack(stats.attack, "Ground");
                }
                break;
            case BULLY:
                if (chosenAttackNumber == 0) {
                    desc = " uses Sleep, increasing health stat by 10%";
                    double health = stats.health * 1.10;
                    stats.health = Math.round(health);
                    attack = new Attack(0, "None");
                } else if (chosenAttackNumber == 1) {
                    desc = " uses Body Slam";
                    attack = new Attack(stats.attack, "Normal");
                } else if (chosenAttackNumber == 2) {
                    desc = " uses Splash";
                    attack = new Attack(stats.attack, "Water");
                } else if (chosenAttackNumber == 3) {
                    desc = " uses Ground Pound";
                    attack = new Attack(stats.attack, "Ground");
                }
                
        }
            
        System.out.println(name.toString().toLowerCase() + desc);
        return attack;
    }

    public enum Name {
        ALBERT, RALPHIE, SPARKY, BULLY
    }
}
