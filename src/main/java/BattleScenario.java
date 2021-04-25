import java.util.concurrent.ThreadLocalRandom;

public class BattleScenario {

    Mascotmon mon1;
    Mascotmon mon2;
    Stats mon1Stats;
    //Stats mon2Stats;
    Environment battleWeather;
    double totalAttack;
    double attackervsType;
    double defendervsType;


    public BattleScenario(Mascotmon p1Mon, Mascotmon p2Mon) {
        setMon1(p1Mon);
        setMon2(p2Mon);
    } 

    /**
     * Sets environment of the battlefield, and sets buff/debuff modifiers for all Mascotmons on the
     * field. If the Mascotmon's type is buffed by the environment,they receive a 25% multiplier to
     * their attack and defense stat. If the Mascotmon's type is debuffed by the environment, they
     * receive a reduction of 25% to their attack and defense stat.
     */
    public void setEnvironment(Environment.Weather p1Weather) {
        battleWeather = new Environment(p1Weather);
    }

    /**
     * Initiate Battle. 
     */
    public void initiateBattle() {

        // initiate stats for mon1 and mon2
        mon1Stats = new Stats(mon1.name);
        //mon2Stats = new Stats(mon2.name);
        System.out.println("Woooo: " + mon1Stats.health);

        System.out.println("\nWelcome everyone to the Mascotmon training arena!");
        System.out.println("It is a " + battleWeather.weather1.toString().toLowerCase()
                + " day here at Frank Kush Field");
        System.out.println("Today, on the attacking team we have " + mon1.name + " " 
               + mon1.description);
        System.out.println("Their opponent, on the defending team is " + mon2.name + " " 
               + mon2.description);
        System.out.println(mon2.name + " prepares for the incoming attack");
        // added random attacks to allow for random attacks per round
        Mascotmon winner = fight(1,1,true);
        System.out.println(winner.name + " has won with " + winner.stats.health + " health left");
    }

    /**
     * Sample fight scenario of two rounds.
     * Each Mascotmon uses one random attack per round; this attack multiplier is used to calculate 
     * damage output against opposing mascotmon. 
     */
    // Added parameters to allow for testing.
    // Added randomAttack parameter to allow the game to still be random, but for 
    // testing, this will be false

    public void fightRound (Mascotmon monster1, Mascotmon monster2, int round, int choosenAttackNumber1, 
        int choosenAttackNumber2, Boolean randomAttack) {
        
        double damage1;
        double damage2;

        Attack attack1;
        Attack attack2;
        
        System.out.println("\n" + monster1.name + " launches an attack against " + monster2.name + "!");
        attack1 = monster1.attack(choosenAttackNumber1, randomAttack);
        
        //Calculate damage:
        damage1 = calculateDamage(attack1, monster1, monster2);
        
        System.out.println(damage1 + " damage dealt");

        //Adjust mon2's health:
        monster2.stats.health = monster2.stats.health - damage1;
        System.out.println(monster2.name + " has " + monster2.stats.health + " health left");

    }

    public Mascotmon fight(int choosenAttackNumber1, int choosenAttackNumber2, 
          Boolean randomAttack) {

        int round = 1;
    

        while (true) {
            fightRound(mon1, mon2, round, choosenAttackNumber1, choosenAttackNumber2, randomAttack);

            if (mon2.stats.health <= 0.0) {
                System.out.println(mon2.name + " has fainted in round " + round);
                return mon1;
            }

            fightRound(mon2, mon1, round, choosenAttackNumber1, choosenAttackNumber2, randomAttack);

            if (mon2.stats.health <= 0.0) {
                System.out.println(mon2.name + " has fainted in round " + round);
                return mon1;
            }
             
            round++;
        } //end while
    }


    public void setMon1(Mascotmon pmon) {
        mon1 = pmon;
    }


    public void setMon2(Mascotmon pmon) {
        mon2 =  pmon;
    }


    /**
      * TO DO: Implement for Assignment 3
      * This method implements the calculation of damage for one specific attack.
      * One monster attacks with the given damage, the dealt damage is then calculated through
      * (pAttackDamage * p1Attacker.weatherBonus * p1Attacker.typeBonus) -
                (p1Defender.stats.defense * p1Defender.weatherBonus * p1Defender.typeBonus)
      * If the initial pAttackDamage is 0, then the damage dealt is 0. If the totalDamage calculated
      * is negative, the totalDamage dealt should be 1.Any positive value is the total damage dealt.
      * Weather bonus: see the Environment which you can assume is correct.
      * You need to check though if the weather bonus is applied 
      * correctly, since maybe the method does not use the environment correctly. 
      * or debuffed based on the weather. EG. fire monsters have a stat advantage of +25% in 
      * sunny weather
      * while they have a stat disadvantage of -25% in the rain.
      * If the attack chosen, matches the monsters type, the attacker will get an 
      * extra 20% on its attack.
      * Type bonus: Certain monsters have an attack bonus against others:
      *     Fire against Water: Water gains 25% while Fire looses 25%
      *     Fire against Ground: Fire gains 25% while Ground looses 25%
      *     Ground against Water: Ground gains 25% while Water looses 25%
      *     Normal mon: never gain any type bonus and are weaker during droughts.
      * These bonuses do not stack up, they are just applied for every attack. 
      * @param p1Attack is the attack value given to the method where that attack value is 
      *     based on the monsters damage value.
      * @param p1Attacker the attacking monster
      * @param p1Defender the defending monster (the defending monster will never get damage)
      *     to calculate damage output.
      * @return total damage output
      */
    
    public double calculateDamage(Attack p1Attack, Mascotmon p1Attacker, Mascotmon p1Defender) {
        
        p1Attacker.weatherBonus = 1;
        p1Attacker.typeBonus = 1;
        p1Defender.typeBonus = 1; 
        p1Defender.weatherBonus = 1;
        attackervsType = 1;
        defendervsType = 1;

     
        
        if (p1Attack.damage == 0) {
            totalAttack = 0; 
            return Math.round(totalAttack);
        } else if (p1Attacker.name == Mascotmon.Name.ALBERT) {
            if (battleWeather.weather1 == Environment.Weather.sunny) {
                p1Attacker.weatherBonus = 0.75;
            } else if (battleWeather.weather1 == Environment.Weather.rainy) {
                p1Attacker.weatherBonus = 1.25;
            } else {
                p1Attacker.weatherBonus = 1;
            }

            if (battleWeather.weather1 == Environment.Weather.sunny && p1Defender.name == Mascotmon.Name.SPARKY) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.rainy && p1Defender.name == Mascotmon.Name.SPARKY) {
                p1Defender.weatherBonus = 0.75;
            } else if (battleWeather.weather1 == Environment.Weather.drought && p1Defender.name == Mascotmon.Name.RALPHIE) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.drought && p1Defender.name == Mascotmon.Name.BULLY) {
                p1Defender.weatherBonus = 0.75;
            } else {
                p1Defender.weatherBonus = 1;
            }
            
            if (p1Attack.type == "Water") {
                p1Attacker.typeBonus = 1.2;
            } else {
                p1Attacker.typeBonus = 1; 
            }

            if (p1Defender.name == Mascotmon.Name.SPARKY) {
                attackervsType = 1.25;
                defendervsType = 0.75; 
            } else if (p1Defender.name == Mascotmon.Name.RALPHIE) {
                attackervsType = 0.75;
                defendervsType = 1.25;
            }
            
            totalAttack = (p1Attack.damage * p1Attacker.weatherBonus * p1Attacker.typeBonus * attackervsType) - (p1Defender.stats.defense * defendervsType * p1Defender.weatherBonus * p1Defender.typeBonus);
            
            if (totalAttack < 0) {
                totalAttack = 1;
                
                return Math.round(totalAttack);
            } else {
                
                return Math.round(totalAttack);
            }
            
        } else if (p1Attacker.name == Mascotmon.Name.RALPHIE) {
            
            if (battleWeather.weather1 == Environment.Weather.drought) {
                p1Attacker.weatherBonus = 1.25;
            }

            if (battleWeather.weather1 == Environment.Weather.sunny && p1Defender.name == Mascotmon.Name.SPARKY) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.rainy && p1Defender.name == Mascotmon.Name.SPARKY) {
                p1Defender.weatherBonus = 0.75;
            } else if (battleWeather.weather1 == Environment.Weather.rainy && p1Defender.name == Mascotmon.Name.ALBERT) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.drought && p1Defender.name == Mascotmon.Name.BULLY) {
                p1Defender.weatherBonus = 0.75;
            }
            if (p1Attack.type == "Ground") {
                p1Attacker.typeBonus = 1.2;
            } else {
                p1Attacker.typeBonus = 1; 
            }
   
            if (p1Defender.name == Mascotmon.Name.ALBERT) {
                attackervsType = 1.25;
                defendervsType = 0.75;
            } else if (p1Defender.name == Mascotmon.Name.SPARKY) {
                defendervsType = 1.25;
                attackervsType = 0.75;
            }
            totalAttack = (p1Attack.damage * p1Attacker.weatherBonus * p1Attacker.typeBonus * attackervsType) - (p1Defender.stats.defense * defendervsType * p1Defender.weatherBonus * p1Defender.typeBonus);
            
            if (totalAttack < 0) {
                totalAttack = 1;
                
                return Math.round(totalAttack);
            } else {
                
                return Math.round(totalAttack);
            }
        
        } else if (p1Attacker.name == Mascotmon.Name.SPARKY) {
            
            
            if (battleWeather.weather1 == Environment.Weather.sunny) {
                p1Attacker.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.rainy) {
                p1Attacker.weatherBonus = 0.75;
            } else {
                p1Attacker.weatherBonus = 1;
            }

            if (battleWeather.weather1 == Environment.Weather.sunny && p1Defender.name == Mascotmon.Name.ALBERT) {
                p1Defender.weatherBonus = 0.75;
            } else if (battleWeather.weather1 == Environment.Weather.rainy && p1Defender.name == Mascotmon.Name.ALBERT) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.drought && p1Defender.name == Mascotmon.Name.RALPHIE) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.drought && p1Defender.name == Mascotmon.Name.BULLY) {
                p1Defender.weatherBonus = 0.75;
            } else {
                p1Defender.weatherBonus = 1;
            }

            if (p1Attack.type == "Fire") {
                p1Attacker.typeBonus = 1.2;
            } else {
                p1Attacker.typeBonus = 1; 
            }

            if (p1Defender.name == Mascotmon.Name.RALPHIE) {
                attackervsType = 1.25;
                defendervsType = 0.75;
            } else if (p1Defender.name == Mascotmon.Name.ALBERT) {
                defendervsType = 1.25;
                attackervsType = 0.75;
            }

            totalAttack = (p1Attack.damage * p1Attacker.weatherBonus * p1Attacker.typeBonus * attackervsType) - (p1Defender.stats.defense * defendervsType * p1Defender.weatherBonus * p1Defender.typeBonus);
            
            if (totalAttack < 0) {
                totalAttack = 1;
                
                return Math.round(totalAttack);
            } else {
                
                return Math.round(totalAttack);
            }
   
        } else {
            
            if (battleWeather.weather1 == Environment.Weather.drought) {
                p1Attacker.weatherBonus = 0.75;
            } else {
                p1Attacker.weatherBonus = 1;
            }
         
            if (battleWeather.weather1 == Environment.Weather.sunny && p1Defender.name == Mascotmon.Name.SPARKY) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.rainy && p1Defender.name == Mascotmon.Name.SPARKY) {
                p1Defender.weatherBonus = 0.75;
            } else if (battleWeather.weather1 == Environment.Weather.drought && p1Defender.name == Mascotmon.Name.RALPHIE) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.rainy && p1Defender.name == Mascotmon.Name.ALBERT) {
                p1Defender.weatherBonus = 1.25;
            } else if (battleWeather.weather1 == Environment.Weather.sunny && p1Defender.name == Mascotmon.Name.ALBERT) {
                p1Defender.weatherBonus = 0.75;
            } else {
                p1Defender.weatherBonus = 1;
            }
            
            if (p1Attack.type == "Normal") {
                p1Attacker.typeBonus = 1.2;
            } else {
                p1Attacker.typeBonus = 1; 
            }
          
            totalAttack = (p1Attack.damage * p1Attacker.weatherBonus * p1Attacker.typeBonus * attackervsType) - (p1Defender.stats.defense * defendervsType * p1Defender.weatherBonus * p1Defender.typeBonus);
          
            if (totalAttack < 0) {
                totalAttack = 1;
                
                return Math.round(totalAttack);
            } else {
                
                return Math.round(totalAttack);
            }
        }
        
        
    }
    

}