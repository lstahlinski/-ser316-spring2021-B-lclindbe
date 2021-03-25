import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;


public class GivenWhiteBox {
    @Before
    public void setUp() throws Exception {
    }


    /**
     * This is not deterministic if total damage is calculated correctly. Try to find out why and what the problem is.
     * Then make changes so that the battle outcome IS deterministic!
     * The battle mechanic will need to change for this but you should try to make 
     * the least amount of changes possible
     
    @Test
    public void BvsRsunny() {
        // One Student
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        
        //Create First battle scenario with two mons
        BattleScenario fight1 = new BattleScenario(attacker1, defender1); 
        //Set the weather
        fight1.setEnvironment(Environment.Weather.sunny);
        
        Mascotmon mon = fight1.fight();
        assertEquals(mon, attacker1);
    }
    */

     /**
     * This would test route 57 -> 65 -> 79 (false) -> 97 (false) -> 65 -> 79 (true) -> 103 
     * This would be were second mon faints 
     * This provides full node coverage
     */
    @Test
    public void nodeCoverage() {
        
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        
        //Create First battle scenario with two mons
        BattleScenario fight1 = new BattleScenario(attacker1, defender1); 
        //Set the weather
        fight1.setEnvironment(Environment.Weather.sunny);
        
        Mascotmon mon = fight1.fight(2, 2, false);
        assertEquals(mon, attacker1);
    }

     /**
     * This would test route 57 -> 65 -> 79 (false) -> 97 (false) -> 65 -> 79 (true) -> 103 
     * This would be were first mon faints 
     */
    @Test
    public void edgeCoverageFirst() {
        
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);
        
        //Create First battle scenario with two mons
        BattleScenario fight1 = new BattleScenario(attacker1, defender1); 
        //Set the weather
        fight1.setEnvironment(Environment.Weather.sunny);
        
        Mascotmon mon = fight1.fight(2, 2, false);
        assertEquals(mon, defender1);
    }

    /**
     * Adding Additional Tests to meet coverage requirements for calculateDamageTest
     */
    @Test
    public void albertVSSparkySuny() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    albertVSSparkySunny");
        fight3.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 38, 0.2);
    }
    @Test
    public void albertVSSparkyRain() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    albertVSSparkyRain");
        fight3.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 103, 0.2);
    }

    @Test
    public void albertVSRALPHIE() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    albertVSRALPHIE");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 1, 0.2);
    }

    @Test
    public void albertVSBully() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    albertVSBully");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 50, 0.2);
    }

    @Test
    public void ralphieVSSparky() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    ralphieVSSparky");
        fight3.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 23, 0.2);
    }

    @Test
    public void ralphieVSALBERT() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    ralphieVSALBERT");
        fight3.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 63, 0.2);
    }

    @Test
    public void ralphieVSBully() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    ralphieVSBully");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 70, 0.2);
    }

    @Test
    public void sparkyVSALBERTSunny() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    sparkyVSALBERTSunny");
        fight3.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 38, 0.2);
    }

    @Test
    public void sparkyVSALBERTRainy() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    sparkyVSALBERTrainy");
        fight3.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 1, 0.2);
    }

    @Test
    public void sparkyVSRalphie() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    sparkyVSRalphie");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 39, 0.2);
    }

    @Test
    public void bullyVSRALPHIE() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    bullyVSRALPHIE");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Normal");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 1, 0.2);
    }

    @Test
    public void bullyVSSPARKY() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    bullyVSSPARKY");
        fight3.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(80, "Fire");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 30, 0.2);
    }
    
    @Test
    public void bullyVSRALPHIEDrought() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    bullyVSRALPHIEdrought");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Fire");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 1, 0.2);
    }

    @Test
    public void bullyVSALBERTRAINY() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    bullyVSAlbertRainy");
        fight3.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(80, "Fire");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 30, 0.2);
    }

    
    @Test
    public void bullyVSALBERTSunny() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    bullyVSAlbertSunny");
        fight3.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(80, "Fire");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 50, 0.2);
    }

    
    @Test
    public void bullyVSALBERTDrought() throws Exception {
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);
        
        BattleScenario fight3 = new BattleScenario(attacker1, defender1);
        System.out.println("    bullyVSAlbertDrought");
        fight3.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(80, "Fire");

        double damage = fight3.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        assertEquals(damage, 20, 0.2);
    }
}
