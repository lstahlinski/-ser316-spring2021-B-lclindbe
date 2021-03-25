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

}
