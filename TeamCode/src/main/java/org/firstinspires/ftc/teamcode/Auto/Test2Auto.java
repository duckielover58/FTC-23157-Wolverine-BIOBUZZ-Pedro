package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.follower.Follower;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous(name = "Test2Auto")
public class Test2Auto extends OpMode {
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    // Start and end: the robot is actually at these
    private final Pose startPose = p.of(10.8, 0.8, 180);
    private final Pose endPose   = p.of(20, 34, 90);
    private final Pose cp1 = p.of(15, 45, 0);
    private final Pose cp2 = p.of(20.75, 6.75, 0);
    private final Pose cp3 = p.of(45, 1, 45);
    private final Pose linear1 = p.of(40.75, 49.75, 45);
    private final Pose cp4 = p.of(7.75, 53.75, 45);
    private final Pose cp5 = p.of(42.75, 69.75, 0);
    private final Pose cp6 = p.of(13.75, 70.75, 90);
    private Path curvePark() {
        return curve(startPose, cp1, cp2, cp3, endPose).linear(startPose, endPose);
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void start() {
        schedule(follow(follower, curvePark()));
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.update();
    }
}