package com.example.brightsteps.mixin;

/** Флаг: сейчас спавнятся частицы бега (в потоке клиента). */
public final class RunningContext {
    public static final ThreadLocal<Boolean> ACTIVE = ThreadLocal.withInitial(() -> false);
    private RunningContext() {}
}
