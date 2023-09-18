package garden_sim;

public class Healthbar {

    private final int maxHealth;
    private int currentHealth;
    private double healthPercent;

    public Healthbar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    /**
     * Updates the health by the amount passed. (to decrease health pass a negative number)
     * Will not increase health above maxHealth
     * Will not decrease health below 0
     * @param changeInHealth the amount to change the health by
     */
    public void update(double changeInHealth) {
        if (currentHealth+changeInHealth <= 0) {
            currentHealth = 0;
        } else if (currentHealth+changeInHealth >= maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth+=changeInHealth;
        }
        //System.out.println("Health: " + currentHealth);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }


}
