package frc.robot.commands.auto;

import java.time.chrono.JapaneseChronology;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import frc.robot.subsystems.ShooterFeeder;
import frc.robot.subsystems.ShooterMotor;
import frc.robot.subsystems.IntakeArm;

public class TimedIntakeArm extends Command {
    public  IntakeArm intakeRotate;
    public Timer timer;
    public double speed;
    public double time;
    public TimedIntakeArm(double time, double m_speed) {
        timer=new Timer();
        intakeRotate = IntakeArm.getInstance();
        addRequirements(intakeRotate);
        speed=m_speed;
        this.time=time;
    }
    public void initialize() {
        timer.reset();
        timer.start();
    }
    public void execute() {
        intakeRotate.setSpeed(speed);
    }
    public void end(boolean interrupted) {
        intakeRotate.setSpeed(0);
        timer.stop();
    }
    public boolean isFinished() {
        return timer.get() > time;
    }
}

