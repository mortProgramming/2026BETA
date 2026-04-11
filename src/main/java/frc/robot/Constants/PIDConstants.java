package frc.robot.Constants;
public final class PIDConstants{
    public static final class IntakeArmConstants{
        public static final double kP = 0.05; //first increase, will be the largest value
        public static final double kI = 0.0; //stays close to zero
        public static final double kD = 0.0; //If spas out increase kD
        public static final double kG = 0.3;
    }
    public static final class ClimberConstants {
        public static final double kP2 = 0.05; //first increase, will be the largest value
        public static final double kI2 = 0.0; //stays close to zero
        public static final double kD2 = 0.0; //If spas out increase kD

    }
    public static final class ShooterMotorConstants {
        // public static final double kP = 0.1; //first increase, will be the largest value
        // public static final double kI = 0.0; //stays close to zero
        // public static final double kD = 0.0; //If spas out increase kD
        // public static final double kS = 0.2; // Static gain, adjust based on your system's characteristics
        // public static final double kV = 0.1; // Velocity gain, adjust based
        // public static final double kA = 0.01; // Acceleration gain, adjust based on your system's characteristics
        // public static final double SlewRateLimiter = 0.5; // Adjust the rate limit as needed

    //   //  dan
    //      public static final double kP = 0.00001; //first increase, will be the largest value
    //      public static final double kI = 0.0000; //stays close to zero
    //      public static final double kD = 0.00001; //If spas out increase kD
    //      public static final double kS = 0.2 /12; // Static gain, adjust based on your system's characteristics
    //      public static final double kV = 0.00005 / 12; // Velocity gain, adjust based
    //      public static final double kA = 0; // Acceleration gain, adjust based on your system's characteristics
    //      public static final double SlewRateLimiter = 0.5; // Adjust the rate limit as needed

        //evan things today
        public static final double kP = 0.0004; //first increase, will be the largest value
        public static final double kI = 0; //stays close to zero
        public static final double kD = 0.000; //If spas out increase kD
        public static final double kS = 0.085 / 12; // Static gain, adjust based on your system's characteristics
        public static final double kV = 0.000025 / 12; // Velocity gain, adjust based
        public static final double kA = 0; // Acceleration gain, adjust based on your system's characteristics
        public static final double SlewRateLimiter = 0.5; // Adjust the rate limit as needed

        //Kv is difference in rpm/desired rpm.  
  }
}
