package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.constants.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
            TalonFX m_intakeMotor;
            VoltageOut m_request = new VoltageOut (0);
            
            CurrentLimitsConfigs intakeCurrentLimits = new CurrentLimitsConfigs();
            
            public IntakeSubsystem () {
            m_intakeMotor = new TalonFX(IntakeConstants.IntakeMotorId);
            //var Slot0Configs = new Slot0Configs();

                


            intakeCurrentLimits.withStatorCurrentLimitEnable(true);
            intakeCurrentLimits.withStatorCurrentLimit(40);
            
            
            m_intakeMotor.getConfigurator().apply(intakeCurrentLimits);
                        }
            
@Override
public void periodic() {}

public void dropCoral(double speed) {

     //set voltage output
        m_intakeMotor.setVoltage(speed);


}

public static class IntakeConstants {
public static final int IntakeMotorId = 41;
public static final int SensorID = 42;
public static final double kP = .0;
public static final double kI = .0;
public static final double kD = .0;
}


public void feedStop() {
    m_intakeMotor.setVoltage(0);
            }

}
