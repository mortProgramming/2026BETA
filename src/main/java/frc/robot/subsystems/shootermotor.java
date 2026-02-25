package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;

public class ShooterMotor extends SubsystemBase {
    public static ShooterMotor shootermotor; 
    private SparkFlex shooter;
    public SparkFlex shooterFollower;
    public SparkFlexConfig shooterFollowerConfig;
    private SparkFlexConfig shooterConfig; // Defining variables  


    public static CANcoder m_backRightLocation;
    public static CANcoder m_backLeftLocation;
    public static CANcoder m_frontRightLocation; // Odometry Stuff
    public static CANcoder m_frontLeftLocation;

    public static Module m_frontLeftModule;
    public static Module m_backLeftModule;
    public static Module m_frontRightModule;
    public static Module m_backRightModule;

    public static Pigeon2 m_gyro;
    
    private ShooterMotor() {
        shooter = new SparkFlex(17, MotorType.kBrushless);    // SparkFlex Creation
        shooterConfig = new SparkFlexConfig();     
        shooter.configure(shooterConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
                 
        shooterFollower= new SparkFlex(18, MotorType.kBrushless);
        shooterFollowerConfig = new SparkFlexConfig();
        shooterFollower.configure(shooterFollowerConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);

         shooterFollowerConfig.follow(17, true);
    }
        public void setSpeed(double speed){
            shooter.set(speed);
        }

        public void stop() {
            shooter.set(0);
        }

        public static ShooterMotor getInstance() {
            if(shootermotor==null) 
                shootermotor = new ShooterMotor();
            return shootermotor;
        }
    }

//         //v₀ = √[(33.37·x²)/(1.036x - 4)] 
//         //Shooter Formula


//         // Locations for the swerve drive modules relative to the robot center.
// Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
// Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
// Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
// Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);

// // // Creating my kinematics object using the module locations
//  SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
//    m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
//  );
//   //Creating my odometry object from the kinematics object and the initial wheel positions.
// // // Here, our starting pose is 5 meters along the long end of the field and in the
// // // center of the field along the short end, facing the opposing alliance wall.
// SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(
//   m_kinematics, m_gyro.getRotation2d(),
//     new SwerveModulePosition[] {
//     m_frontLeftModule.getPosition(),
//     m_frontRightModule.getPosition(),
//     m_backLeftModule.getPosition(),
//     m_backRightModule.getPosition()
// }, new Pose2d(5.0, 13.5, new Rotation2d()));
//  @Override
//          public double getPosition(){
//             return position;
//         }
//         public
//     public void periodic() {
// //  Get the rotation of the robot from the gyro.
//   Rotation2d gyroAngle = m_gyro.getRotation2d();
// //   // Update the pose
//  m_pose = m_odometry.update(gyroAngle,
//     new SwerveModulePosition[] {
//      position = m_frontLeftModule.getPosition(), m_frontRightModule.getPosition(),
//     m_backLeftModule.getPosition(), m_backRightModule.getPosition()
//    })
//         public double getPosition() {
//             return position;
//         }
//         public void updatePosition(){
//             position=.getEncoder().getPosition();
//         }
// }


