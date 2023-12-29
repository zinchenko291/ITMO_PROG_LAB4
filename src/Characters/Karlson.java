package Characters;

import Entity.Entity;
import Items.Container;
import Items.Item;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;

public class Karlson extends Character implements IGrabbable, IFlyable {
  private final Engine engine = new Engine();

  public Karlson() {
    super("Кралсон", Gender.MAN);
  }

  public Karlson(Feels feel) {
    this();
    this.feel = feel;
  }

  private static class Engine {
    private int fuel = 13;

    public Engine() {}
    public boolean canFly() {
      return fuel > 0;
    }

    public void startFly() {
      this.fuel -= this.countMinPath();
      if (fuel < 0) {
        throw new ExitFuelException("У двигателя кончилось топливо");
      }
    }

    public int countMinPath() {
      interface IntFormula {
        int getResult(int x);
        int getAbs(int x);
      }
      class Path {
        private final int distance;
        private final IntFormula formula;

        Path (int distance, IntFormula formula) {
          this.distance = distance;
          this.formula = formula;
        }

        public int getResult() {
          return formula.getAbs(distance);
        }
      }

      Path[] paths = {
        new Path(4, new IntFormula() {
          @Override
          public int getResult(int x) {
            return x*3;
          }

          @Override
          public int getAbs(int x) {
            return Math.abs(getResult(x));
          }
        }),
        new Path(7, new IntFormula() {
          @Override
          public int getResult(int x) {
            return x*6;
          }

          @Override
          public int getAbs(int x) {
            return Math.abs(getResult(x));
          }
        }),
        new Path(1, new IntFormula() {
          @Override
          public int getResult(int x) {
            return x*x-2;
          }

          @Override
          public int getAbs(int x) {
            return Math.abs(getResult(x));
          }
        })
      };

      int minPath = Integer.MAX_VALUE;
      for (Path path: paths) {
        minPath = Math.min(path.getResult(), minPath);
      }

      return minPath;
    }
  }

  @Override
  public void moveTo(Entity obj) {
    this.setPosition(obj);
    this.setState(String.format("%s сделал шаг к %s", this.getGender(), obj));
  }

  public void flyTo(Entity obj) {
    if (engine.canFly()) {
      this.moveTo(obj);
      this.engine.startFly();
      this.setState(String.format("%s подлетел к %s", this.getGender(), obj));
      return;
    }
    this.setState("%s пытался взлететь, но у него не получилось");
  }

  @Override
  public void grab(Item obj) throws OwnershipException {
    this.itemInHand = obj;
    this.setState(String.format("%s взял %s", this.getGender(), obj));
    if (obj.getOwner() != this && obj.getOwner() != null) {
      throw new OwnershipException(String.format("Предмет %s принадлежит %s, а не %s", obj.getName(), obj.getOwner().getName(), this.getName()));
    }
  }

  public void grabFrom(Container container) throws OwnershipException {
    this.grab(container.getInnerItem());
    this.setState(String.format("%s взял одну %s из %s", this.getName(), container.getInnerItem(), container));
  }

  @Override
  public String getGrabbablePart() {
    return "шиворот";
  }

  public void stomp() {
    this.setState(String.format("%s топнул ногой", this.getName()));
  }
}
