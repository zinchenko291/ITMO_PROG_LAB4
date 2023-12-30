package Characters;

import Annotations.ValidLength;
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

  @ValidLength
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
    this.validateFields();
  }

  public final String getState() {
    return this.currentState;
  }

  protected final void setState(String state) {
    this.currentState = state;
    this.validateFields();
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

  private void validateFields() {
    for (java.lang.reflect.Field field : Character.class.getDeclaredFields()) {
      if (field.isAnnotationPresent(ValidLength.class)) {
        String value = getValueForField(this, field);
        if (value.length() < 3 || value.length() > 20) {
          setValueForField(this, field, "unknown");
        }
      }
    }
  }

  private static String getValueForField(Object obj, java.lang.reflect.Field field) {
    try {
      field.setAccessible(true);
      return (String) field.get(obj);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void setValueForField(Object obj, java.lang.reflect.Field field, String value) {
    try {
      field.setAccessible(true);
      field.set(obj, value);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
