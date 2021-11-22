package org.firstinspires.ftc.teamcode.drive;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class DuckySpinnerSubsystem extends SubsystemBase {

    private Motor mDuckySpinner;

    public DuckySpinnerSubsystem(Motor duckySpinner) {
        mDuckySpinner = duckySpinner;
    }

    public void start(double power) { mDuckySpinner.set(power); }
    public void stop() { mDuckySpinner.set(0); }

}
