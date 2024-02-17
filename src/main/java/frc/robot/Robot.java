// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    Orchestra m_orchestra = new Orchestra();

// Add a single device to the orchestra
  m_orchestra.addInstrument(new TalonFX(41));
  m_orchestra.addInstrument(new TalonFX(42));
  m_orchestra.addInstrument(new TalonFX(43));
  m_orchestra.addInstrument(new TalonFX(44));
  m_orchestra.addInstrument(new TalonFX(45));
  m_orchestra.addInstrument(new TalonFX(46));
  m_orchestra.addInstrument(new TalonFX(47));
  m_orchestra.addInstrument(new TalonFX(48));


// Attempt to load the chrp
  var status = m_orchestra.loadMusic("src\\main\\java\\frc\\robot\\output.chrp");
if (!status.isOK()) {
   // log error
}

m_orchestra.play();

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
    Command[] teleCommands = m_robotContainer.getTeleCommand();
    for(Command command : teleCommands){
      command.schedule();
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
