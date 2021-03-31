public class Type {

    String type;

    public Type() {
        type = "Normal";
    }

    /**
     * Describe type of monsters. 
     */
    public Type(Mascotmon.Name name) {
        if (name.equals(Mascotmon.Name.ALBERT)) {
            type = "Water";
        } else if (name.equals(Mascotmon.Name.RALPHIE)) {
            type = "Ground";

        } else if (name.equals(Mascotmon.Name.SPARKY)) {
            type = "Fire";
        } else {
            type = "Normal";
        }
    }
}
