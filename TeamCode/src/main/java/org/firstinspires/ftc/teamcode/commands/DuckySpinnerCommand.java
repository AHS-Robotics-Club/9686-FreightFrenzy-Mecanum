package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.DuckySpinnerSubsystem;

import java.util.function.DoubleSupplier;

public class DuckySpinnerCommand extends CommandBase {
    DuckySpinnerSubsystem sDuckySpinner;
    DoubleSupplier dPower;

    public DuckySpinnerCommand(DuckySpinnerSubsystem duckySpinnerSubsystem, DoubleSupplier powerSupplier) {
        sDuckySpinner = duckySpinnerSubsystem;
        dPower = powerSupplier;
        addRequirements(duckySpinnerSubsystem);
    }

    @Override
    public void execute() {
        sDuckySpinner.start(dPower.getAsDouble());
    }

}
