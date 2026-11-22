package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.api.Paths;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
@Autonomous
public class SplineAutoTest extends OpMode {
    private Follower follower;
    //Poses
    private final PoseFactory p = PoseFactory.degrees();

    //First Curve Pose
    private final Pose startPose = p.of(72,72,90);
    private final Pose cp1 = p.of(80,88,180);
    private final Pose circleCurve1 = p.of(100,72,270);

    //Second Curve Pose
    private final Pose cp2 = p.of(80,56,0);
    private final Pose circleCurve2 = p.of(72,72,90);

    //Circle Path
    private Path circleCW() {
        Path circleCW1 = curve(startPose, cp1, circleCurve1).linear(startPose, circleCurve1);
        Path circleCW2 = curve(circleCurve1, cp2, circleCurve2).linear(circleCurve1, circleCurve2);

        return Paths.path(circleCW1, circleCW2);
    }

    private Path circleCCW() {
        Path circleCCW1 = curve(startPose, cp2, circleCurve1).linear(startPose, circleCurve2);
        Path circleCCW2 = curve(circleCurve1,cp1,circleCurve2).linear(circleCurve2,circleCurve1);

        return Paths.path(circleCCW1,circleCCW2);
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, circleCW()),
                follow(follower, circleCCW())
        );
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
        schedule(autoRoutine());
    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}