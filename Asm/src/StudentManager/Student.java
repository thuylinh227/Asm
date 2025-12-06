/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManager;

/**
 *
 * @author Admin
 */
public class Student {
    private String id;
    private String name;
    private double score;

    public Student(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getScore() { return score; }

    public void setName(String name) { this.name = name; }
    public void setScore(double score) { this.score = score; }
    
    public String getRank() {
        if (score >= 8) return "Excellent";
        if (score >= 6.5) return "Good";
        if (score >= 5) return "Average";
        return "Weak";
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Score: %.2f | Rank: %s",
                id, name, score, getRank());
    }
}
