package Characters;

import Entity.Entity;
import Items.Item;

import java.util.Objects;

public abstract class Character extends Entity implements IMovable, IFeelable, ICanTake {

  public enum Gender {
    MAN("Он"),
    WOMAN("Она");

    private final String gender;

    Gender(String gender) {
      this.gender = gender;
    }

    @Override
    public String toString() {
      return gender;
    }
  }

  private final Gender gender;
  private Entity position;
  private String currentState = "";
  protected Feels feel;
  protected Item itemInHand;

  public Character(String name, Gender gender) {
    this(name, gender, Feels.NORMAL);
  }

  public Character(String name, Gender gender, Feels feel) {
    this.name = name;
    this.gender = gender;
    this.feel = feel;
  }

  public final String getGender() {
    return this.gender.toString();
  }

  public final void setFeel(Feels feel) {
    this.feel = feel;
    this.setState(String.format("%s %s", this.getName(), feel));
  }

  public abstract void moveTo(Entity obj);

  public abstract void grab(Item obj) throws OwnershipException;

  public final Item getItemInHand() {
    return this.itemInHand;
  }

  public final Entity getPosition() {
    return this.position;
  }

  protected final void setPosition(Entity obj) {
    this.position = obj;
  }

  public final String getState() {
    return this.currentState;
  }

  protected final void setState(String state) {
    this.currentState = state;
  }

  @Override
  public String toString() {
    return this.getName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Character character = (Character) o;
    return Objects.equals(this.name, character.name) && this.gender == character.gender;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.gender);
  }
}
