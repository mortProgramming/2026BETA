package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import java.time.chrono.JapaneseChronology;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterFeederConstants;
import frc.robot.subsystems.ShooterFeeder;
import frc.robot.subsystems.ShooterMotor;

public class TimedFeed extends Command{

    public ShooterFeeder shooterFeeder;
    public Timer timer;
    public double speed;
    public double time;
    public TimedFeed(double time, double m_speed2) {
        timer=new Timer();
        shooterFeeder=ShooterFeeder.getInstance();
        addRequirements(shooterFeeder);
        speed=m_speed2;
        this.time=time;
    }
    public void initialize() {
        timer.reset();
        timer.start();
    }
    public void execute() {
        shooterFeeder.setSpeed(speed);
    }
    public void end(boolean interrupted) {
        shooterFeeder.setSpeed(0);
        timer.stop();
    }
    public boolean isFinished() {
        return timer.hasElapsed(time);
    }
}


