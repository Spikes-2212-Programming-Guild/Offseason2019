package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.function.Supplier;

public class WaitForCondition extends Command {

    private Supplier<Boolean> condition;

    public WaitForCondition(Supplier<Boolean> condition) {
        this.condition = condition;
    }

    @Override
    protected boolean isFinished() {
        return condition.get();
    }
}
