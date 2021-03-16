public class Environment {

    Weather WEATHER;
    String buffedType;
    String DebuffedType;
    double buffModifier;
    double debuffModifier;

    public Environment(){
        this(Weather.neutral);
    }

    public Environment(Weather weather) {
        this.WEATHER = weather;
        this.buffModifier = 1.25;
        this.debuffModifier = 0.75;
        switch (weather){
            case sunny:
               this.buffedType = "Fire";
               this.DebuffedType = "Water";
               break;
            case rainy:
                this.buffedType = "Water";
                this.DebuffedType = "Fire";
                break;
            case drought:
                this.buffedType = "Ground";
                this.DebuffedType = "Normal";
                break;
            default:
                this.buffedType = "";
                this.DebuffedType = "";
                break;
        }
    }

    public String GetBuffedType(){
        return buffedType;
    }

    public String getDebuffedType(){
        return DebuffedType;
    }

    public enum Weather {
        sunny, rainy, drought, neutral
    }
}
