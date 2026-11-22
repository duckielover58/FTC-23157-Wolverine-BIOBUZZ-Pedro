package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name="teleop", group="Opmode")
public class tele extends OpMode {

    private Follower follower;
    double forward, lateral, turn;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        ManualDrive.driveOrHold(follower, forward, lateral, turn);
        follower.update();

        Pose robotPose = follower.pose();

        telemetry.addData("X", robotPose.x());
        telemetry.addData("Y", robotPose.y());
        telemetry.addData("Heading", Math.toDegrees(robotPose.heading()));
    }

}
