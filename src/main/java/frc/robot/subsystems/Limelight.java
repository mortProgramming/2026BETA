package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.LimeLightHelpers.PoseEstimate;

public class Limelight extends SubsystemBase {
    private final String name;
    private final NetworkTable telemetryTable;
    private final StructPublisher<Pose2d> posePublisher;

    public Limelight(String name) {
        this.name = name;
        this.telemetryTable = NetworkTableInstance.getDefault().getTable("SmartDashboard/" + name);
        this.posePublisher = telemetryTable.getStructTopic("Estimated Robot Pose", Pose2d.struct).publish();
    }

    public Optional<Measurement> getMeasurement(Pose2d currentRobotPose) {
        // Always update robot orientation before fetching pose estimates
        // so MegaTag2 has the correct yaw when computing position
        LimeLightHelpers.SetRobotOrientation(
            name,
            currentRobotPose.getRotation().getDegrees(),
            0, 0, 0, 0, 0
        );

        final PoseEstimate poseEstimate_MegaTag1 = LimeLightHelpers.getBotPoseEstimate_wpiBlue(name);
        final PoseEstimate poseEstimate_MegaTag2 = LimeLightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(name);

        // Bail out early if either estimate is missing or sees no tags
        if (poseEstimate_MegaTag1 == null
                || poseEstimate_MegaTag2 == null
                || poseEstimate_MegaTag1.tagCount == 0
                || poseEstimate_MegaTag2.tagCount == 0) {
            return Optional.empty();
        }

        // Reject poses that are wildly far from the current known pose —
        // helps filter out bad detections caused by tag ambiguity
        double distanceFromCurrent = poseEstimate_MegaTag2.pose
            .getTranslation()
            .getDistance(currentRobotPose.getTranslation());
        if (distanceFromCurrent > 1.5) {
            return Optional.empty();
        }

        final boolean isAuton = DriverStation.isAutonomous();

        final PoseEstimate primaryEstimate;
        final Matrix<N3, N1> standardDeviations;

        if (isAuton) {
            // During auton, use MegaTag1 (full 6DOF solve) since MegaTag2's
            // gyro-assisted translation is unreliable until the gyro is well-calibrated.
            // Rotation std dev is set very high so the estimator ignores vision rotation
            // and trusts the gyro exclusively.
            primaryEstimate = poseEstimate_MegaTag1;
            standardDeviations = VecBuilder.fill(0.5, 0.5, 9999.0);
        } else {
            // During teleop, combine the stable translation from MegaTag2 with
            // the rotation from MegaTag1 to counteract gyro drift.
            poseEstimate_MegaTag2.pose = new Pose2d(
                poseEstimate_MegaTag2.pose.getTranslation(),
                poseEstimate_MegaTag1.pose.getRotation()
            );
            primaryEstimate = poseEstimate_MegaTag2;
            standardDeviations = VecBuilder.fill(0.1, 0.1, 10.0);
        }

        posePublisher.set(primaryEstimate.pose);

        // Push telemetry for debugging
        SmartDashboard.putNumber(name + "/Tag Count", primaryEstimate.tagCount);
        SmartDashboard.putNumber(name + "/Avg Tag Dist", primaryEstimate.avgTagDist);
        SmartDashboard.putNumber(name + "/Pose X", primaryEstimate.pose.getX());
        SmartDashboard.putNumber(name + "/Pose Y", primaryEstimate.pose.getY());

        return Optional.of(new Measurement(primaryEstimate, standardDeviations));
    }

    /** Returns the raw TX (horizontal offset) from the limelight in degrees */
    public double getTX() {
        return LimeLightHelpers.getTX(name);
    }

    /** Returns the raw TY (vertical offset) from the limelight in degrees */
    public double getTY() {
        return LimeLightHelpers.getTY(name);
    }

    /** Returns true if the limelight currently sees a valid target */
    public boolean hasTarget() {
        return LimeLightHelpers.getTV(name);
    }

    /** Returns the ID of the currently tracked AprilTag, or -1 if none */
    public int getTagId() {
        return (int) LimeLightHelpers.getFiducialID(name);
    }

    public static class Measurement {
        public final PoseEstimate poseEstimate;
        public final Matrix<N3, N1> standardDeviations;

        public Measurement(PoseEstimate poseEstimate, Matrix<N3, N1> standardDeviations) {
            this.poseEstimate = poseEstimate;
            this.standardDeviations = standardDeviations;
        }
    }
}