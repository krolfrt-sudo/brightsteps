package com.example.brightsteps;

public final class RunningContext {
    public static final ThreadLocal<Boolean> ACTIVE = ThreadLocal.withInitial(() -> false);
    private RunningContext() {}
}
