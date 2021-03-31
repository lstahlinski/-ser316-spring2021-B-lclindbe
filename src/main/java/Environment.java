public class Environment {

    Weather weather1;
    String buffedType;
    String debuffedType;
    double buffModifier;
    double debuffModifier;

    public Environment() {
        this(Weather.neutral);
    }

    /**
     * Weather Debuff. 
     */
    public Environment(Weather weather) {
        this.weather1 = weather;
        this.buffModifier = 1.25;
        this.debuffModifier = 0.75;
        switch (weather) {
            case sunny:
                this.buffedType = "Fire";
                this.debuffedType = "Water";
                break;
            case rainy:
                this.buffedType = "Water";
                this.debuffedType = "Fire";
                break;
            case drought:
                this.buffedType = "Ground";
                this.debuffedType = "Normal";
                break;
            default:
                this.buffedType = "";
                this.debuffedType = "";
                break;
        }
    }

    public String getBuffedType() {
        return buffedType;
    }

    public String getDebuffedType() {
        return debuffedType;
    }

    public enum Weather {
        sunny, rainy, drought, neutral
    }
}
