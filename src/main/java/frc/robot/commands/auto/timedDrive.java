package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;

public class timedDrive extends Command {
        public CommandSwerveDrivetrain drivetrain;
        public double m_time;
        public double xSpeed;
        public double ySpeed;
        public double omegaSpeed;
        public Timer timer;
        
public timedDrive(double x, double y, double omega, double time) {
        xSpeed=x;
        ySpeed=y;
        omegaSpeed=omega;
        m_time = time;
        drivetrain = RobotContainer.drivetrain;
        addRequirements(drivetrain);
    }
    public void initialize() {
        timer.reset();
        timer.start();
    }
    public void execute() {
        drivetrain.driveRelative(xSpeed, ySpeed, omegaSpeed);
    }
    public void end(boolean interrupted) {
        drivetrain.stop();
        //This might not be neccesary
        timer.stop();
    }
    public boolean isFinished() {
        return timer.hasElapsed(m_time);
    }
}
