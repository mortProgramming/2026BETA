package frc.robot.commands.auto;

import java.time.chrono.JapaneseChronology;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import frc.robot.subsystems.ShooterFeeder;
import frc.robot.subsystems.ShooterMotor;

public class TimedShoot extends Command {
    public ShooterMotor shooterMotor;
    public Timer timer;
    public double speed;
    public double time;
    // public double velocity;
    public double RPM; 
    public TimedShoot(double time, double velocity) {
        timer=new Timer();
        shooterMotor=ShooterMotor.getInstance();
        addRequirements(shooterMotor);
        RPM=velocity;
        this.time=time;
    }
    public void initialize() {
        timer.reset();
        timer.start();

        shooterMotor.getPidController().reset();
    }
    public void execute() {
        shooterMotor.setShooterSpeedRPM(RPM);
    }
    public void end(boolean interrupted) {
        shooterMotor.setSpeed(0);
        timer.stop();
    }
    public boolean isFinished() {
        return timer.get() > time;
    }
}

