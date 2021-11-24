package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DuckySpinnerSubsystem;

import java.util.function.DoubleSupplier;

public class DuckySpinnerCommand extends CommandBase {
    DuckySpinnerSubsystem sDuckySpinner;
    double dPower;

    public DuckySpinnerCommand(DuckySpinnerSubsystem duckySpinnerSubsystem, double power) {
        sDuckySpinner = duckySpinnerSubsystem;
        dPower = power;
        addRequirements(duckySpinnerSubsystem);
    }

    @Override
    public void execute() {
        sDuckySpinner.run(dPower);
    }

    @Override
    public void end(boolean interrupted) {
        sDuckySpinner.stop();
    }

}
