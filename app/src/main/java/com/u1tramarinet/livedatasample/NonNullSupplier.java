package com.u1tramarinet.livedatasample;

import androidx.annotation.NonNull;

import java.util.function.Supplier;

/**
 * Supplier that guarantee NonNull value.
 * @param <T> type.
 */
@FunctionalInterface
interface NonNullSupplier<T> extends Supplier<T> {
    @NonNull
    @Override
    T get();
}
