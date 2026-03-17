package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static edu.wpi.first.units.Units.*;


public class ShooterMotor extends SubsystemBase {
    public double RPM;
    private SparkClosedLoopController ClosedLoopController;
    private SimpleMotorFeedforward feedforward;
    private PIDController pid;
    public static ShooterMotor shootermotor; 
    private SparkFlex shooter;
    public SparkFlex shooterFollower;
    public SparkFlexConfig shooterFollowerConfig;
    private SparkFlexConfig shooterConfig; // Defining variables  
    private MedianFilter RPMAverager; 
    private static final double ROBOT_VOLTAGE = 12; // Assuming a 12V system

    public double speed;
    public static CANcoder m_backRightLocation;
    public static CANcoder m_backLeftLocation;
    public static CANcoder m_frontRightLocation; // Odometry Stuff
    public static CANcoder m_frontLeftLocation;

    public static Module m_frontLeftModule;
    public static Module m_backLeftModule;
    public static Module m_frontRightModule;
    public static Module m_backRightModule;

    public static SparkRelativeEncoder motorencoder;

    public static Pigeon2 m_gyro;
    
    private ShooterMotor() {
        shooter = new SparkFlex(17, MotorType.kBrushless);    // SparkFlex Creation
        shooterConfig = new SparkFlexConfig(); 

        // shooterConfig.closedLoop.feedForward
        //     .kS(PIDConstants.ShooterMotorConstants.kS)
        //     .kV(PIDConstants.ShooterMotorConstants.kV)
        //     .kA(PIDConstants.ShooterMotorConstants.kA);

        shooter.configure(shooterConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
                 
        shooterFollower= new SparkFlex(18, MotorType.kBrushless);
        shooterFollowerConfig = new SparkFlexConfig();

        shooterFollowerConfig.follow(17, true);

        shooterFollower.configure(shooterFollowerConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);

        // feedforward = new SimpleMotorFeedforward(frc.robot.Constants.PIDConstants.ShooterMotorConstants.kS, frc.robot.Constants.PIDConstants.ShooterMotorConstants.kV, frc.robot.Constants.PIDConstants.ShooterMotorConstants.kA);
        // slewLimiter = new SlewRateLimiter(frc.robot.Constants.PIDConstants.ShooterMotorConstants.SlewRateLimiter);
         RPMAverager = new MedianFilter(35);
        ClosedLoopController=shooter.getClosedLoopController();
        // speed = (RPM/6784);
        speed = 0;

        feedforward = new SimpleMotorFeedforward(PIDConstants.ShooterMotorConstants.kS, PIDConstants.ShooterMotorConstants.kV);
        pid = new PIDController(PIDConstants.ShooterMotorConstants.kP, PIDConstants.ShooterMotorConstants.kI, PIDConstants.ShooterMotorConstants.kD);
    }
    
    //     public double slewLimitedSpeed(double shooterSpeed) {
    //     return slewLimiter.calculate(shooterSpeed);
    // } 

    //    private SlewRateLimiter slewLimiter = new SlewRateLimiter(0.5); // Adjust the rate limit as needed

//        private static final double SHOOTER_SPEED_BUZZ_TOLERANCE = 0.01; // 1% tolerance
        
       public void setSpeed(double RPM){
           shooter.setVoltage((speed) * 12);
       }
 
        // public void setShooterSpeedRPM(double RPM) {
        //     ClosedLoopController.setSetpoint(RPM, ControlType.kVelocity);
        // }

        public void setShooterSpeedRPM(double RPM) {
            shooter.setVoltage( 12 * (
                RPM / 6784 +
                feedforward.calculate(RPM) +
                pid.calculate(getSpeedRPM(), RPM)
            ));
        }


    // Bum code

    //     public boolean isAtTargetRPM(double targetRPM) {
    //         if (targetRPM == 0) return true;
    //         return Math.abs(getShooterRPM() - targetRPM) / targetRPM < SHOOTER_SPEED_BUZZ_TOLERANCE;
    //     }   
        
    //     public void setShooterRPM(double RPM) {
    //     shooterSpeed = (RPM / MAX_SHOOTER_RPM) + 
    //     (feedforward.calculate(RPM) / ROBOT_VOLTAGE);
    // }

   
       public void stop() {
            shooter.set(0);
       }

      public static ShooterMotor getInstance() {
                  if(shootermotor==null) 
                shootermotor = new ShooterMotor();
             return shootermotor;
       }
         @Override
       public void periodic() {
        //  shooter.setVoltage(slewLimitedSpeed(PIDConstants.ShooterMotorConstants.SlewRateLimiter) * ROBOT_VOLTAGE);
            shootermotor.setSpeed(RPM);
            // shooter.setVoltage();
        SmartDashboard.putNumber("Shooter Speed RPM", RPMAverager.calculate(getSpeedRPM()));
       }

       public double getSpeedRPM() {
        return shooter.getEncoder().getVelocity();
       }
    
    }

// //         //v₀ = √[(33.37·x²)/(1.036x - 4)] 
// //         //Shooter Formula


// //         // Locations for the swerve drive modules relative to the robot center.
// // Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
// // Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
// // Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
// // Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);

// // // // Creating my kinematics object using the module locations
// //  SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
// //    m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
// //  );
// //   //Creating my odometry object from the kinematics object and the initial wheel positions.
// // // // Here, our starting pose is 5 meters along the long end of the field and in the
// // // // center of the field along the short end, facing the opposing alliance wall.
// // SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(
// //   m_kinematics, m_gyro.getRotation2d(),
// //     new SwerveModulePosition[] {
// //     m_frontLeftModule.getPosition(),
// //     m_frontRightModule.getPosition(),
// //     m_backLeftModule.getPosition(),
// //     m_backRightModule.getPosition()
// // }, new Pose2d(5.0, 13.5, new Rotation2d()));
// //  @Override
// //          public double getPosition(){
// //             return position;
// //         }
// //         public
// //     public void periodic() {
// // //  Get the rotation of the robot from the gyro.
// //   Rotation2d gyroAngle = m_gyro.getRotation2d();
// // //   // Update the pose
// //  m_pose = m_odometry.update(gyroAngle,
// //     new SwerveModulePosition[] {
// //      position = m_frontLeftModule.getPosition(), m_frontRightModule.getPosition(),
// //     m_backLeftModule.getPosition(), m_backRightModule.getPosition()
// //    })
// //         public double getPosition() {
// //             return position;
// //         }
// //         public void updatePosition(){
// //             position=.getEncoder().getPosition();
// //         }
// // }


