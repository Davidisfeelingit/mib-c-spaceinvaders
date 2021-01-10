package mib.c.SpaceInvaders;

public class Character {
    private int character = 0;
    final private String [] sources = {"src/images/player.png", "src/images/1.png", "src/images/2.png", "src/images/3.png", "src/images/4.png", "src/images/5.png", "src/images/6.png", "src/images/7.png", "src/images/.png"};

    public Character(){}

    public void chooseCharacter(int character){
        this.character = Math.floorMod(character, sources.length);
    }
    public String getChaSource(){
        return sources[character];
    }
    public int getChosenCharacter(){
        return character;
    }
}
