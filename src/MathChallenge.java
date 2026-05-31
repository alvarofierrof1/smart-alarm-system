package src;

import java.util.Random;

public class MathChallenge {
    private int correctAnswer;
    private String challengeExpression;

    public void generateChallenge() {
        Random random = new Random();
        int num1 = random.nextInt(10) + 2; // Número entre 2 y 11
        int num2 = random.nextInt(9) + 2;  // Número entre 2 y 10
        int num3 = random.nextInt(15) + 5; // Número entre 5 y 19
        
        // Creamos una operación combinada simple: (A * B) + C
        this.correctAnswer = (num1 * num2) + num3;
        this.challengeExpression = "¿Cuánto es " + num1 + " * " + num2 + " + " + num3 + "?";
    }

    public String getChallengeExpression() {
        return challengeExpression;
    }

    public boolean verifyAnswer(int userAnswer) {
        if (userAnswer == correctAnswer) {
            System.out.println("✅ [RETO SUPERADO]: ¡Respuesta correcta! Desactivando sonido de alarma.");
            return true;
        } else {
            System.out.println("❌ [RETO INCORRECTO]: Respuesta errónea. La alarma sigue sonando.");
            return false;
        }
    }
}