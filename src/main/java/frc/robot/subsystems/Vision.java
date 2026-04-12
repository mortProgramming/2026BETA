package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveDrivetrain.SwerveDriveState;

import frc.robot.subsystems.CommandSwerveDrivetrain;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    private static Vision instance;
    private HttpCamera limelightOneFeed;

    private AprilTagFieldLayout fieldLayout;

    private NetworkTable cameraTableOne;

    private static final String[] LIMELIGHTS = {
        "limelight-one",
    };

    public static String[] getLimelights() {
        return LIMELIGHTS;
    }

    public Vision() {
        fieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltAndymark);

        cameraTableOne = NetworkTableInstance.getDefault().getTable("limelight-one");

        limelightOneFeed = new HttpCamera("limelight-one", "http://limelight-one.local:5801/stream.mjpeg");

        CameraServer.addCamera(limelightOneFeed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Tag ID", getTagId());
        SmartDashboard.putNumber("X Degrees", getTX());
        SmartDashboard.putBoolean("Tag Detected?", hasTag());
    }

    // ---------- Camera / Limelight Methods ----------

    public boolean hasTag() {
        return cameraTableOne.getEntry("tv").getDouble(0) == 1;
    }

    public int getTagId() {
        if (cameraTableOne.getEntry("tv").getDouble(0) == 1) {
            return (int) cameraTableOne.getEntry("tid").getInteger(-1);
        }
        return -1;
    }

    public double getTX() {
        if (cameraTableOne.getEntry("tv").getDouble(0) == 1) {
            return cameraTableOne.getEntry("tx").getDouble(0);
        }
        return 0;
    }

    public double getTY() {
        if (cameraTableOne.getEntry("tv").getDouble(0) == 1) {
            return cameraTableOne.getEntry("ty").getDouble(0);
        }
        return 0;
    }

    // FIX: was incorrectly reading "ty" instead of "ta"
    public double getTA() {
        if (cameraTableOne.getEntry("tv").getDouble(0) == 1) {
            return cameraTableOne.getEntry("ta").getDouble(0);
        }
        return 0;
    }

    public double[] getCameraPosition() {
        return new double[] {
            getTX(),
            getTY(),
            getTA()
        };
    }

    private NetworkTable getFirstActiveTable() {
        if (cameraTableOne.getEntry("tv").getDouble(0) == 1) return cameraTableOne;
        return cameraTableOne; // default fallback
    }

    public Pose2d getRobotPosition() {
        NetworkTable table = getFirstActiveTable();
        double[] poseNums = table.getEntry("botpose_orb_wpiblue").getDoubleArray(new double[6]);
        return new Pose2d(
            poseNums[0],
            poseNums[1],
            new Rotation2d(Math.toRadians(poseNums[4]))
        );
    }

    public Pose2d getRelativeRobotPosition() {
        NetworkTable table = getFirstActiveTable();
        double[] poseNums = table.getEntry("camerapose_targetspace").getDoubleArray(new double[6]);
        return new Pose2d(
            poseNums[0],
            poseNums[2],
            new Rotation2d(Math.toRadians(poseNums[4]))
        );
    }

    public Pose3d get3dRobotPosition() {
        NetworkTable table = getFirstActiveTable();
        double[] poseNums = table.getEntry("botpose_orb_wpiblue").getDoubleArray(new double[6]);
        return new Pose3d(
            new Translation3d(poseNums[0], poseNums[1], poseNums[2]),
            new Rotation3d(
                Math.toRadians(poseNums[3]),
                Math.toRadians(poseNums[4]),
                Math.toRadians(poseNums[5])
            )
        );
    }

    public Pose2d getFieldTagPose(int tagId) {
        return fieldLayout.getTagPose(tagId).get().toPose2d();
    }

    public void setLEDMode(int mode) {
        cameraTableOne.getEntry("ledMode").setNumber(mode);
    }

    public void setRobotOrientation(double yaw, double yawRate) {
        double[] orientation = {yaw, yawRate, 0, 0, 0, 0};
        cameraTableOne.getEntry("robot_orientation_set").setDoubleArray(orientation);
    }

    public double[] getPicturePosition() {
        return new double[]{0.0, 0.0, 0.0};
    }

    // ---------------- MEGATAG2 SUPPORT ----------------

    public static class VisionMeasurement {
        public Pose2d pose;
        public double timestamp;
        public int tagCount;
        public double avgTagDist;
    }

    public static void updateRobotOrientation(CommandSwerveDrivetrain drivetrain) {
        // Use raw Pigeon 2 yaw directly — do NOT use getPose().getRotation() here
        // because that has the operator perspective offset baked in, which would
        // corrupt MegaTag2 on Red alliance.
        double yaw = drivetrain.getPigeon2().getYaw().getValueAsDouble();
        double yawRate = drivetrain.getPigeon2().getAngularVelocityZWorld().getValueAsDouble();

        for (String name : LIMELIGHTS) {
            LimeLightHelpers.SetRobotOrientation(name, yaw, yawRate, 0.0, 0.0, 0.0, 0.0);
        }
    }

    public static Vision getInstance() {
        if (instance == null) {
            instance = new Vision();
        }
        return instance;
    }
}