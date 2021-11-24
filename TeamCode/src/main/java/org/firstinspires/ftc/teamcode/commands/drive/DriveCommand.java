package org.firstinspires.ftc.teamcode.commands.drive;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem mecDrive;
    private final DoubleSupplier m_strafe, m_forward, m_turn;
    private double multiplier;

    public DriveCommand(DriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, double mult){
        mecDrive = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        multiplier = mult;

        addRequirements(subsystem);
    }
    public DriveCommand(DriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn){
        mecDrive = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        multiplier = 1.0;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        mecDrive.drive(m_strafe.getAsDouble() * multiplier,
                m_forward.getAsDouble() * multiplier,
                m_turn.getAsDouble() * 0.92 * multiplier);
    }
}
