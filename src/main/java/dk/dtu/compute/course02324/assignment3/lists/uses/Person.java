package dk.dtu.compute.course02324.assignment3.lists.uses;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Person implements Comparable<Person> {

    final public String name;

    public double weight;

    private Integer age;

    Person(@NotNull String name, @NotNull double weight, @NotNull int age) {
        if (name == null || weight <= 0 || age <= 0) {
            throw new IllegalArgumentException("A persons must be initialized with a" +
                    "(non null) name and weight and age greater than 0");
        }
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        if (o == null) {
            throw new IllegalArgumentException("Argument of compareTo() must not be null");
        }

        int nameComparison = this.name.compareTo(o.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Double.compare(this.weight, o.weight);
    }

    /**
     * Computes a simple string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        // This could be automatically generated, but this automatically
        // generated representation is a bit too verbose. Therefore, we
        // chose a simpler representation here.
        return name + ", " + age + " years, " + weight + "kg";
    }

    /*
     * The following two methods must be implemented in order to respect the contract of objects
     * with respect to equals(), hashCode() and compareTo() methods. The details and reasons
     * as to why will be discussed much later.
     *
     * The implementations can be done fully automatically my IntelliJ (using the two attributes
     * of person).
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Double.compare(person.weight, weight) == 0 && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDead() {
        return this.age > 99;
    }

}
