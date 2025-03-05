// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;
  public static TalonFX m_armMotor = new TalonFX(52); // Set to actual CAN ID

  public Robot() {
    m_robotContainer = new RobotContainer();
    configureMotors(m_armMotor, 400, 100, 1600);
  }

  public void configureMotors(TalonFX motor, int velocity, int acceleration, int jerk) {
    TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration();
    talonFXConfigs.Slot0
      .withKG(.25)
      .withKS(.25)
      .withKV(.12)
      .withKA(.01)
      .withKP(4.8)
      .withKI(0)
      .withKD(.1);
    
    talonFXConfigs.MotionMagic
      .withMotionMagicCruiseVelocity(velocity)
      .withMotionMagicAcceleration(acceleration)
      .withMotionMagicJerk(jerk);
    
    // Try 400-100-1600 for elevator for now

    // Apply configs
    motor.getConfigurator().apply(talonFXConfigs);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
