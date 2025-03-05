package frc.robot.subsystems;

import com.ctre.phoenix6.controls.MotionMagicVoltage;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Intake extends SubsystemBase {
    double position = 0;
    public Intake(double position) {
        this.position = position;
    }

    public Command move() {
        return runOnce(() -> {
            MotionMagicVoltage request = new MotionMagicVoltage(0);
            Robot.m_armMotor.setControl(request.withPosition(position));
        });
    }
}
