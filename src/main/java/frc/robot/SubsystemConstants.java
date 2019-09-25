package frc.robot;

import com.spikes2212.dashboard.ConstantHandler;

import java.util.function.Supplier;

public class SubsystemConstants {
        public interface Gripper{
            Supplier<Double> IN_SPEED = ConstantHandler.addConstantDouble("gripper in speed", -0.7);
            Supplier<Double> OUT_SPEED = ConstantHandler.addConstantDouble("gripper out speed", -0.7);
        }
    }
