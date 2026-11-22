package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Auto.Intake;

import static com.pedropathing.ivy.Scheduler.schedule;

@TeleOp(name = "Intake Test")
public class Test extends OpMode {
    private Intake intake;
    private String last = "none";
    private boolean lastA, lastB, lastX;

    @Override
    public void init() {
        Scheduler.reset();
        intake = new Intake(hardwareMap);
        telemetry.addLine("A = intake, B = reverse, X = stop");
        telemetry.update();
    }

    @Override
    public void loop() {
        boolean a = gamepad1.a;
        boolean b = gamepad1.b;
        boolean x = gamepad1.x;

        if (a && !lastA) { schedule(intake.intakeBall());        last = "intakeBall"; }
        if (b && !lastB) { schedule(intake.intakeBallReverse()); last = "intakeBallReverse"; }
        if (x && !lastX) { schedule(intake.intakeBallStop());    last = "intakeBallStop"; }

        lastA = a;
        lastB = b;
        lastX = x;

        Scheduler.execute();

        telemetry.addLine("A = intake, B = reverse, X = stop");
        telemetry.addData("Last command", last);
        telemetry.update();
    }
}