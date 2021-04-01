public class Type {

    public String type;
    
    /**
     * Constructor.
     */
    public Type() {
        type = "Normal";
    }
    /**
     * Describe type of monsters. 
     */
    public Type(Mascotmon.Name name) {
        String n = name.toString();
        if (n.equals("ALBERT")) {
            type = "Water";
        } else if (n.equals("RALPHIE")) {
            type ="Ground";

        } else if (n.equals("SPARKY")) {
            type = "Fire";
        } else {
            type = "Normal";
        }
    }

}
