package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
        private final MecanumDriveSubsystem mecDrive;
        private final DoubleSupplier m_strafe, m_forward, m_turn;
        private final DoubleSupplier multiplier;

        public DriveCommand(MecanumDriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, DoubleSupplier mult){
            mecDrive = subsystem;
            m_strafe = strafe;
            m_forward = forward;
            m_turn = turn;
            multiplier = mult;

            addRequirements(subsystem);
        }

        @Override
        public void execute(){
            mecDrive.drive(m_strafe.getAsDouble() * multiplier.getAsDouble(),
                    m_forward.getAsDouble() * multiplier.getAsDouble(),
                    m_turn.getAsDouble() * 0.92 * multiplier.getAsDouble());
        }

}
