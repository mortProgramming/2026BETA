package frc.robot.Constants;
public final class PIDConstants{
    public static final class IntakeArmConstants{
        public static final double kP = 0.05; //first increase, will be the largest value
        public static final double kI = 0.0; //stays close to zero
        public static final double kD = 0.0; //If spas out increase kD
    }
    public static final class ClimberConstants {
        public static final double kP2 = 0.05; //first increase, will be the largest value
        public static final double kI2 = 0.0; //stays close to zero
        public static final double kD2 = 0.0; //If spas out increase kD

    }
}