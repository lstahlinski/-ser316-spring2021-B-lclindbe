public class Stats {

    double attack;
    double defense;
    double health;

    public Stats() {
        attack = 50;
        defense = 50;
        health = 100;
    }

    public Stats(Mascotmon.Name name) {
        if (name == Mascotmon.Name.ALBERT){
            attack = 60;
            defense = 40;
            health = 100;
        }
        else if (name == Mascotmon.Name.RALPHIE){
            attack = 30;
            defense = 65;
            health = 105;
        }
        else if (name == Mascotmon.Name.SPARKY){
            attack = 70;
            defense = 40;
            health = 90;
        }
        else if (name == Mascotmon.Name.SPARKY){
            attack = 40;
            defense = 40;
            health = 110;
        }
        else {
            attack = 50;
            defense = 50;
            health = 100;
        }
    }
}
