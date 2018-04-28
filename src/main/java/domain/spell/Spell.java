package domain.spell;

/**
 * Created by David on 4/28/2018.
 */
public abstract class Spell {

    private int _id;
    private String _name;
    private SpellAlignment _alignment;
    private SpellType _type;

    public void setId(int id){
        _id = id;
    }

    public int getId(){
        return _id;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public SpellAlignment getAlignment(){
        return _alignment;
    }

    public void setAlignment(SpellAlignment alignment){
        _alignment = alignment;
    }

    public SpellType getType(){
        return _type;
    }

    public void setType(SpellType type){
        _type = type;
    }

    public void castOn(SpellTarget target){
        target.handleCastedSpell(this);
    }
}
