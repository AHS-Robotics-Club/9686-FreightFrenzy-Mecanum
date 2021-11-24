package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class DuckySpinnerSubsystem extends SubsystemBase {

    Motor mDuckySpinner;

    public DuckySpinnerSubsystem(Motor mDS) {
        mDuckySpinner = mDS;
    }

    public void run(double power) {
        mDuckySpinner.set(power);
    }

    public void stop() {
        mDuckySpinner.set(0);
    }

}
