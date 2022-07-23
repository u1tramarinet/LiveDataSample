package com.u1tramarinet.livedatasample

import java.util.function.Supplier

/**
 * Supplier that guarantee NonNull value.
 * @param <T> type.
</T> */
@FunctionalInterface
internal interface NonNullSupplier<T> : Supplier<T> {
    override fun get(): T
}