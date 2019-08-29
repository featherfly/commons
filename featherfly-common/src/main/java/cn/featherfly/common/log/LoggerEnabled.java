package cn.featherfly.common.log;

@FunctionalInterface
public interface LoggerEnabled {
    Object[] arguments();
}