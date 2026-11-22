package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.pedropathing.ivy.commands.Commands.instant;

public class Intake {
    private final DcMotorEx intake;
    public double intakePower = 0.6;

    public Intake (HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "Intake");
    }

    public void in() {
        intake.setPower(-intakePower);
    }
    public void reverse() {
        intake.setPower(intakePower);
    }
    public void stop()    {
        intake.setPower(0);
    }

    public Command intakeBall()   {
        return instant(this::in);
    }
    public Command intakeBallReverse() {
        return instant(this::reverse);
    }
    public Command intakeBallStop() {
        return instant(this::stop);
    }
}